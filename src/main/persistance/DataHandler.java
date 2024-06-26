package persistance;

import model.ClassicHangman;
import model.GamesManager;
import model.Hangman;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DataHandler {

    protected String filePath;
    protected ArrayList<Hangman> gamesPlayed;
    protected GamesManager manager;

    public DataHandler(GamesManager manager) {
        this.filePath = "./src/main/data/games.json";
        this.manager = manager;
        this.gamesPlayed = new ArrayList<Hangman>();
    }

    // EFFECTS: Load existing games from file
    @SuppressWarnings({ "checkstyle:MethodLength", "checkstyle:SuppressWarnings" })
    public void loadGames() throws IOException {

        manager.setLoadedGames(new ArrayList<>());

        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONArray jsonArray = new JSONArray(content);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String result = jsonObject.getString("result");
                String difficulty = jsonObject.getString("difficulty");
                String secretWord = jsonObject.getString("secretWord");
                Integer guessesLeft = jsonObject.getInt("guessesLeft");
                Integer score = jsonObject.getInt("score");

                Hangman game = new ClassicHangman(difficulty, manager);

                game.setResult(result);
                game.setSecretWord(secretWord);
                game.setScore(score);
                game.setGuessesLeft(guessesLeft);

                manager.addToLoadedGames(game);
            }

        } catch (IOException e) {
            throw e;
        }
    }

    // Save singular game to file
    public void saveGame(Hangman game) throws IOException {
        JSONArray jsonArray;

        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            jsonArray = new JSONArray(content);
        } catch (IOException e) {
            jsonArray = new JSONArray();
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", game.getResult());
        jsonObject.put("difficulty", game.getDifficulty());
        jsonObject.put("secretWord", game.getSecretWord());
        jsonObject.put("guessesLeft", game.getGuessesLeft());
        jsonObject.put("score", game.getScore());
        jsonArray.put(jsonObject);

        writeToFile(jsonArray.toString());
        manager.gameAdded(game);
        manager.addToLoadedGames(game);
    }

    // Method for writing to a file
    public void writeToFile(String content) throws IOException {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(content);
        } catch (IOException e) {
            throw e;
        }
    }

    public StringBuilder getGamesByWord(String word) {

        StringBuilder gameDetails = new StringBuilder();

        for (Hangman game : manager.getLoadedGames()) {

            if (game.getSecretWord().equals(word)) {

                gameDetails.append(game).append("\n");

            }

        }

        manager.gamesFilteredByWord(word);
        return gameDetails;

    }

    // EFFECTS: returns high score of all Loaded Games
    public Integer getHighScore(GamesManager manager) {

        Integer highScore = 0;

        for (Hangman game : manager.getLoadedGames()) {

            if (game.getScore() > highScore) {

                highScore = game.getScore();
            }

        }

        return highScore;
    }

    public ArrayList<Hangman> getGamesPlayed() {
        return this.gamesPlayed;
    }

    public void setFilePath(String path) {
        this.filePath = path;
    }

    public String getFilePath() {
        return this.filePath;
    }
}