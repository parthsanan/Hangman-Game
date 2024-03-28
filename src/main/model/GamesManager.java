package model;

import persistance.DataHandler;

import java.util.ArrayList;

// EFFECTS: Class handling logistics of all games 
public class GamesManager {

    protected DataHandler dataHandler;
    protected Hangman currentGame;    
    ArrayList<Hangman> loadedGames;

    protected String[] rookieWords;
    protected String[] noviceWords;
    protected String[] masterWords;

    public GamesManager() {
        this.dataHandler = new DataHandler();
        this.loadedGames = new ArrayList<>();

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

    public ArrayList<Hangman> getLoadedGames() {
        return this.loadedGames;
    }

    public void addToLoadedGames(Hangman game) {
        this.loadedGames.add(game);
    }

    public void setLoadedGames(ArrayList<Hangman> games) {
        this.loadedGames = games;
    }

    public String[] getRookieWords() {
        return rookieWords;
    }

    public String[] getNoviceWords() {
        return noviceWords;
    }

    public String[] getMasterWords() {
        return masterWords;
    }

    public DataHandler getDataHandler() {
        return this.dataHandler;
    }
}
