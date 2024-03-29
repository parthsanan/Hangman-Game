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

    private TestDataHandler dataHandler;
    private GamesManager gamesManager;

    @BeforeEach
    void setUp() {
        dataHandler = new TestDataHandler();
        dataHandler.setFilePath("src/main/data/testGames.json");
        gamesManager = new GamesManager();
    }

    @Test
    void testSaveGame() {

        Hangman game = new ClassicHangman("Novice", gamesManager);
        String savedJsonContent = null;

        game.setResult("Won");
        game.setSecretWord("apple");
        game.setGuessesLeft(5);
        game.setScore(50);

        try {
            dataHandler.saveGame(game);
            assertTrue(dataHandler.writeToFileCalled);
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

        }

        try {
            String content = new String(Files.readAllBytes(Paths.get(dataHandler.getFilePath())),
                    StandardCharsets.UTF_8);
            assertEquals(savedJsonContent.toString(), content);
        } catch (IOException e) {
        }
    }

    @Test
    void testSaveGameThrowsIOException() {
        dataHandler.setFilePath("nonexistent/file/path");

        Hangman game = new ClassicHangman("Novice", gamesManager);

        assertThrows(IOException.class, () -> dataHandler.saveGame(game));

        try {
            dataHandler.saveGame(game);
            fail();
        } catch (IOException e) {
            assertEquals(e.getMessage(), "nonexistent\\file\\path (The system cannot find the path specified)");
        }
    }

    @Test
    void testWriteToFile() {
        TestDataHandler dataHandler = new TestDataHandler();
        String content = "Test content";

        try {
            dataHandler.writeToFile(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try {
            String writtenContent = new String(Files.readAllBytes(Paths.get(dataHandler.getFilePath())),
                    StandardCharsets.UTF_8);
            assertEquals(content, writtenContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testWriteToFileThrowsIOE() {
        dataHandler.setFilePath("nonexistent/file/path");
        try {
            dataHandler.writeToFile("[\r\n" + //
                    "  {\r\n" + //
                    "    \"result\": \"Won\",\r\n" + //
                    "    \"mode\": \"Classic\",\r\n" + //
                    "    \"difficulty\": \"Novice\",\r\n" + //
                    "    \"score\": 50,\r\n" + //
                    "    \"guessesLeft\": 5,\r\n" + //
                    "    \"secretWord\": \"apple\"\r\n" + //
                    "  }]");
            fail();
        } catch (IOException e) {
            assertEquals(e.getMessage(), "nonexistent\\file\\path (The system cannot find the path specified)");
        }
    }

    @Test
    void testLoadGamesCorrectPath() {

        String mockJsonContent = "[{\"result\":\"Won\",\"mode\":\"Classic\",\"difficulty\":\"Novice\"," +
                "\"secretWord\":\"apple\",\"guessesLeft\":5,\"score\":50}]";

        try {
            Files.write(Paths.get(dataHandler.getFilePath()), mockJsonContent.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
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

    @Test
    void testLoadGamesThrowsIOException() {
        dataHandler.setFilePath("nonexistent/file/path");

        try {
            dataHandler.loadGames(gamesManager);
            fail();
        } catch (IOException e) {
            assertEquals(e.getMessage(), "nonexistent\\file\\path");
        }

        assertEquals(0, gamesManager.getLoadedGames().size());
    }

    static class TestDataHandler extends DataHandler {
        boolean writeToFileCalled = false;

        @Override
        public void writeToFile(String content) throws IOException {
            writeToFileCalled = true;
            super.writeToFile(content);
        }
    }
}