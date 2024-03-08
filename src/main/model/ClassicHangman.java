package model;

import ui.InputHandler;

public class ClassicHangman extends Hangman {

    public ClassicHangman(String difficulty) {

        super(difficulty);
        setMode("Classic");

    }

    @Override
    public void playGame(GamesManager gamesManager) {
        
        InputHandler inputHandler = new InputHandler(gamesManager);

        switch (getDifficulty()) {
            case "Master":
                chooseSecretWord(gamesManager.masterWords);
                break;

            case "Novice":
                chooseSecretWord(gamesManager.noviceWords);
                break;

            case "Rookie":
                chooseSecretWord(gamesManager.rookieWords);
                break;
        }

        while (!isGameOver()) {

            inputHandler.getGuess(this);

        }

        inputHandler.gameResult(this);
    }

}