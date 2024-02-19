package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

                testClassicHangmanRookie.assignSecretWord();
                assertTrue(Arrays.asList(rookieWords).contains(testClassicHangmanRookie.getSecretWord()));

                testClassicHangmanNovice.assignSecretWord();
                assertTrue(Arrays.asList(noviceWords).contains(testClassicHangmanNovice.getSecretWord()));

                testClassicHangmanMaster.assignSecretWord();
                assertTrue(Arrays.asList(masterWords).contains(testClassicHangmanMaster.getSecretWord()));

                testClassicHangmanMaster.setDifficulty("null");
                assertNull(testClassicHangmanMaster.assignSecretWord());

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

                testClassicHangmanMaster.setVisibleWord("test");
                assertFalse(testClassicHangmanMaster.isGameOver());

                testClassicHangmanMaster.setSecretWord(testClassicHangmanMaster.getVisibleWord());
                assertTrue(testClassicHangmanMaster.isGameOver());
        }

        @Test
        void setGuessesLeftTest() {

                assertEquals(7, testClassicHangmanRookie.getGuessesLeft());

                testClassicHangmanRookie.setGuessesLeft(6);
                assertEquals(6, testClassicHangmanRookie.getGuessesLeft());
        }

        @Test
        void getGuessedLettersTest() {

                List<Character> emptyCharacterList = new ArrayList<>();

                assertEquals(emptyCharacterList, testClassicHangmanRookie.getGuessedLetters());
                assertEquals(emptyCharacterList, testClassicHangmanNovice.getGuessedLetters());
                assertEquals(emptyCharacterList, testClassicHangmanMaster.getGuessedLetters());

        }

        @Test
        void getSecretWordTest() {

                String[] rookieWords = { "apple", "banana", "cat", "dog", "fish", "bird", "tree",
                                "sun", "moon", "star", "car", "house", "flower", "book", "chair" };
                String[] noviceWords = { "elephant", "giraffe", "lion", "monkey", "tiger", "zebra",
                                "kangaroo", "snake", "rabbit", "turtle", "pizza", "guitar", "computer",
                                "soccer", "globe" };
                String[] masterWords = { "phenomenon", "onomatopoeia", "ubiquitous", "serendipity",
                                "juxtaposition", "paradox", "synergy", "algorithm", "quantum", "holography",
                                "architecture", "surreptitious", "chiaroscuro", "mnemonic", "polyglot" };

                assertTrue(Arrays.asList(rookieWords).contains(testClassicHangmanRookie.getSecretWord()));
                assertTrue(Arrays.asList(noviceWords).contains(testClassicHangmanNovice.getSecretWord()));
                assertTrue(Arrays.asList(masterWords).contains(testClassicHangmanMaster.getSecretWord()));

        }
}
