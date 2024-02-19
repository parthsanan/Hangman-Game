package model;

import ui.InputHandler;

import java.util.ArrayList;
import java.util.List;

public class HangmanGame {

    private List<Character> guessedLetters;

    public HangmanGame() {

        this.guessedLetters = new ArrayList<>();

        startGame();

    }

    public void startGame() {

        System.out.println("Welcome to Hangman: Remastered!" + "\n");

        new InputHandler();

    }
}
