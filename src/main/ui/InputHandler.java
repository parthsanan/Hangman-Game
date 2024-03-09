package ui;

import model.ClassicHangman;
import model.GamesManager;
import model.Hangman;
import persistance.DataHandler;

import java.util.ArrayList;
import java.util.Scanner;

public class InputHandler {

    private String gameMode;
    private String variantMode;
    private String classicDifficulty;
    private static Scanner input = new Scanner(System.in);
    private DataHandler dataHandler;
    private GamesManager manager;

    public InputHandler(GamesManager manager) {
        this.manager = manager;
        this.dataHandler = new DataHandler();
    }

    // EFFECTS: prints out menu for history option
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void historyMenu(ArrayList<Hangman> loadedGames) {

        System.out.println("1. Load All Previous Games" + "\n");
        System.out.println("2. Game Archive (View All Games Played)" + "\n");
        System.out.println("3. Main Menu" + "\n");
        System.out.println("4. Quit" + "\n");
        System.out.print("-> ");
        String choice = input.next();

        System.out.println();

        switch (choice) {

            case "1":
                dataHandler.loadGames(manager);
                break;

            case "2":

                if (manager.getLoadedGames().size() == 0) {

                    System.out.println("No Games Loaded!" + "\n");

                } else {

                    System.out.println("All time high score: " + dataHandler.getHighScore() + "\n");
                    System.out.println("Game History:" + "\n");

                    for (Hangman game : manager.getLoadedGames()) {

                        System.out.println("===========================");
                        System.out.println("Result: " + game.getResult() + "\n");
                        System.out.println("Mode: " + game.getMode() + "\n");
                        System.out.println("Difficulty: " + game.getDifficulty() + "\n");
                        System.out.println("Score: " + game.getScore() + "\n");
                        System.out.println("Guesses Left: " + game.getGuessesLeft() + "\n");
                        System.out.println("Secret Word: " + game.getSecretWord());
                        System.out.println("===========================");
                        System.out.println();
                    }

                }

                break;

            case "3":
                manager.getMenu(this);
                break;

            case "4":
                System.out.println("Bye!");
                System.exit(0);

            default:
                break;

        }

        historyMenu(manager.getLoadedGames());

    }

    // EFFECTS: Passes user input guess to check if correct
    public void getGuess(ClassicHangman game) {

        System.out.println("Word: " + game.getVisibleWord());
        System.out.print("Enter a letter: ");
        String strLetter = input.nextLine();

        while (true) {

            if (strLetter.matches("[A-Za-z]+")) {

                char charLetter = strLetter.charAt(0);

                game.guessLetter(charLetter);

            } else {

                System.out.println("Invalid Input" + "\n");

            }

            break;
        }

    }

    // EFFECTS: Check if game is over
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void gameResult(ClassicHangman game) {

        if (game.getVisibleWord().equals(game.getSecretWord())) {

            game.setScore(game.getScore() + 100);

            System.out.println("Congratulations! You WON!");
            System.out.println("Score: " + game.getScore());
            game.setResult("Won");

        } else {

            System.out.println("Sorry, you LOST, the word was: " + game.getSecretWord());
            System.out.println("Score: " + game.getScore());
            game.setResult("Lost");

        }

        System.out.println();
        System.out.println("Do you want to save the game?");
        System.out.println("1 -> Yes");
        System.out.println("2 -> No");

        String choice = input.next();

        switch (choice) {
            case "1":
                dataHandler.saveGame(game);
                System.out.println("Game Saved!" + "\n");
                break;

            case "2":
                System.out.println("Game Not Saved!" + "\n");

            default:
                break;
        }

        manager.getMenu(this);
    }

    // REQUIRES: choice is int
    // MODIFIES: gameMode
    // EFFECTS: sets game mode basis user input.
    public String chooseMode() {

        System.out.println("Game Mode:" + "\n");
        System.out.println("1. Classic" + "\n");
        System.out.println("2. Variant" + "\n");
        System.out.println("3. History" + "\n");
        System.out.println("4. Quit" + "\n");
        System.out.print("-> ");

        int choice = input.nextInt();

        if (choice == 1) {

            setGameMode("Classic");

        } else if (choice == 2) {

            setGameMode("Variant");

        } else if (choice == 3) {

            setGameMode("History");

        } else if (choice == 4) {

            System.out.println();
            System.out.println("Bye!");
            System.exit(0);

        } else {

            input.nextLine();
            System.out.println("Invalid Input!");

        }

        System.out.println("You chose: " + getGameMode() + "\n");

        return getGameMode();

    }

    // MODIFIES: difficulty
    // EFFECTS: sets difficulty basis user input.
    public String chooseClassicDifficulty() {

        System.out.println("Difficulty:" + "\n");
        System.out.println("1. Rookie" + "\n");
        System.out.println("2. Novice" + "\n");
        System.out.println("3. Master" + "\n");
        System.out.print("-> ");

        int choice = input.nextInt();

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
    public String chooseVariantMode() {

        System.out.println("Choose Variant:" + "\n");
        System.out.println("1. Timed" + "\n");
        System.out.println("2. Survival" + "\n");
        System.out.print("-> ");

        int choice = input.nextInt();

        if (choice == 1) {

            setVariantMode("Timed");

        } else if (choice == 2) {

            setVariantMode("Survival");

        }

        System.out.println("You chose: " + getVariantMode() + "\n");

        return getVariantMode();
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public String getGameMode() {
        return gameMode;
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
