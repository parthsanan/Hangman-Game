package ui;

import exceptions.GuessedLetterException;
import model.ClassicHangman;
import model.GamesManager;
import model.Hangman;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GameWindow extends JFrame implements ActionListener {

    private Hangman currentGame;
    private GamesManager manager;

    private JLabel visibleWordLabel;
    private JLabel scoreLabel;
    private JLabel guessesLeftLabel;
    private JLabel responseLabel;
    private JLabel hangmanLabel;
    private JTextField guessTextField;
    private JButton submitGuessButton;

    // EFFECTS: Instantiates a new game window
    public GameWindow(GamesManager manager, String difficulty) {

        this.currentGame = new ClassicHangman(difficulty, manager);
        this.manager = manager;

        initializeFields();
    }

    // EFFECTS: handles all different types of button presses
    @Override
    public void actionPerformed(ActionEvent e) {
        if ((e.getSource() == submitGuessButton) || (e.getSource() == guessTextField)) {
            handleGuess();
        }
    }

    // EFFECTS: handles the guess input by the user
    private void handleGuess() {
        String strLetter = guessTextField.getText();

        if (strLetter.length() != 1) {
            responseLabel.setText("Incorrect Guess!");
        } else {
            char charLetter = strLetter.charAt(0);
            processGuess(charLetter);
        }
    }

    // EFFECTS: processes the guess input by the user
    private void processGuess(char guess) {
        try {
            Boolean guessCorrect = currentGame.checkGuessedLetter(guess);

            if (guessCorrect) {
                responseLabel.setText("Correct Guess!");
            } else {
                guessesLeftLabel.setText("Guesses Left: " + currentGame.getGuessesLeft());
                responseLabel.setText("Incorrect Guess!");
                updateHangmanImage();
            }

        } catch (GuessedLetterException guessedLetterException) {
            JOptionPane.showMessageDialog(null, "You already guessed that letter!");
        }

        updateUI();
        checkGameOver();
    }

    // EFFECTS: updates the hangman image based on the number of guesses left
    private void updateHangmanImage() {
        int guessesLeft = currentGame.getGuessesLeft();
        String imagePath = "src\\main\\assets\\images\\hangman" + (6 - guessesLeft) + ".jpg";
        hangmanLabel.setIcon(new ImageIcon(imagePath));
    }

    // EFFECTS: updates the UI based on the current game state
    private void updateUI() {
        guessTextField.setText("");
        visibleWordLabel.setText("Word: " + currentGame.getVisibleWord());
        scoreLabel.setText("Score: " + currentGame.getScore());
    }

    // EFFECTS: checks if the game is over and displays the appropriate message
    private void checkGameOver() {
        if (currentGame.isGameOver()) {
            if (currentGame.isGameWon()) {
                currentGame.setScore(currentGame.getScore() + 100);
                scoreLabel.setText("Score: " + currentGame.getScore());
                JOptionPane.showMessageDialog(null, "Game Over! You won!");
            } else {
                JOptionPane.showMessageDialog(null,
                        "Game Over! You lost!\n" + "The word was: " + currentGame.getSecretWord());
            }

            promptToSaveGame();
            closeWindow();
            new StartGUI(manager);
        }
    }

    // EFFECTS: prompts the user to save the game
    private void promptToSaveGame() {
        int response = JOptionPane.showConfirmDialog(this, "Do you want to save the game?", "Save Game",
                JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            try {
                manager.getDataHandler().saveGame(currentGame);
                JOptionPane.showMessageDialog(null, "Game Saved!");
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(null, "Error while saving game!");
            }
        }
    }

    // EFFECTS: closes the current window
    private void closeWindow() {
        this.dispose();
    }

    // EFFECTS: initialize components of the GUI
    @SuppressWarnings({ "checkstyle:MethodLength", "checkstyle:SuppressWarnings" })
    public void initializeFields() {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.setLayout(null);

        ImageIcon hangmanImage = new ImageIcon("src\\main\\assets\\images\\hangmanStart.jpg");
        hangmanLabel = new JLabel(hangmanImage);
        hangmanLabel.setBounds(350, 50, 450, 400);
        this.add(hangmanLabel, BorderLayout.EAST);

        visibleWordLabel = new JLabel("Word: " + currentGame.getVisibleWord());
        visibleWordLabel.setBounds(50, 50, 350, 50);
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
        guessTextField.addActionListener(this);
        this.add(guessTextField);

        submitGuessButton = new JButton("Submit Guess");
        submitGuessButton.setBounds(50, 150, 200, 50);
        submitGuessButton.addActionListener(this);
        this.add(submitGuessButton);
    }

}