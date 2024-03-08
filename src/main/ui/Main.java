package ui;

import model.GamesManager;

public class Main {

    public static void main(String[] args) {

        DrawHangman drawer = new DrawHangman();
        GamesManager manager = new GamesManager();

        drawer.startImage();
        manager.getMenu();

    }
}
