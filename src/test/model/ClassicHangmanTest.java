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
                
                testClassicHangmanRookie = new ClassicHangman("Rookie");
                testClassicHangmanNovice = new ClassicHangman("Novice");
                testClassicHangmanMaster = new ClassicHangman("Master");
                testGamesManager = new GamesManager();
                testInputHandler = new InputHandler(testGamesManager);
                testDataHandler = new DataHandler();

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
                
                testClassicHangmanRookie.playGame(testGamesManager);
                
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
                
                testClassicHangmanRookie.playGame(testGamesManager);
                
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
                
                testClassicHangmanRookie.playGame(testGamesManager);
                
                assertTrue(testClassicHangmanRookie.isGameOver());
        }

        @Test
        void testGetHighScore() {
                // Create some Hangman instances with different scores
                Hangman hangman1 = new ClassicHangman("Novice");
                hangman1.setScore(50);
                Hangman hangman2 = new ClassicHangman("Rookie");
                hangman2.setScore(100);
                Hangman hangman3 = new ClassicHangman("Master");
                hangman3.setScore(75);

                // Add the Hangman instances to the gamesPlayed list in dataHandler
                testDataHandler.getGamesPlayed().add(hangman1);
                testDataHandler.getGamesPlayed().add(hangman2);
                testDataHandler.getGamesPlayed().add(hangman3);

                // Ensure that the highest score is correctly returned
                assertEquals(100, testDataHandler.getHighScore());
                }

        @Test
        void testGetGamesPlayed() {
                // Create some Hangman instances
                Hangman hangman1 = new ClassicHangman("Novice");
                Hangman hangman2 = new ClassicHangman("Rookie");
                Hangman hangman3 = new ClassicHangman("Master");

                // Add the Hangman instances to the gamesPlayed list in testDataHandler
                testDataHandler.getGamesPlayed().add(hangman1);
                testDataHandler.getGamesPlayed().add(hangman2);
                testDataHandler.getGamesPlayed().add(hangman3);

                // Ensure that the gamesPlayed list is returned correctly
                assertEquals(3, testDataHandler.getGamesPlayed().size());
                assertTrue(testDataHandler.getGamesPlayed().contains(hangman1));
                assertTrue(testDataHandler.getGamesPlayed().contains(hangman2));
                assertTrue(testDataHandler.getGamesPlayed().contains(hangman3));
                }

        @Test
                void testSetFilePath() {
                // Set a new file path
                String newPath = "new/path/to/file.json";
                testDataHandler.setFilePath(newPath);

                // Ensure that the file path is set correctly
                assertEquals(newPath, testDataHandler.getFilePath());
        }
}
