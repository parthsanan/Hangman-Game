package ui;

import model.GamesManager;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGUI extends JFrame implements ActionListener {
    private JButton rookieButton;
    private JButton noviceButton;
    private JButton masterButton;
    private JButton loadAllButton;
    private JButton viewAllButton;
    private GamesManager manager;

    public GameGUI(GamesManager manager) {
        super("Hangman Game");
        this.manager = manager;

        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel mainPanel = new JPanel(null);
        JPanel historyPanel = new JPanel(null);

        initializeButtons(mainPanel, historyPanel);
        initializeTabs(mainPanel, historyPanel, tabbedPane);
    }

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

    public void initializeButtons(JPanel mainPanel, JPanel historyPanel) {
        rookieButton = new JButton("Rookie");
        rookieButton.addActionListener(this);
        rookieButton.setBounds(50, 300, 100, 50);
        mainPanel.add(rookieButton);

        noviceButton = new JButton("Novice");
        noviceButton.addActionListener(this);
        noviceButton.setBounds(200, 300, 100, 50);
        mainPanel.add(noviceButton);

        masterButton = new JButton("Master");
        masterButton.addActionListener(this);
        masterButton.setBounds(350, 300, 100, 50);
        mainPanel.add(masterButton);

        loadAllButton = new JButton("Load Games");
        loadAllButton.addActionListener(this);
        loadAllButton.setBounds(50, 100, 150, 50);
        historyPanel.add(loadAllButton);

        viewAllButton = new JButton("View Games");
        viewAllButton.addActionListener(this);
        viewAllButton.setBounds(250, 100, 150, 50);
        historyPanel.add(viewAllButton);
    }

    public void initializeTabs(JPanel mainPanel, JPanel historyPanel, JTabbedPane tabbedPane) {
        tabbedPane.addTab("Game", mainPanel);
        tabbedPane.addTab("History", historyPanel);

        this.add(tabbedPane);
        this.setSize(500, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}