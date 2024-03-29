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

    public DataHandler() {
        this.filePath = "./src/main/data/games.json";
        this.gamesPlayed = new ArrayList<>();
    }

    // EFFECTS: Load existing games from file
    @SuppressWarnings({ "checkstyle:MethodLength", "checkstyle:SuppressWarnings" })
    public void loadGames(GamesManager manager) throws IOException {

        manager.setLoadedGames(new ArrayList<Hangman>());

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
                Hangman game = null;

                game = new ClassicHangman(difficulty, manager);

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

        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(jsonArray.toString());
        fileWriter.close();
    }

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