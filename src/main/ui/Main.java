package ui;

import model.GamesManager;

// EFFECTS: Class that starts the game
public class Main {

    public static void main(String[] args) {

        new StartGUI(new GamesManager());

    }
}
