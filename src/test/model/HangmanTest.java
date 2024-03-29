package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HangmanTest {

    private Hangman hangman;
    private GamesManager manager;

    @BeforeEach
    public void setUp() {
        manager = new GamesManager();
        hangman = new ClassicHangman("Rookie", manager);
    }

    @Test
    public void testGuessLetter() {
        assertEquals(hangman.getDifficulty(), "Rookie");
        hangman.setSecretWord("test");
        try {
            hangman.checkGuessedLetter('t');
        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }

        assertEquals(10, hangman.getScore());
        assertEquals(6, hangman.getGuessesLeft());
        assertTrue(hangman.getVisibleWord().contains("t"));
        try {
            hangman.checkGuessedLetter('x');
        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }
        assertEquals(5, hangman.getGuessesLeft());
        assertFalse(hangman.getVisibleWord().contains("x"));
    }

    @Test
    public void testGetVisibleWord() {
        hangman.setSecretWord("test");
        assertEquals("_ _ _ _ ", hangman.getVisibleWord());
        try {
            hangman.checkGuessedLetter('t');
        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }
        assertEquals("t_ _ t", hangman.getVisibleWord());
    }

    @Test
    public void testChooseSecretWord() {
        assertNotNull(hangman.getSecretWord());
        assertTrue(hangman.getSecretWord().length() > 0);
    }

    @Test
    public void testIsGameOverWordGUessed() {
        hangman.setSecretWord("test");
        try {
            hangman.checkGuessedLetter('t');
            hangman.checkGuessedLetter('e');
            hangman.checkGuessedLetter('s');
        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }
        assertTrue(hangman.getGuessedLetters().contains('t'));
        assertTrue(hangman.getGuessedLetters().contains('e'));
        assertTrue(hangman.getGuessedLetters().contains('s'));
        assertFalse(hangman.getGuessedLetters().contains('f'));
        assertTrue(hangman.isGameOver());
        List<Character> guessedLetters = new ArrayList<>();
        guessedLetters.add('a');
        guessedLetters.add('b');
        guessedLetters.add('c');
        hangman.setGuessedLetters(guessedLetters);
        assertEquals(guessedLetters, hangman.getGuessedLetters());
    }

    @Test
    public void testIsGameOverWordNotGUessed() {
        try {
            hangman.setSecretWord("test");
            hangman.checkGuessedLetter('t');
            hangman.checkGuessedLetter('e');
        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }
        assertFalse(hangman.isGameOver());
        assertEquals(6, hangman.getGuessesLeft());
    }

    @Test
    public void testIsGameOverNoGuessesLeft() {
        hangman.setSecretWord("test");
        try {
            hangman.checkGuessedLetter('t');
            hangman.checkGuessedLetter('e');
            hangman.checkGuessedLetter('f');
        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }
        assertEquals(hangman.getGuessesLeft(), 5);
        try {
            hangman.checkGuessedLetter('x');
            hangman.checkGuessedLetter('b');
            hangman.checkGuessedLetter('a');
            hangman.checkGuessedLetter('i');
            hangman.checkGuessedLetter('p');
        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }
        assertEquals(hangman.getGuessesLeft(), 0);
        hangman.setResult("Won");
        assertEquals("Won", hangman.getResult());
        assertTrue(hangman.isGameOver());
    }
}
