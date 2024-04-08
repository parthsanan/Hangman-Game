package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import persistance.DataHandler;

import static org.junit.jupiter.api.Assertions.*;

public class ClassicHangmanTest {

        ClassicHangman testClassicHangmanRookie;
        ClassicHangman testClassicHangmanNovice;
        ClassicHangman testClassicHangmanMaster;
        GamesManager testGamesManager;
        DataHandler testDataHandler;

        @BeforeEach
        void setupClassicHangmanTest() {

                testGamesManager = new GamesManager();
                testDataHandler = new DataHandler(testGamesManager);

                testClassicHangmanRookie = new ClassicHangman("Rookie", testGamesManager);
                testClassicHangmanNovice = new ClassicHangman("Novice", testGamesManager);
                testClassicHangmanMaster = new ClassicHangman("Master", testGamesManager);

        }

        @Test
        void testPlayGameRookie() {

                testClassicHangmanRookie.setSecretWord("cat");
                assertEquals(testClassicHangmanRookie.getSecretWord(), "cat");

                try {
                        testClassicHangmanRookie.checkGuessedLetter('c');
                        testClassicHangmanRookie.checkGuessedLetter('a');
                        testClassicHangmanRookie.checkGuessedLetter('t');
                } catch (Exception e) {
                        fail("Exception should not have been thrown");
                }

                try {
                        testClassicHangmanRookie.checkGuessedLetter('t');
                        fail("Exception should have been thrown");
                } catch (Exception e) {
                        // expected
                }

                assertTrue(testClassicHangmanRookie.isGameOver());

                testGamesManager.rookieWords = new String[] { "cat" };
                testGamesManager.noviceWords = new String[] { "dog" };
                testGamesManager.masterWords = new String[] { "elephant" };

                testClassicHangmanRookie.setGuessesLeft(0);

                assertTrue(testClassicHangmanRookie.isGameOver());
        }

        @Test
        void testPlayGameNovice() {

                testClassicHangmanNovice.setSecretWord("elephant");
                assertEquals(testClassicHangmanNovice.getSecretWord(), "elephant");

                try {
                        testClassicHangmanNovice.checkGuessedLetter('a');
                        testClassicHangmanNovice.checkGuessedLetter('b');
                        testClassicHangmanNovice.checkGuessedLetter('c');
                        testClassicHangmanNovice.checkGuessedLetter('d');
                        testClassicHangmanNovice.checkGuessedLetter('f');
                        testClassicHangmanNovice.checkGuessedLetter('g');
                        testClassicHangmanNovice.checkGuessedLetter('i');
                        testClassicHangmanNovice.checkGuessedLetter('x');
                } catch (Exception e) {
                        fail("Exception should not have been thrown");
                }

                assertTrue(testClassicHangmanNovice.isGameOver());
                assertNull(testClassicHangmanRookie.getResult());
                testGamesManager.rookieWords = new String[] { "cat" };
                testGamesManager.noviceWords = new String[] { "elephant" };
                testGamesManager.masterWords = new String[] { "phenomenon" };

                testClassicHangmanRookie.setGuessesLeft(0);

                assertTrue(testClassicHangmanRookie.isGameOver());
        }

        @Test
        void testPlayGameMaster() {

                testClassicHangmanMaster.setSecretWord("phenomenon");
                assertEquals(testClassicHangmanMaster.getSecretWord(), "phenomenon");

                try {
                        testClassicHangmanMaster.checkGuessedLetter('p');
                        testClassicHangmanMaster.checkGuessedLetter('h');
                        testClassicHangmanMaster.checkGuessedLetter('e');
                        testClassicHangmanMaster.checkGuessedLetter('n');
                        testClassicHangmanMaster.checkGuessedLetter('o');
                } catch (Exception e) {
                        fail("Exception should not have been thrown");
                }

                assertFalse(testClassicHangmanMaster.isGameOver());

                try {
                        testClassicHangmanMaster.checkGuessedLetter('m');
                } catch (Exception e) {
                        fail("Exception should not have been thrown");
                }

                assertTrue(testClassicHangmanMaster.isGameOver());

                testGamesManager.rookieWords = new String[] { "cat" };
                testGamesManager.noviceWords = new String[] { "dog" };
                testGamesManager.masterWords = new String[] { "elephant" };

                testClassicHangmanRookie.setSecretWord("ubiquotous");

                testClassicHangmanRookie.setGuessesLeft(0);

                assertTrue(testClassicHangmanRookie.isGameOver());
                assertTrue(testClassicHangmanMaster.isGameWon());

                assertEquals("Result: Won" + "\n" + "Difficulty: " + "Master" + "\n"
                                + "Secret Word: " + "phenomenon" + "\n"
                                + "Guesses Left: " + "6" + "\n"
                                + "Score: " + 60 + "\n", testClassicHangmanMaster.toString());
        }

        @Test
        void testGetHighScore() {
                Hangman hangman1 = new ClassicHangman("Novice", testGamesManager);
                hangman1.setScore(50);
                Hangman hangman2 = new ClassicHangman("Rookie", testGamesManager);
                hangman2.setScore(100);
                Hangman hangman3 = new ClassicHangman("Master", testGamesManager);
                hangman3.setScore(75);

                testGamesManager.getLoadedGames().add(hangman1);
                testGamesManager.getLoadedGames().add(hangman2);
                testGamesManager.getLoadedGames().add(hangman3);

                assertEquals(100, testDataHandler.getHighScore(testGamesManager));
        }

        @Test
        void testGetGamesPlayed() {
                // Create some Hangman instances
                Hangman hangman1 = new ClassicHangman("Novice", testGamesManager);
                Hangman hangman2 = new ClassicHangman("Rookie", testGamesManager);
                Hangman hangman3 = new ClassicHangman("Master", testGamesManager);

                testDataHandler.getGamesPlayed().add(hangman1);
                testDataHandler.getGamesPlayed().add(hangman2);
                testDataHandler.getGamesPlayed().add(hangman3);

                assertEquals(3, testDataHandler.getGamesPlayed().size());
                assertTrue(testDataHandler.getGamesPlayed().contains(hangman1));
                assertTrue(testDataHandler.getGamesPlayed().contains(hangman2));
                assertTrue(testDataHandler.getGamesPlayed().contains(hangman3));
        }

        @Test
        void testSetFilePath() {

                String newPath = "new/path/to/file.json";
                testDataHandler.setFilePath(newPath);

                assertEquals(newPath, testDataHandler.getFilePath());
        }
}
