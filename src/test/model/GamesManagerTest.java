package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import ui.InputHandler;

import static org.junit.jupiter.api.Assertions.*;

public class GamesManagerTest {

    private GamesManager gamesManager;
    private InputHandler inputHandler;

    @BeforeEach
    public void setUp() {
        gamesManager = new GamesManager();
        inputHandler = new InputHandler(gamesManager);
    }

    @Test
    void testGetMenuClassicMode() {
        inputHandler.setGameMode("Classic");
        inputHandler.setClassicDifficulty("Novice");    

        gamesManager.getMenu(inputHandler);

        assertNotNull(gamesManager.currentGame);
        assertTrue(gamesManager.currentGame instanceof ClassicHangman);
        assertEquals("Novice", gamesManager.currentGame.getDifficulty());
    }

    @Test
    void testGetMenuVariantMode() {
        inputHandler.setGameMode("Variant");
        inputHandler.setVariantMode("Custom");

        gamesManager.getMenu(inputHandler);

        assertNotNull(gamesManager.currentGame);
        assertTrue(gamesManager.currentGame instanceof VariantHangman);
        assertEquals("Custom", gamesManager.currentGame.getMode());
    }

    @Test
    void testGetMenuHistoryMode() {
        inputHandler.setGameMode("History");

        ArrayList<Hangman> loadedGames = new ArrayList<>();
        loadedGames.add(new ClassicHangman("Novice"));
        gamesManager.setLoadedGames(loadedGames);

        gamesManager.getMenu(inputHandler);

        assertNull(gamesManager.currentGame);
    }

    @Test
    public void testGetMenu() {
        // Test Classic mode
        inputHandler.setGameMode("Classic");
        inputHandler.setClassicDifficulty("Novice");

        gamesManager.getMenu(inputHandler);

        assertNotNull(gamesManager.currentGame);
        
        inputHandler.setGameMode("Variant");
        inputHandler.setVariantMode("Mastermind");

        gamesManager.getMenu(inputHandler);

        assertNotNull(gamesManager.currentGame);  

        inputHandler.setGameMode("History");

        gamesManager.getMenu(inputHandler);

        assertEquals(gamesManager.loadedGames, gamesManager.getLoadedGames());
    }

    @Test
    public void testGetLoadedGames() {
        assertNotNull(gamesManager.getLoadedGames());
        assertTrue(gamesManager.getLoadedGames().isEmpty());

        ArrayList<Hangman> loadedGames = new ArrayList<>();
        loadedGames.add(new ClassicHangman("Novice"));

        gamesManager.setLoadedGames(loadedGames);

        assertEquals(loadedGames, gamesManager.getLoadedGames());
    }

    @Test
    public void testAddToLoadedGames() {
        Hangman game = new ClassicHangman("Novice");

        gamesManager.addToLoadedGames(game);

        assertEquals(1, gamesManager.getLoadedGames().size());
        assertTrue(gamesManager.getLoadedGames().contains(game));
    }
}
