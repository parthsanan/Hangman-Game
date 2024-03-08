package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ui.InputHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        void testPlayGame() {

                testClassicHangmanRookie.setSecretWord("cat");
                assertEquals(testClassicHangmanRookie.getSecretWord(), "cat");

                testClassicHangmanRookie.guessLetter('c');
                testClassicHangmanRookie.guessLetter('a');
                testClassicHangmanRookie.guessLetter('t');

                assertTrue(testClassicHangmanRookie.isGameOver());
        }
}
