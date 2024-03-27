package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import persistance.DataHandler;
import ui.InputHandler;

import static org.junit.jupiter.api.Assertions.*;

public class ClassicHangmanTest {
        
        ClassicHangman testClassicHangmanRookie;
        ClassicHangman testClassicHangmanNovice;
        ClassicHangman testClassicHangmanMaster;
        GamesManager testGamesManager;
        InputHandler testInputHandler;
        DataHandler testDataHandler;

        @BeforeEach
        void setupClassicHangmanTest() {

                testGamesManager = new GamesManager();
                testInputHandler = new InputHandler(testGamesManager);
                testDataHandler = new DataHandler();

                testClassicHangmanRookie = new ClassicHangman("Rookie", testGamesManager);
                testClassicHangmanNovice = new ClassicHangman("Novice", testGamesManager);
                testClassicHangmanMaster = new ClassicHangman("Master", testGamesManager);

        }

        @Test
        void testPlayGameRookie() {

                testClassicHangmanRookie.setSecretWord("cat");
                assertEquals(testClassicHangmanRookie.getSecretWord(), "cat");

                testClassicHangmanRookie.guessLetter('c');
                testClassicHangmanRookie.guessLetter('a');
                testClassicHangmanRookie.guessLetter('t');

                assertTrue(testClassicHangmanRookie.isGameOver());

                testGamesManager.rookieWords = new String[]{"cat"};
                testGamesManager.noviceWords = new String[]{"dog"};
                testGamesManager.masterWords = new String[]{"elephant"};

                testClassicHangmanRookie.setGuessesLeft(0);
                
                assertTrue(testClassicHangmanRookie.isGameOver());
        }

        @Test
        void testPlayGameNovice() {

                testClassicHangmanNovice.setSecretWord("elephant");
                assertEquals(testClassicHangmanNovice.getSecretWord(), "elephant");

                testClassicHangmanNovice.guessLetter('a');
                testClassicHangmanNovice.guessLetter('b');
                testClassicHangmanNovice.guessLetter('c');
                testClassicHangmanNovice.guessLetter('d');
                testClassicHangmanNovice.guessLetter('f');
                testClassicHangmanNovice.guessLetter('g');
                testClassicHangmanNovice.guessLetter('i');
                testClassicHangmanNovice.guessLetter('x');


                assertTrue(testClassicHangmanNovice.isGameOver());
                assertNull(testClassicHangmanRookie.getResult());
                testGamesManager.rookieWords = new String[]{"cat"};
                testGamesManager.noviceWords = new String[]{"elephant"};
                testGamesManager.masterWords = new String[]{"phenomenon"};

                testClassicHangmanRookie.setGuessesLeft(0);
                
                assertTrue(testClassicHangmanRookie.isGameOver());
        }

        @Test
        void testPlayGameMaster() {

                testClassicHangmanMaster.setSecretWord("phenomenon");
                assertEquals(testClassicHangmanMaster.getSecretWord(), "phenomenon");

                testClassicHangmanMaster.guessLetter('p');
                testClassicHangmanMaster.guessLetter('h');
                testClassicHangmanMaster.guessLetter('e');
                testClassicHangmanMaster.guessLetter('n');
                testClassicHangmanMaster.guessLetter('o');
                
                assertFalse(testClassicHangmanMaster.isGameOver());

                testClassicHangmanMaster.guessLetter('m');

                assertTrue(testClassicHangmanMaster.isGameOver());

                testGamesManager.rookieWords = new String[]{"cat"};
                testGamesManager.noviceWords = new String[]{"dog"};
                testGamesManager.masterWords = new String[]{"elephant"};

                
                testClassicHangmanRookie.setSecretWord("ubiquotous");

                testClassicHangmanRookie.setGuessesLeft(0);
                
                assertTrue(testClassicHangmanRookie.isGameOver());
        }

        @Test
        void testGetHighScore() {
                Hangman hangman1 = new ClassicHangman("Novice", testGamesManager);
                hangman1.setScore(50);
                Hangman hangman2 = new ClassicHangman("Rookie", testGamesManager);
                hangman2.setScore(100);
                Hangman hangman3 = new ClassicHangman("Master", testGamesManager);
                hangman3.setScore(75);
                testDataHandler.getGamesPlayed().add(hangman1);
                testDataHandler.getGamesPlayed().add(hangman2);
                testDataHandler.getGamesPlayed().add(hangman3);

                assertEquals(100, testDataHandler.getHighScore());
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
