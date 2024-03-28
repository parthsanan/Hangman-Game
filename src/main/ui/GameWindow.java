package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Exceptions.GuessedLetterException;
import model.*;

public class GameWindow extends JFrame implements ActionListener {

    private Hangman currentGame;

    private JLabel visibleWordLabel;
    private JLabel scoreLabel;
    private JLabel guessesLeftLabel;
    private JLabel responseLabel;
    private JTextField guessTextField;
    private JButton submitGuessButton;
    private GamesManager manager;

    public GameWindow(GamesManager manager, String difficulty) {

        this.currentGame = new ClassicHangman(difficulty, manager);
        this.manager = manager;

        initializeFields();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == submitGuessButton) {

            String strLetter = guessTextField.getText();
            char charLetter = strLetter.charAt(0);

            try {

                Boolean guessCorrect = currentGame.checkGuessedLetter(charLetter);

                if (guessCorrect) {

                    responseLabel.setText("Correct Guess!");

                } else {

                    responseLabel.setText("Incorrect Guess!");
                    guessesLeftLabel.setText("Guesses Left: " + (currentGame.getGuessesLeft()));

                }

            } catch (GuessedLetterException ex) {

                JOptionPane.showMessageDialog(null, "You already guessed that letter!");

            }

            guessTextField.setText("");
        }

        if (currentGame.isGameOver()) {

            if (currentGame.isGameWon()) {

                JOptionPane.showMessageDialog(null, "Game Over!" + " You won!");
                currentGame.setResult("Won");

            } else {

                JOptionPane.showMessageDialog(null, "Game Over!" + " You lost!");
                currentGame.setResult("Lost");

            }

            int response = JOptionPane.showConfirmDialog(
                    this, "Do you want to save the game?", "Save Game",
                    JOptionPane.YES_NO_OPTION);

            if (response == JOptionPane.YES_OPTION) {
                manager.getDataHandler().saveGame(currentGame);
                JOptionPane.showMessageDialog(null, "Game Saved!");
            }

            this.dispose();
            new GameGUI(manager);

        }

        visibleWordLabel.setText("Word: " + currentGame.getVisibleWord());
        scoreLabel.setText("Score: " + currentGame.getScore());

    }

    public void initializeFields() {
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        visibleWordLabel = new JLabel("Word" + currentGame.getVisibleWord() + currentGame.getSecretWord());
        visibleWordLabel.setBounds(50, 50, 200, 50);
        this.add(visibleWordLabel);

        responseLabel = new JLabel("GUESS!");
        responseLabel.setBounds(50, 10, 200, 50);
        this.add(responseLabel);

        scoreLabel = new JLabel("Score: " + currentGame.getScore());
        scoreLabel.setBounds(50, 200, 200, 50);
        this.add(scoreLabel);

        guessesLeftLabel = new JLabel("Guesses Left: " + currentGame.getGuessesLeft());
        guessesLeftLabel.setBounds(50, 220, 200, 50);
        this.add(guessesLeftLabel);

        guessTextField = new JTextField();
        guessTextField.setBounds(50, 100, 200, 50);
        this.add(guessTextField);

        submitGuessButton = new JButton("Submit Guess");
        submitGuessButton.setBounds(50, 150, 200, 50);
        submitGuessButton.addActionListener(this);
        this.add(submitGuessButton);

        this.setLayout(null);
    }

}
