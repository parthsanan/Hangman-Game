package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ClassicHangmanTest {

    ClassicHangman testClassicHangmanRookie;
    ClassicHangman testClassicHangmanNovice;
    ClassicHangman testClassicHangmanMaster;

    @BeforeEach
    void setupClassicHangmanTest() {

        testClassicHangmanRookie = new ClassicHangman("Rookie");
        testClassicHangmanNovice = new ClassicHangman("Novice");
        testClassicHangmanMaster = new ClassicHangman("Master");

    }

    @Test
    void assignSecretWordTest() {

        String[] rookieWords = { "apple", "banana", "cat", "dog", "fish", "bird", "tree",
                "sun", "moon", "star", "car", "house", "flower", "book", "chair" };
        String[] noviceWords = { "elephant", "giraffe", "lion", "monkey", "tiger", "zebra",
                "kangaroo", "snake", "rabbit", "turtle", "pizza", "guitar", "computer",
                "soccer", "globe" };
        String[] masterWords = { "phenomenon", "onomatopoeia", "ubiquitous", "serendipity",
                "juxtaposition", "paradox", "synergy", "algorithm", "quantum", "holography",
                "architecture", "surreptitious", "chiaroscuro", "mnemonic", "polyglot" };

        assertTrue(Arrays.asList(rookieWords).contains(testClassicHangmanRookie.assignSecretWord()));
        assertTrue(Arrays.asList(noviceWords).contains(testClassicHangmanNovice.assignSecretWord()));
        assertTrue(Arrays.asList(masterWords).contains(testClassicHangmanMaster.assignSecretWord()));

    }

    @Test
    void chooseRandomTest() {

        String[] rookieWords = { "apple", "banana", "cat", "dog", "fish", "bird", "tree",
                "sun", "moon", "star", "car", "house", "flower", "book", "chair" };
        String[] noviceWords = { "elephant", "giraffe", "lion", "monkey", "tiger", "zebra",
                "kangaroo", "snake", "rabbit", "turtle", "pizza", "guitar", "computer",
                "soccer", "globe" };
        String[] masterWords = { "phenomenon", "onomatopoeia", "ubiquitous", "serendipity",
                "juxtaposition", "paradox", "synergy", "algorithm", "quantum", "holography",
                "architecture", "surreptitious", "chiaroscuro", "mnemonic", "polyglot" };

        assertTrue(Arrays.asList(rookieWords).contains(testClassicHangmanRookie.chooseRandom(rookieWords)));
        assertFalse(Arrays.asList(rookieWords).contains(testClassicHangmanNovice.chooseRandom(noviceWords)));
        assertTrue(Arrays.asList(noviceWords).contains(testClassicHangmanNovice.chooseRandom(noviceWords)));
        assertTrue(Arrays.asList(masterWords).contains(testClassicHangmanMaster.chooseRandom(masterWords)));

    }

    @Test
    void isGameOverTest() {

        assertFalse(testClassicHangmanRookie.isGameOver());

        testClassicHangmanRookie.setGuessesLeft(1);
        assertFalse(testClassicHangmanRookie.isGameOver());

        testClassicHangmanRookie.setGuessesLeft(0);
        assertTrue(testClassicHangmanRookie.isGameOver());
    }

    @Test
    void setGuessesLeftTest() {

        assertEquals(7, testClassicHangmanRookie.getGuessesLeft());

        testClassicHangmanRookie.setGuessesLeft(6);
        assertEquals(6, testClassicHangmanRookie.getGuessesLeft());
    }
}
