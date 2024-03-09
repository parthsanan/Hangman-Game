package model;

// Hangman subclass with Variant mode
public class VariantHangman extends Hangman {

    public VariantHangman(String difficulty) {
        super(difficulty);
        setMode("Variant");
    }

    @Override
    public void playGame(GamesManager manager) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'playGame'");
    }

}
