package ui;

import exceptions.GuessedLetterException;
import model.ClassicHangman;
import model.GamesManager;
import model.Hangman;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame implements ActionListener {

    private Hangman currentGame;

    private JLabel visibleWordLabel;
    private JLabel scoreLabel;
    private JLabel guessesLeftLabel;
    private JLabel responseLabel;
    private JLabel hangmanLabel;
    private JTextField guessTextField;
    private JButton submitGuessButton;
    private GamesManager manager;

    public GameWindow(GamesManager manager, String difficulty) {

        this.currentGame = new ClassicHangman(difficulty, manager);
        this.manager = manager;

        initializeFields();
    }

    @SuppressWarnings({ "checkstyle:MethodLength", "checkstyle:SuppressWarnings" })
    @Override
    public void actionPerformed(ActionEvent e) {

        if ((e.getSource() == submitGuessButton) || (e.getSource() == guessTextField)) {

            String strLetter = guessTextField.getText();

            if (strLetter.length() != 1) {

                responseLabel.setText("Incorrect Guess!");

            } else {

                char charLetter = strLetter.charAt(0);

                try {

                    Boolean guessCorrect = currentGame.checkGuessedLetter(charLetter);

                    if (guessCorrect) {

                        responseLabel.setText("Correct Guess!");

                    } else {

                        responseLabel.setText("Incorrect Guess!");
                        guessesLeftLabel.setText("Guesses Left: " + (currentGame.getGuessesLeft()));

                        switch (currentGame.getGuessesLeft()) {
                            case 5:
                                hangmanLabel.setIcon(new ImageIcon("src\\main\\assets\\images\\hangman1.jpg"));
                                break;
                            case 4:
                                hangmanLabel.setIcon(new ImageIcon("src\\main\\assets\\images\\hangman2.jpg"));
                                break;
                            case 3:
                                hangmanLabel.setIcon(new ImageIcon("src\\main\\assets\\images\\hangman3.jpg"));
                                break;
                            case 2:
                                hangmanLabel.setIcon(new ImageIcon("src\\main\\assets\\images\\hangman4.jpg"));
                                break;
                            case 1:
                                hangmanLabel.setIcon(new ImageIcon("src\\main\\assets\\images\\hangman5.jpg"));
                                break;
                            case 0:
                                hangmanLabel.setIcon(new ImageIcon("src\\main\\assets\\images\\hangman6.jpg"));
                                break;

                            default:
                                break;
                        }

                    }

                } catch (GuessedLetterException guessedLetterException) {

                    JOptionPane.showMessageDialog(null, "You already guessed that letter!");

                }

                guessTextField.setText("");
            }

            visibleWordLabel.setText("Word: " + currentGame.getVisibleWord());
            scoreLabel.setText("Score: " + currentGame.getScore());

            if (currentGame.isGameOver()) {

                if (currentGame.isGameWon()) {

                    JOptionPane.showMessageDialog(null, "Game Over!" + " You won!");

                } else {

                    JOptionPane.showMessageDialog(
                            null, "Game Over!" + " You lost!" + "\n" + "The word was: "
                                    + currentGame.getSecretWord());

                }

                int response = JOptionPane.showConfirmDialog(
                        this, "Do you want to save the game?", "Save Game",
                        JOptionPane.YES_NO_OPTION);

                if (response == JOptionPane.YES_OPTION) {
                    manager.getDataHandler().saveGame(currentGame);
                    JOptionPane.showMessageDialog(null, "Game Saved!");
                }

                this.dispose();
                new StartGUI(manager);
            }

        }

    }

    @SuppressWarnings({ "checkstyle:MethodLength", "checkstyle:SuppressWarnings" })
    public void initializeFields() {
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.setLayout(null);

        ImageIcon hangmanImage = new ImageIcon("src\\main\\assets\\images\\hangmanStart.jpg");
        hangmanLabel = new JLabel(hangmanImage);
        hangmanLabel.setBounds(300, 50, 400, 400);
        add(hangmanLabel, BorderLayout.EAST);

        visibleWordLabel = new JLabel("Word: " + currentGame.getVisibleWord());
        visibleWordLabel.setBounds(50, 50, 500, 50);
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
