package ui;

import javax.swing.*;

import model.GamesManager;

// EFFECTS: Class that starts the game
public class Main extends JFrame {

    public static void main(String[] args) {

        new GameGUI(new GamesManager());

    }
}
