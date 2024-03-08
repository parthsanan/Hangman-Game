package ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import model.*;
import persistance.*;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataHandlerTest {

    private DataHandler dataHandler;
    private GamesManager gamesManager;

    @BeforeEach
    public void setUp() {
        dataHandler = new DataHandler();
        gamesManager = new GamesManager();
    }

    @Test
    public void testLoadGames() throws IOException {
        // Create a JSON array representing saved games
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("result", "Win");
        jsonObject1.put("mode", "Classic");
        jsonObject1.put("difficulty", "Novice");
        jsonObject1.put("secretWord", "test");
        jsonObject1.put("guessesLeft", 5);
        jsonObject1.put("score", 50);
        jsonArray.put(jsonObject1);

        // Write the JSON array to a file
        String filePath = "src\\test\\ui\\testgames.json";
        Files.write(Paths.get(filePath), jsonArray.toString().getBytes());

        // Load games from the file
        dataHandler.loadGames(gamesManager);

        Hangman game = gamesManager.getLoadedGames().get(0);
        assertEquals("Won", game.getResult());
        assertEquals("Classic", game.getMode());
        assertEquals("Novice", game.getDifficulty());
        assertEquals("penguin", game.getSecretWord());
        assertEquals(3, game.getGuessesLeft());
        assertEquals(160, game.getScore());
    }

    @Test
    public void testSaveGame() throws IOException {
        // Create a game and save it
        Hangman game = new ClassicHangman("Novice");
        game.setResult("Won");
        game.setSecretWord("test");
        game.setGuessesLeft(5);
        game.setScore(50);
        dataHandler.saveGame(game);

        // Read the saved file and parse its content
        String filePath = "src\\test\\ui\\testgames.json";
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        JSONArray jsonArray = new JSONArray(content);

        // Verify that the saved game matches the expected JSON structure
        assertEquals(1, jsonArray.length());
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        assertEquals("Win", jsonObject.getString("result"));
        assertEquals("Classic", jsonObject.getString("mode"));
        assertEquals("Novice", jsonObject.getString("difficulty"));
        assertEquals("test", jsonObject.getString("secretWord"));
        assertEquals(5, jsonObject.getInt("guessesLeft"));
        assertEquals(50, jsonObject.getInt("score"));
    }

    @Test
    public void testGetHighScore() {
        // Add some games to the gamesPlayed list
        Hangman game1 = new ClassicHangman("Novice");
        game1.setScore(50);
        Hangman game2 = new ClassicHangman("Master");
        game2.setScore(100);
        dataHandler.getGamesPlayed().add(game1);
        dataHandler.getGamesPlayed().add(game2);

        // Verify the high score
        assertEquals(100, dataHandler.getHighScore().intValue());
    }
}
