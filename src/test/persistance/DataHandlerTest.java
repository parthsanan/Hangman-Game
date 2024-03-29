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

        Hangman game = new ClassicHangman("Novice", gamesManager);
        game.setResult("win");
        game.setSecretWord("apple");
        game.setGuessesLeft(5);
        game.setScore(50);

        dataHandler.saveGame(game);

        String savedJsonContent = null;

        try {
            savedJsonContent = new String(Files.readAllBytes(Paths.get("\\src\\main\\resources\\wrongFile.json")));
            fail("Should have thrown an exception for invalid file path");
        } catch (IOException e) {
            // Expected
        }

        try {
            savedJsonContent = new String(Files.readAllBytes(Paths.get(dataHandler.getFilePath())));
        } catch (IOException e) {
            fail();
        }

        assertNotNull(savedJsonContent);
        assertTrue(savedJsonContent.contains("\"result\":\"win\""));
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

        String mockJsonContent = "[{\"result\":\"win\",\"mode\":\"Classic\",\"difficulty\":\"Novice\"," +
                "\"secretWord\":\"apple\",\"guessesLeft\":5,\"score\":50}]";

        try {
            Files.write(Paths.get("\\src\\main\\resources\\wrongFile.json"), mockJsonContent.getBytes());
            fail("Should have thrown an exception for invalid file path");
        } catch (IOException e) {
            // Expected
        }

        try {
            Files.write(Paths.get(dataHandler.getFilePath()), mockJsonContent.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        dataHandler.loadGames(gamesManager);

        assertEquals(1, gamesManager.getLoadedGames().size());
        Hangman loadedGame = gamesManager.getLoadedGames().get(0);
        assertEquals("win", loadedGame.getResult());
        assertEquals("Novice", loadedGame.getDifficulty());
        assertEquals("apple", loadedGame.getSecretWord());
        assertEquals(5, loadedGame.getGuessesLeft());
        assertEquals(50, loadedGame.getScore());
    }
}
