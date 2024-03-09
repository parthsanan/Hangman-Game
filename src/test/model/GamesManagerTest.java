package model;

import org.junit.jupiter.api.BeforeEach;
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
    public void testGetMenu() {
        
        inputHandler.setGameMode("Classic");
        inputHandler.setClassicDifficulty("Novice");

        gamesManager.getMenu(inputHandler);

        assertNotNull(gamesManager.currentGame);
        assertTrue(gamesManager.currentGame instanceof ClassicHangman);
        
        inputHandler.setGameMode("Variant");
        inputHandler.setVariantMode("Mastermind");

        gamesManager.getMenu(inputHandler);

        assertNotNull(gamesManager.currentGame);  
        assertTrue(gamesManager.currentGame instanceof VariantHangman);

        inputHandler.setGameMode("History");

        gamesManager.getMenu(inputHandler);
        assertNull(gamesManager.currentGame);          

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

        Hangman hangman = new ClassicHangman("Rookie");
        hangman.setSecretWord("apple");
        gamesManager.addToLoadedGames(hangman);
        assertTrue(gamesManager.getLoadedGames().contains(hangman));
    }

}
