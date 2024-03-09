package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ui.InputHandler;

import static org.junit.jupiter.api.Assertions.*;

public class ClassicHangmanTest {
        
        ClassicHangman testClassicHangmanRookie;
        ClassicHangman testClassicHangmanNovice;
        ClassicHangman testClassicHangmanMaster;
        GamesManager testGamesManager;
        InputHandler testInputHandler;

        @BeforeEach
        void setupClassicHangmanTest() {
                
                testClassicHangmanRookie = new ClassicHangman("Rookie");
                testClassicHangmanNovice = new ClassicHangman("Novice");
                testClassicHangmanMaster = new ClassicHangman("Master");
                testGamesManager = new GamesManager();
                testInputHandler = new InputHandler(testGamesManager);

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

                testClassicHangmanNovice.guessLetter('e');
                testClassicHangmanNovice.guessLetter('l');
                testClassicHangmanNovice.guessLetter('p');
                testClassicHangmanNovice.guessLetter('h');
                testClassicHangmanNovice.guessLetter('a');
                testClassicHangmanNovice.guessLetter('n');
                testClassicHangmanNovice.guessLetter('t');


                assertTrue(testClassicHangmanNovice.isGameOver());

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
}
