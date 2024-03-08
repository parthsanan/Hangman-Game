package model;

import persistance.DataHandler;
import ui.InputHandler;

import java.util.ArrayList;

public class GamesManager {

    protected ArrayList<Hangman> gamesPlayed;
    protected InputHandler inputHandler;
    protected DataHandler dataHandler;
    protected Hangman currentGame;

    protected String[] rookieWords;
    protected String[] noviceWords;
    protected String[] masterWords;

    public GamesManager() {
        this.inputHandler = new InputHandler();
        this.dataHandler = new DataHandler();

        this.rookieWords = new String[] {
                "apple", "banana", "cat", "dog", "fish", "bird", "tree", "sun", "moon", "star", "car", "house",
                "flower", "book", "chair", "piano", "chair", "couch", "table", "lamp", "phone", "mouse", "pen",
                "paper", "shirt", "socks", "pants", "shoes", "glove", "hat", "coat"
        };
        this.noviceWords = new String[] {
                "elephant", "giraffe", "lion", "monkey", "tiger", "zebra", "kangaroo", "snake", "rabbit", "turtle",
                "pizza", "guitar", "computer", "soccer", "globe", "octopus", "toucan", "rhinoceros", "crocodile",
                "hippopotamus", "chimpanzee", "penguin", "dolphin", "parrot", "jaguar",
                "chameleon", "platypus", "flamingo", "cheetah", "ostrich", "orangutan"
        };
        this.masterWords = new String[] {
                "phenomenon", "onomatopoeia", "ubiquitous", "serendipity",
                "juxtaposition", "paradox", "synergy", "algorithm", "quantum", "holography",
                "architecture", "surreptitious", "chiaroscuro", "mnemonic", "polyglot",
                "acquiesce", "allegiance", "circumvent", "perplexity", "benevolence",
                "procrastinate", "capitulate", "cacophony", "ubiquitous", "narcissist",
                "ambiguity", "loquacious", "obfuscate", "exacerbate", "idiosyncratic",
                "paragon", "quintessential", "serendipity", "vernacular", "antagonist"
        };
    }

    public void getMenu() {

        String mode = inputHandler.chooseMode();

        if (mode.equals("Classic") || mode.equals("Variant")) {

            switch (mode) {

                case "Classic":
                    currentGame = new ClassicHangman(inputHandler.chooseClassicDifficulty());
                    break;

                case "Variant":
                    currentGame = new VariantHangman(inputHandler.chooseVariantMode());
                    break;

            }

            currentGame.playGame();

        } else if (mode.equals("History")) {

            inputHandler.historyMenu(dataHandler.getGamesPlayed());

        }

    }
}
