package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ClassicHangman {

    private String difficulty;
    private String secretWord;
    private int guessesLeft;
    private int score;
    private List guessedLetters;


    public ClassicHangman(String difficulty) {

        this.difficulty = difficulty;
        this.guessesLeft = 7;
        this.score = 0;
        this.guessedLetters = new ArrayList<String>();
        this.secretWord = assignSecretWord();

        playGame(secretWord);

    }

    public void playGame(String secretWord) {

        Scanner input = new Scanner(System.in);

        System.out.println("Start guessing!");

        while (!isGameOver()) {

            System.out.println("Word: " + getVisibleWord());
            System.out.println("Enter a letter: ");

            String strLetter = input.nextLine();

            while (true) {

                if (strLetter.matches("[A-Za-z]+")) {

                    char charLetter = strLetter.charAt(0);

                    guessLetter(charLetter);
                    new DrawHangman(guessesLeft);

                } else {

                    System.out.println("Invalid Input" + "\n");

                }

                break;
            }
        }

        if (getVisibleWord().equals(secretWord)) {

            setScore(getScore() + 100);

            System.out.println("Congratulations! You WON!" + "\n" + "Score: " + getScore());

        } else {

            System.out.println("Sorry, you LOST, the word was: " + secretWord);

        }

    }

    public String getVisibleWord() {

        StringBuilder visibleWordBuilder = new StringBuilder();

        for (char letter : secretWord.toCharArray()) {

            if (guessedLetters.contains(letter)) {

                visibleWordBuilder.append(letter);

            } else {

                visibleWordBuilder.append('_');

            }
        }

        return visibleWordBuilder.toString();
    }


    @SuppressWarnings({"checkstyle:EmptyBlock", "checkstyle:SuppressWarnings"})
    public void guessLetter(char letter) {

        letter = Character.toLowerCase(letter);

        if (guessedLetters.contains(letter)) {

            System.out.println("\n" + "You've already guessed that letter.");

        } else {

            guessedLetters.add(letter);

            if (secretWord.contains(Character.toString(letter))) {

                setScore(getScore() + 10);

                System.out.println("\n" + "Correct guess!");

            } else {

                guessesLeft--;

                System.out.println("\n" + "Incorrect guess!");

            }

        }

        System.out.println("Attempts left: " + guessesLeft);
        System.out.println("Score: " + score);
        System.out.println("");

        isGameOver();

    }

    public String assignSecretWord() {
        // set word depending on difficulty

        String[] rookieWords = {"apple", "banana", "cat", "dog", "fish", "bird", "tree",
                "sun", "moon", "star", "car", "house", "flower", "book", "chair"};
        String[] noviceWords = {"elephant", "giraffe", "lion", "monkey", "tiger", "zebra",
                "kangaroo", "snake", "rabbit", "turtle", "pizza", "guitar", "computer",
                "soccer", "globe"};
        String[] masterWords = {"phenomenon", "onomatopoeia", "ubiquitous", "serendipity",
                "juxtaposition", "paradox", "synergy", "algorithm", "quantum", "holography",
                "architecture", "surreptitious", "chiaroscuro", "mnemonic", "polyglot"};

        switch (getDifficulty()) {

            case "Novice":
                return chooseRandom(noviceWords);

            case "Rookie":
                return chooseRandom(rookieWords);

            case "Master":
                return chooseRandom(masterWords);

            default:
                return null;

        }

    }

    public String chooseRandom(String[] array) {

        Random generator = new Random();

        return array[generator.nextInt(array.length)];

    }

    public boolean isGameOver() {

        return guessesLeft <= 0 || getVisibleWord().equals(secretWord);

    }

    public String getDifficulty() {
        return difficulty;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
