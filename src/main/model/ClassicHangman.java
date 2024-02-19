package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClassicHangman {

    private String difficulty;
    private String secretWord;
    private String visibleWord;
    private int guessesLeft;
    private List<Character> guessedLetters;

    public ClassicHangman(String difficulty) {

        this.difficulty = difficulty;
        this.guessesLeft = 7;
        this.guessedLetters = new ArrayList<Character>();

        this.secretWord = assignSecretWord();

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

    // REQUIRES: getDifficulty() != null
    // MODIFIES: secretWord
    // EFFECTS: Set secret word depending on getDifficulty()
    public String assignSecretWord() {

        String[] rookieWords = { "apple", "banana", "cat", "dog", "fish", "bird", "tree",
                "sun", "moon", "star", "car", "house", "flower", "book", "chair" };
        String[] noviceWords = { "elephant", "giraffe", "lion", "monkey", "tiger", "zebra",
                "kangaroo", "snake", "rabbit", "turtle", "pizza", "guitar", "computer",
                "soccer", "globe" };
        String[] masterWords = { "phenomenon", "onomatopoeia", "ubiquitous", "serendipity",
                "juxtaposition", "paradox", "synergy", "algorithm", "quantum", "holography",
                "architecture", "surreptitious", "chiaroscuro", "mnemonic", "polyglot" };

        switch (getDifficulty()) {

            case "Master":
                return chooseRandom(masterWords);

            case "Novice":
                return chooseRandom(noviceWords);

            case "Rookie":
                return chooseRandom(rookieWords);

        }

        return null;
    }

    // REQUIRES: array.length > 0
    // EFFECTS: Choose random word from array
    public String chooseRandom(String[] array) {

        Random generator = new Random();

        return array[generator.nextInt(array.length)];

    }

    // EFFECTS: Return true if no guesses left (guessesLeft <= 0) || word guessed by
    // user.
    public boolean isGameOver() {

        return guessesLeft <= 0 || getVisibleWord().equals(secretWord);

    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {

        this.difficulty = difficulty;

    }

    public List<Character> getGuessedLetters() {
        return guessedLetters;
    }

    public String getSecretWord() {
        return secretWord;
    }

    public int getGuessesLeft() {
        return guessesLeft;
    }

    public void setGuessesLeft(int guessesLeft) {
        this.guessesLeft = guessesLeft;
    }
}
