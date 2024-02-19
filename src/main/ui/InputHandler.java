package ui;

import model.ClassicHangman;

import java.util.Scanner;

public class InputHandler {

    private int choice;
    private int score;
    private String gameMode;
    private String variantMode;
    private String classicDifficulty;
    private Scanner input = new Scanner(System.in);
    private ClassicHangman classicHangman;

    public InputHandler() {

        this.score = 0;

        System.out.println("Welcome to Hangman: Remastered!" + "\n");

        chooseMode();

        if (gameMode == "Classic") {

            classicHangman = new ClassicHangman(chooseClassicDifficulty());
            playGame(classicHangman.getSecretWord());

        } else if (gameMode == "Variant") {

            chooseVariantMode();

        }

    }

    // REQUIRES: choice is int
    // MODIFIES: gameMode
    // EFFECTS: sets game mode basis user input.
    public void chooseMode() {

        System.out.println("Game Mode:" + "\n");
        System.out.println("1. Classic" + "\n");
        System.out.println("2. Variant" + "\n");

        choice = input.nextInt();

        if (choice == 1) {

            setGameMode("Classic");

        } else if (choice == 2) {

            setGameMode("Variant");

        } else {

            input.nextLine();
            System.out.println("Invalid Input!");

        }

        System.out.println("You chose: " + getGameMode() + "\n");

    }


    // EFFECTS: Check after guess if game is over
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void playGame(String secretWord) {

        Scanner input = new Scanner(System.in);

        System.out.println("Start guessing!");

        while (!classicHangman.isGameOver()) {

            System.out.println("Word: " + classicHangman.getVisibleWord());
            System.out.println("Enter a letter: ");

            String strLetter = input.nextLine();

            while (true) {

                if (strLetter.matches("[A-Za-z]+")) {

                    char charLetter = strLetter.charAt(0);

                    guessLetter(charLetter);
                    new DrawHangman(classicHangman.getGuessesLeft());

                } else {

                    System.out.println("Invalid Input" + "\n");

                }

                break;
            }
        }

        if (this.classicHangman.getVisibleWord().equals(secretWord)) {

            setScore(getScore() + 100);

            System.out.println("Congratulations! You WON!");
            System.out.println("Score: " + getScore());

        } else {

            System.out.println("Sorry, you LOST, the word was: " + secretWord);
            System.out.println("Score: " + getScore());

        }

    }

    // EFFECTS: Check if secret word contains letter entered by user
    @SuppressWarnings({ "checkstyle:EmptyBlock", "checkstyle:SuppressWarnings" })
    public void guessLetter(char letter) {

        letter = Character.toLowerCase(letter);

        if (classicHangman.getGuessedLetters().contains(letter)) {

            System.out.println("\n" + "You've already guessed that letter.");

        } else {

            classicHangman.getGuessedLetters().add(letter);

            if (classicHangman.getSecretWord().contains(Character.toString(letter))) {

                setScore(getScore() + 10);

                System.out.println("\n" + "Correct guess!");

            } else {

                classicHangman.setGuessesLeft(classicHangman.getGuessesLeft() - 1);

                System.out.println("\n" + "Incorrect guess!");

            }

        }

        System.out.println("Attempts left: " + classicHangman.getGuessesLeft());
        System.out.println("Score: " + getScore());
        System.out.println("");

        classicHangman.isGameOver();

    }

    // MODIFIES: difficulty
    // EFFECTS: sets difficulty basis user input.
    @SuppressWarnings({ "checkstyle:MethodLength", "checkstyle:SuppressWarnings" })
    public String chooseClassicDifficulty() {

        System.out.println("Difficulty:" + "\n");
        System.out.println("1. Rookie" + "\n");
        System.out.println("2. Novice" + "\n");
        System.out.println("3. Master" + "\n");

        choice = input.nextInt();

        if (choice == 1) {

            setClassicDifficulty("Rookie");

        } else if (choice == 2) {

            setClassicDifficulty("Novice");

        } else if (choice == 3) {

            setClassicDifficulty("Master");

        }

        System.out.println("You chose: " + getClassicDifficulty() + "\n");
        return getClassicDifficulty();

    }

    // MODIFIES: variantMode
    // EFFECTS: sets variant mode basis user input.
    public void chooseVariantMode() {

        System.out.println("Choose Variant:" + "\n");
        System.out.println("1. Timed" + "\n");
        System.out.println("2. Survival" + "\n");

        choice = input.nextInt();

        if (choice == 1) {

            setVariantMode("Timed");

        } else if (choice == 2) {

            setVariantMode("Survival");

        }

        System.out.println("You chose: " + getVariantMode() + "\n");

    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return this.score;
    }

    public String getClassicDifficulty() {
        return this.classicDifficulty;
    }

    public void setClassicDifficulty(String classicDifficulty) {
        this.classicDifficulty = classicDifficulty;
    }

    public String getVariantMode() {
        return variantMode;
    }

    public void setVariantMode(String variantMode) {
        this.variantMode = variantMode;
    }
}
