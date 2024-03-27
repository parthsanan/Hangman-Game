package ui;

import model.ClassicHangman;
import model.GamesManager;
import model.Hangman;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistance.DataHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DataHandlerTest {

    private DataHandler dataHandler;
    private GamesManagerStub gamesManager;

    @BeforeEach
    void setUp() {
        dataHandler = new DataHandler();
        gamesManager = new GamesManagerStub();
    }

    @Test
    void testSaveGame() {
        // Create a mock game
        Hangman game = new ClassicHangman("Novice", gamesManager);
        game.setResult("win");
        game.setSecretWord("apple");
        game.setGuessesLeft(5);
        game.setScore(50);

        // Save the game
        dataHandler.saveGame(game);

        // Read the saved JSON content
        String savedJsonContent = null;
        try {
            savedJsonContent = new String(Files.readAllBytes(Paths.get(dataHandler.getFilePath())));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Verify the saved JSON content
        assertNotNull(savedJsonContent);
        assertTrue(savedJsonContent.contains("\"result\":\"win\""));
        assertTrue(savedJsonContent.contains("\"mode\":\"Classic\""));
        assertTrue(savedJsonContent.contains("\"difficulty\":\"Novice\""));
        assertTrue(savedJsonContent.contains("\"secretWord\":\"apple\""));
        assertTrue(savedJsonContent.contains("\"guessesLeft\":5"));
        assertTrue(savedJsonContent.contains("\"score\":50"));
    }

    @Test
    void testLoadGames() {
        
        String mockJsonContent = "[{\"result\":\"win\",\"mode\":\"Classic\",\"difficulty\":\"Novice\"," +
                "\"secretWord\":\"apple\",\"guessesLeft\":5,\"score\":50}]";

        try {
            Files.write(Paths.get(dataHandler.getFilePath()), mockJsonContent.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        dataHandler.loadGames(gamesManager);

        assertEquals(1, gamesManager.loadedGames.size());
        Hangman loadedGame = gamesManager.loadedGames.get(0);
        assertEquals("win", loadedGame.getResult());
        assertEquals("Classic", loadedGame.getMode());
        assertEquals("Novice", loadedGame.getDifficulty());
        assertEquals("apple", loadedGame.getSecretWord());
        assertEquals(5, loadedGame.getGuessesLeft());
        assertEquals(50, loadedGame.getScore());
    }

    private static class GamesManagerStub extends GamesManager {
        ArrayList<Hangman> loadedGames;

        @Override
        public void addToLoadedGames(Hangman game) {
            loadedGames = new ArrayList<>();
            loadedGames.add(game);
        }
    }
}

