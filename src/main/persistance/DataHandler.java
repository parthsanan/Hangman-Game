package persistance;

import model.ClassicHangman;
import model.GamesManager;
import model.Hangman;
import model.VariantHangman;

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
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void loadGames(GamesManager manager) {

        manager.setLoadedGames(new ArrayList<Hangman>());
        
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONArray jsonArray = new JSONArray(content);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String result = jsonObject.getString("result");
                String mode = jsonObject.getString("mode");
                String difficulty = jsonObject.getString("difficulty");
                String secretWord = jsonObject.getString("secretWord");
                Integer guessesLeft = jsonObject.getInt("guessesLeft");
                Integer score = jsonObject.getInt("score");
                Hangman game = null;

                if (mode.equals("Classic")) {
                    game = new ClassicHangman(difficulty);

                } else if (mode.equals("Variant")) {
                    game = new VariantHangman(difficulty);
                }
                
                game.setResult(result);
                game.setMode(mode);
                game.setSecretWord(secretWord);
                game.setScore(score);
                game.setGuessesLeft(guessesLeft);
                
                manager.addToLoadedGames(game);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(manager.getLoadedGames().size() + " Games Loaded!" + "\n");
    }

    // Save singular game to file
    public void saveGame(Hangman game) {
        JSONArray jsonArray;
        
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            jsonArray = new JSONArray(content);
        } catch (IOException e) {
            jsonArray = new JSONArray();
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", game.getResult());
        jsonObject.put("mode", game.getMode());
        jsonObject.put("difficulty", game.getDifficulty());
        jsonObject.put("secretWord", game.getSecretWord());
        jsonObject.put("guessesLeft", game.getGuessesLeft());
        jsonObject.put("score", game.getScore());
        jsonArray.put(jsonObject);

        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(jsonArray.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Integer getHighScore() {

        Integer highScore = 0;

        for (Hangman game : gamesPlayed) {

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
}
