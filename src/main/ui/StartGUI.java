package ui;

import model.GamesManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartGUI extends JFrame implements ActionListener {
    private JButton rookieButton;
    private JButton noviceButton;
    private JButton masterButton;
    private JButton loadAllButton;
    private JButton viewAllButton;

    private JLabel titleLabel;
    private JLabel chooseLevelLabel;
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

    @SuppressWarnings({ "checkstyle:MethodLength", "checkstyle:SuppressWarnings" })
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == rookieButton) {

            this.dispose();
            new GameWindow(manager, "Rookie");

        } else if (e.getSource() == noviceButton) {

            this.dispose();
            new GameWindow(manager, "Novice");

        } else if (e.getSource() == masterButton) {

            this.dispose();
            new GameWindow(manager, "Master");

        } else if (e.getSource() == viewAllButton) {

            if (manager.getLoadedGames().size() == 0) {

                JOptionPane.showMessageDialog(null, "No games to view");

            } else {

                StringBuilder gameDetails = new StringBuilder();

                for (int i = 0; i < manager.getLoadedGames().size(); i++) {

                    gameDetails.append(manager.getLoadedGames().get(i).toString()).append("\n");

                }

                JTextArea textArea = new JTextArea(gameDetails.toString());
                textArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setPreferredSize(new Dimension(500, 500));
                JOptionPane.showMessageDialog(null, scrollPane, "Game Archive", JOptionPane.INFORMATION_MESSAGE);
            }

        } else if (e.getSource() == loadAllButton) {

            manager.getDataHandler().loadGames(manager);
            JOptionPane.showMessageDialog(null, manager.getLoadedGames().size() + " Games Loaded!");

        }
    }

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
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

        masterButton.setBackground(Color.WHITE);
        loadAllButton.setBackground(Color.WHITE);
        viewAllButton.setBackground(Color.WHITE);

        historyPanel.setBackground(Color.BLACK);
    }

    public void initializeTabs(JPanel mainPanel, JPanel historyPanel, JTabbedPane tabbedPane) {
        tabbedPane.addTab("Game", mainPanel);
        tabbedPane.addTab("History", historyPanel);

        this.add(tabbedPane);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
    }
}