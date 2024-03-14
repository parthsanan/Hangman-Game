package ui;

import model.GamesManager;

// EFFECTS: Class that starts the game
public class Main {

    public static void main(String[] args) {

        DrawHangman drawer = new DrawHangman();
        GamesManager manager = new GamesManager();
        InputHandler inputHandler = new InputHandler(manager);

        drawer.startImage();
        manager.getMenu(inputHandler);



    }
}
