package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GamesManagerTest {

    private GamesManager gamesManager;

    @BeforeEach
    public void setUp() {
        gamesManager = new GamesManager();
    }

    @Test
    public void testGetLoadedGames() {
        assertNotNull(gamesManager.getLoadedGames());
        assertTrue(gamesManager.getLoadedGames().isEmpty());

        ArrayList<Hangman> loadedGames = new ArrayList<>();
        loadedGames.add(new ClassicHangman("Novice", gamesManager));

        gamesManager.setLoadedGames(loadedGames);

        assertEquals(loadedGames, gamesManager.getLoadedGames());
    }

    @Test
    public void testAddToLoadedGames() {
        Hangman game = new ClassicHangman("Novice", gamesManager);

        gamesManager.addToLoadedGames(game);

        assertEquals(1, gamesManager.getLoadedGames().size());
        assertTrue(gamesManager.getLoadedGames().contains(game));

        Hangman hangman = new ClassicHangman("Rookie", gamesManager);
        hangman.setSecretWord("apple");
        gamesManager.addToLoadedGames(hangman);
        assertTrue(gamesManager.getLoadedGames().contains(hangman));
    }

}
