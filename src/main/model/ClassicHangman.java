package model;

// EFFECTS: Hangman subclass for Classic Mode
public class ClassicHangman extends Hangman {

    public ClassicHangman(String difficulty, GamesManager manager) {

        super(difficulty, manager);
        setMode("Classic");
    }

}