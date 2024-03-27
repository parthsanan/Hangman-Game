package model;

import ui.DrawHangman;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Hangman {

    private String mode;
    private String result;
    private String difficulty;
    private String secretWord;
    private String visibleWord;
    private int guessesLeft;
    private int score;
    private List<Character> guessedLetters;

    public Hangman(String difficulty, GamesManager manager) {

        this.difficulty = difficulty;   
        this.guessesLeft = 7;
        this.score = 0;
        this.guessedLetters = new ArrayList<Character>();
        this.secretWord = chooseSecretWord(manager, getDifficulty());

    }

    // EFFECTS: Check if secret word contains letter entered by user
    public void guessLetter(char letter) {

        DrawHangman drawer = new DrawHangman();

        letter = Character.toLowerCase(letter);

        if (getGuessedLetters().contains(letter)) {

            System.out.println("\n" + "You've already guessed that letter.");

        } else {

            getGuessedLetters().add(letter);

            if (secretWord.contains(Character.toString(letter))) {

                setScore(getScore() + 10);

                System.out.println("\n" + "Correct guess!");

            } else {

                setGuessesLeft(getGuessesLeft() - 1);

                System.out.println("\n" + "Incorrect guess!");

            }

        }

        System.out.println("");

        drawer.drawFigure(getGuessesLeft());

        System.out.println("Attempts left: " + getGuessesLeft());
        System.out.println("Score: " + getScore());
        System.out.println("");

        isGameOver();

    }

    // MODIFIES: visibleWordBuilder
    // EFFECTS: Returns word visible on terminal
    public String getVisibleWord() {

        StringBuilder visibleWordBuilder = new StringBuilder();

        for (char letter : secretWord.toCharArray()) {

            if (guessedLetters.contains(letter)) {

                visibleWordBuilder.append(letter);

            } else {

                visibleWordBuilder.append('_');

            }
        }

        visibleWord = visibleWordBuilder.toString();

        return visibleWord;
    }

    // REQUIRES: array.length > 0
    // EFFECTS: Choose random word from array
    public String chooseSecretWord(GamesManager manager, String difficulty) {

        String[] array = new String[]{};

        switch (getDifficulty()) {
            case "Master":
                array = manager.getMasterWords();
                break;

            case "Novice":
                array = manager.getNoviceWords();
                break;

            case "Rookie":
                array = manager.getRookieWords();
                break;
        }

        Random generator = new Random();

        setSecretWord(array[generator.nextInt(array.length)]);

        return getSecretWord();

    }

    // EFFECTS: Return true if no guesses left (guessesLeft <= 0) || word guessed by
    // user.
    public boolean isGameOver() {

        return guessesLeft <= 0 || getVisibleWord().equals(secretWord);

    }

    public String getDifficulty() {
        return difficulty;
    }

    public List<Character> getGuessedLetters() {
        return guessedLetters;
    }

    public void setGuessedLetters(List<Character> guessedLetters) {
        this.guessedLetters = guessedLetters;
    }

    public String getSecretWord() {
        return secretWord;
    }

    public void setSecretWord(String secretWord) {
        this.secretWord = secretWord;
    }

    public int getGuessesLeft() {
        return guessesLeft;
    }

    public void setGuessesLeft(int guessesLeft) {
        this.guessesLeft = guessesLeft;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return this.mode;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return this.result;
    }
}