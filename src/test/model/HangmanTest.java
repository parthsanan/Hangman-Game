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
        assertEquals(hangman.getMode(), "Classic");
        assertEquals(hangman.getDifficulty(), "Rookie");
        hangman.setSecretWord("test");
        hangman.guessLetter('t');
        assertEquals(10, hangman.getScore());
        assertEquals(7, hangman.getGuessesLeft());
        assertTrue(hangman.getVisibleWord().contains("t"));
        hangman.guessLetter('x');
        assertEquals(6, hangman.getGuessesLeft());
        assertFalse(hangman.getVisibleWord().contains("x"));
    }

    @Test
    public void testGetVisibleWord() {
        hangman.setSecretWord("test");
        assertEquals("____", hangman.getVisibleWord());
        hangman.guessLetter('t');
        assertEquals("t__t", hangman.getVisibleWord());
    }

    @Test
    public void testChooseSecretWord() {
        String[] words = {"test", "hello", "world"};
        String secretWord = hangman.chooseSecretWord(manager, hangman.getDifficulty());
        assertNotNull(hangman.getSecretWord());
        assertTrue(hangman.getSecretWord().length() > 0);
    }

    @Test
    public void testIsGameOverWordGUessed() {        
        hangman.setSecretWord("test");
        hangman.guessLetter('t');
        hangman.guessLetter('e');
        hangman.guessLetter('s');
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
        hangman.setSecretWord("test");
        hangman.guessLetter('t');
        hangman.guessLetter('e');
        assertFalse(hangman.isGameOver());
        assertEquals(7, hangman.getGuessesLeft());
    }

    @Test
    public void testIsGameOverNoGuessesLeft() {        
        hangman.setSecretWord("test");
        hangman.guessLetter('t');
        hangman.guessLetter('e');
        hangman.guessLetter('f');
        hangman.guessLetter('f');
        assertEquals(hangman.getGuessesLeft(), 6);
        hangman.guessLetter('x');
        hangman.guessLetter('b');
        hangman.guessLetter('a');
        hangman.guessLetter('i');
        hangman.guessLetter('p');
        hangman.guessLetter('l');
        assertEquals(hangman.getGuessesLeft(), 0);    
        hangman.setResult("Won");
        assertEquals("Won", hangman.getResult());    
        assertTrue(hangman.isGameOver());    
    }
}
