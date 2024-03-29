package persistance;

import model.ClassicHangman;
import model.GamesManager;
import model.Hangman;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class DataHandlerTest {

    private DataHandler dataHandler;
    private GamesManager gamesManager;

    @BeforeEach
    void setUp() {
        dataHandler = new DataHandler();
        gamesManager = new GamesManager();
    }

    @Test
    void testSaveGame() {

        dataHandler.setFilePath("\\src\\main\\resources\\wrongFile.json");

        Hangman game = new ClassicHangman("Novice", gamesManager);
        game.setResult("Won");
        game.setSecretWord("apple");
        game.setGuessesLeft(5);
        game.setScore(50);

        try {
            dataHandler.saveGame(game);
            fail("Expected FileNotFound Exception not thrown!");
        } catch (IOException e) {
            // Expected
        }

        String savedJsonContent = null;

        try {
            savedJsonContent = new String(Files.readAllBytes(Paths.get(dataHandler.getFilePath())));
            fail("Should have thrown an exception for invalid file path");
        } catch (IOException e) {
            // Expected
        }

        dataHandler.setFilePath("src\\main\\data\\testGames.json");

        try {
            savedJsonContent = new String(Files.readAllBytes(Paths.get(dataHandler.getFilePath())));
        } catch (IOException e) {
            fail();
        }

        assertNotNull(savedJsonContent);
        assertTrue(savedJsonContent.contains("\"result\":\"Won\""));
        assertTrue(savedJsonContent.contains("\"mode\":\"Classic\""));
        assertTrue(savedJsonContent.contains("\"difficulty\":\"Novice\""));
        assertTrue(savedJsonContent.contains("\"secretWord\":\"apple\""));
        assertTrue(savedJsonContent.contains("\"guessesLeft\":5"));
        assertTrue(savedJsonContent.contains("\"score\":50"));

        try (FileWriter fileWriter = new FileWriter(dataHandler.getFilePath())) {
            fileWriter.write(savedJsonContent.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String content = new String(Files.readAllBytes(Paths.get(dataHandler.getFilePath())),
                    StandardCharsets.UTF_8);
            assertEquals(savedJsonContent.toString(), content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testLoadGames() {

        dataHandler.setFilePath("\\src\\main\\resources\\wrongFile.json");

        String mockJsonContent = "[{\"result\":\"Won\",\"mode\":\"Classic\",\"difficulty\":\"Novice\"," +
                "\"secretWord\":\"apple\",\"guessesLeft\":5,\"score\":50}]";

        try {
            Files.write(Paths.get("\\src\\main\\resources\\wrongFile.json"), mockJsonContent.getBytes());
            fail("Should have thrown an exception for invalid file path");
        } catch (IOException e) {
            assertEquals(e.getMessage(), "\\src\\main\\resources\\wrongFile.json");
        }

        dataHandler.setFilePath("src\\main\\data\\testGames.json");

        try {
            Files.write(Paths.get(dataHandler.getFilePath()), mockJsonContent.getBytes());
            dataHandler.loadGames(gamesManager);
        } catch (IOException e) {
            fail();
        }

        assertEquals(1, gamesManager.getLoadedGames().size());
        Hangman loadedGame = gamesManager.getLoadedGames().get(0);
        assertEquals("Won", loadedGame.getResult());
        assertEquals("Novice", loadedGame.getDifficulty());
        assertEquals("apple", loadedGame.getSecretWord());
        assertEquals(5, loadedGame.getGuessesLeft());
        assertEquals(50, loadedGame.getScore());
    }
}
