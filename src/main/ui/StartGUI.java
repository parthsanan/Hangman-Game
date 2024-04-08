package ui;

import model.EventLog;
import model.GamesManager;
import model.Hangman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class StartGUI extends JFrame implements ActionListener {
    private JButton rookieButton;
    private JButton noviceButton;
    private JButton masterButton;
    private JButton loadAllButton;
    private JButton viewAllButton;
    private JButton filterByWordButton;

    private JLabel titleLabel;
    private JLabel chooseLevelLabel;
    private JLabel highScoreLabel;

    private GamesManager manager;

    public StartGUI(GamesManager manager) {
        super("Hangman Game");
        this.manager = manager;

        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel mainPanel = new JPanel(null);
        JPanel historyPanel = new JPanel(null);

        initializeButtons(mainPanel, historyPanel);
        initializeTabs(mainPanel, historyPanel, tabbedPane);
    }

    // EFFECTS: handles all behaviour related to buttons on the start GUI
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rookieButton) {
            startNewGame("Rookie");
        } else if (e.getSource() == noviceButton) {
            startNewGame("Novice");
        } else if (e.getSource() == masterButton) {
            startNewGame("Master");
        } else if (e.getSource() == viewAllButton) {
            viewAllGames();
        } else if (e.getSource() == loadAllButton) {
            loadAllGames();
        } else if (e.getSource() == filterByWordButton) {
            filterGamesByWord();
        }
    }

    // EFFECTS: starts a new game with the given difficulty
    private void startNewGame(String difficulty) {
        this.dispose();
        new GameWindow(manager, difficulty);
    }

    // EFFECTS: displays all games in a dialog
    private void viewAllGames() {
        if (manager.getLoadedGames().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No games to view");
        } else {
            displayGamesInDialog(manager.getLoadedGames());
        }
    }

    // EFFECTS: loads all games from the file
    private void loadAllGames() {
        try {
            manager.getDataHandler().loadGames();
            JOptionPane.showMessageDialog(null, manager.getLoadedGames().size() + " Games Loaded!");
            highScoreLabel.setText("High Score: " + manager.getDataHandler().getHighScore(manager));
        } catch (IOException ioException) {
            JOptionPane.showMessageDialog(null, "Error!");
        }
    }

    // EFFECTS: filters games by a word and displays them in a dialog
    private void filterGamesByWord() {
        String word = JOptionPane.showInputDialog("Enter word to filter by:");
        StringBuilder gameDetails = manager.getDataHandler().getGamesByWord(word);

        if (gameDetails.length() == 0) {
            JOptionPane.showMessageDialog(null, "No games found with word: " + word);

        } else {

            JTextArea textArea = new JTextArea(gameDetails.toString());
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(500, 500));
            JOptionPane.showMessageDialog(null, scrollPane, "Games with word: " + word,
                    JOptionPane.INFORMATION_MESSAGE);

        }
    }

    // EFFECTS: displays games in a dialog
    private void displayGamesInDialog(ArrayList<Hangman> games) {

        if (games.isEmpty()) {

            JOptionPane.showMessageDialog(null, "No games to view");

        } else {

            StringBuilder gameDetails = new StringBuilder();

            for (int i = 0; i < games.size(); i++) {

                gameDetails.append(games.get(i).toString()).append("\n");

            }

            JTextArea textArea = new JTextArea(gameDetails.toString());
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(500, 500));
            JOptionPane.showMessageDialog(null, scrollPane, "Game Archive", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // EFFECTS: initialize button component of the start GUI
    @SuppressWarnings({ "checkstyle:MethodLength", "checkstyle:SuppressWarnings" })
    public void initializeButtons(JPanel mainPanel, JPanel historyPanel) {
        mainPanel.setBackground(Color.BLACK);
        String textFont = "Monospaced";

        titleLabel = new JLabel("Hangman");
        titleLabel.setFont(new Font(textFont, Font.BOLD, 50));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBounds(500, 100, 500, 100);
        mainPanel.add(titleLabel, BorderLayout.CENTER);

        chooseLevelLabel = new JLabel("Difficulty Level:");
        chooseLevelLabel.setFont(new Font(textFont, Font.BOLD, 24));
        chooseLevelLabel.setForeground(Color.WHITE);
        chooseLevelLabel.setHorizontalAlignment(JLabel.CENTER);
        chooseLevelLabel.setBounds(500, 240, 500, 50);
        mainPanel.add(chooseLevelLabel);

        rookieButton = new JButton("Rookie");
        rookieButton.setFont(new Font(textFont, Font.BOLD, 17));
        rookieButton.addActionListener(this);
        rookieButton.setBounds(580, 300, 100, 50);
        mainPanel.add(rookieButton);

        noviceButton = new JButton("Novice");
        noviceButton.setFont(new Font(textFont, Font.BOLD, 17));
        noviceButton.addActionListener(this);
        noviceButton.setBounds(700, 300, 100, 50);
        mainPanel.add(noviceButton);

        masterButton = new JButton("Master");
        masterButton.setFont(new Font(textFont, Font.BOLD, 17));
        masterButton.addActionListener(this);
        masterButton.setBounds(820, 300, 100, 50);
        mainPanel.add(masterButton);

        loadAllButton = new JButton("Load Games");
        loadAllButton.setFont(new Font(textFont, Font.BOLD, 17));
        loadAllButton.addActionListener(this);
        loadAllButton.setBounds(50, 100, 150, 50);
        historyPanel.add(loadAllButton);

        viewAllButton = new JButton("View Games");
        viewAllButton.setFont(new Font(textFont, Font.BOLD, 17));
        viewAllButton.addActionListener(this);
        viewAllButton.setBounds(250, 100, 150, 50);
        historyPanel.add(viewAllButton);

        highScoreLabel = new JLabel("High Score: " + manager.getDataHandler().getHighScore(manager));
        highScoreLabel.setFont(new Font(textFont, Font.BOLD, 17));
        highScoreLabel.setForeground(Color.WHITE);
        highScoreLabel.setBounds(50, 200, 200, 50);
        historyPanel.add(highScoreLabel);

        filterByWordButton = new JButton("Filter Games");
        filterByWordButton.setFont(new Font(textFont, Font.BOLD, 17));
        filterByWordButton.addActionListener(this);
        filterByWordButton.setBounds(450, 100, 200, 50);
        historyPanel.add(filterByWordButton);

        masterButton.setBackground(Color.WHITE);
        loadAllButton.setBackground(Color.WHITE);
        viewAllButton.setBackground(Color.WHITE);

        historyPanel.setBackground(Color.BLACK);
    }

    // EFFECTS: initialize tab interface of the start GUI
    public void initializeTabs(JPanel mainPanel, JPanel historyPanel, JTabbedPane tabbedPane) {
        tabbedPane.addTab("Game", mainPanel);
        tabbedPane.addTab("History", historyPanel);

        this.add(tabbedPane);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                EventLog.getInstance().iterator().forEachRemaining(event -> System.out.println(event.toString()));
            }
        });

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
    }
}