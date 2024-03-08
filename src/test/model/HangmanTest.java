package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class HangmanTest {

    private Hangman hangman;

    @BeforeEach
    public void setUp() {
        hangman = new ClassicHangman("Rookie");
    }

    @Test
    public void testGuessLetter() {

        assertEquals(hangman.getMode(), "Classic");
        assertEquals(hangman.getDifficulty(), "Rookie");
        // Test guessing a correct letter
        hangman.setSecretWord("test");
        hangman.guessLetter('t');
        assertEquals(10, hangman.getScore());
        assertEquals(7, hangman.getGuessesLeft());
        assertTrue(hangman.getVisibleWord().contains("t"));

        // Test guessing an incorrect letter
        hangman.guessLetter('x');
        assertEquals(6, hangman.getGuessesLeft());
        assertFalse(hangman.getVisibleWord().contains("x"));
    }

    @Test
    public void testGetVisibleWord() {
        // Test when no letters are guessed
        hangman.setSecretWord("test");
        assertEquals("____", hangman.getVisibleWord());

        // Test when some letters are guessed
        hangman.guessLetter('t');
        assertEquals("t__t", hangman.getVisibleWord());
    }

    @Test
    public void testChooseSecretWord() {
        // Test choosing secret word from an array
        String[] words = {"test", "hello", "world"};
        hangman.chooseSecretWord(words);
        assertNotNull(hangman.getSecretWord());
        assertTrue(hangman.getSecretWord().length() > 0);
    }

    @Test
    public void testIsGameOverWordGUessed() {        

        // Test game over when secret word is guessed
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

        // Test game over when secret word is guessed
        hangman.setSecretWord("test");
        hangman.guessLetter('t');
        hangman.guessLetter('e');
        assertFalse(hangman.isGameOver());
        assertEquals(7, hangman.getGuessesLeft());
    
    }

    @Test
    public void testIsGameOverNoGuessesLeft() {        

        // Test game over when secret word is guessed
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
