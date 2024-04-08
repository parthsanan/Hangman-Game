package model;

import exceptions.GuessedLetterException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Hangman {

    private String result;
    private String difficulty;
    private String secretWord;
    private String visibleWord;
    private int guessesLeft;
    private int score;
    private List<Character> guessedLetters;

    public Hangman(String difficulty, GamesManager manager) {

        this.difficulty = difficulty;
        this.guessesLeft = 6;
        this.score = 0;
        this.guessedLetters = new ArrayList<Character>();
        this.secretWord = chooseSecretWord(manager, getDifficulty());

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
                visibleWordBuilder.append(' ');

            }
        }

        visibleWord = visibleWordBuilder.toString();

        return visibleWord;
    }

    // REQUIRES: array.length > 0
    // EFFECTS: Choose random word from array
    public String chooseSecretWord(GamesManager manager, String difficulty) {

        String[] array = new String[] {};

        if (difficulty == "Master") {

            array = manager.getMasterWords();

        } else if (difficulty == "Novice") {

            array = manager.getNoviceWords();

        } else if (difficulty == "Rookie") {

            array = manager.getRookieWords();

        }

        if (array.length > 0) {

            Random generator = new Random();

            int index = generator.nextInt(array.length);

            setSecretWord(array[index]);

            return getSecretWord();

        } else {

            return "Zero";

        }

    }

    // EFFECTS: Return true if no guesses left (guessesLeft <= 0) || word guessed by
    // user.
    public boolean isGameOver() {

        String wordGuessed = getVisibleWord().replaceAll("\\s", "");

        if (guessesLeft <= 0 || wordGuessed.equals(secretWord)) {

            if (getVisibleWord().equals(secretWord)) {

                setResult("Won");

            } else {

                setResult("Lost");
            }

        }

        return guessesLeft <= 0 || getVisibleWord().equals(secretWord);

    }

    // EFFECTS: Return true if word guessed by user
    public boolean isGameWon() {

        return getVisibleWord().equals(secretWord);

    }

    // EFFECTS: Check if secret word contains letter entered by user
    public boolean checkGuessedLetter(char letter) throws GuessedLetterException {

        letter = Character.toLowerCase(letter);

        if (getGuessedLetters().contains(letter)) {

            throw new GuessedLetterException();

        } else {

            getGuessedLetters().add(letter);

            if (secretWord.contains(Character.toString(letter))) {

                setScore(getScore() + 10);

                return true;

            } else {

                setGuessesLeft(getGuessesLeft() - 1);

            }

        }

        return false;

    }

    // EFFECTS: Return modified string representation of Hangman object
    @Override
    public String toString() {
        return "Result: " + getResult() + "\n" + "Difficulty: " + getDifficulty() + "\n"
                + "Secret Word: " + getSecretWord() + "\n"
                + "Guesses Left: " + getGuessesLeft() + "\n"
                + "Score: " + getScore() + "\n";
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

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return this.result;
    }
}