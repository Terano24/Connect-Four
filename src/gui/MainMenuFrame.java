package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuFrame extends JFrame {
    
    private String loggedInUser;

    public MainMenuFrame(String username) {
        this.loggedInUser = username;
        setTitle("Connect Four - Main Menu");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        setLayout(new BorderLayout());
        
        // Header
        JLabel welcomeLabel = new JLabel("Welcome to Connect Four!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 22));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(welcomeLabel, BorderLayout.NORTH);
        
        // Buttons Panel
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 10, 15));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 30, 50));
        
        JButton playButton = new JButton("Play Game");
        JButton statsButton = new JButton("View Statistics");
        JButton leaderboardButton = new JButton("View Leaderboard");
        JButton exitButton = new JButton("Exit");
        
        // Add Action Listeners for routing
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SwingUtilities.invokeLater(() -> new GameFrame(loggedInUser).setVisible(true));
            }
        });
        
        statsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SwingUtilities.invokeLater(() -> new StatisticsFrame(loggedInUser).setVisible(true));
            }
        });
        
        leaderboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SwingUtilities.invokeLater(() -> new TopScorersFrame(loggedInUser).setVisible(true));
            }
        });
        
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        buttonPanel.add(playButton);
        buttonPanel.add(statsButton);
        buttonPanel.add(leaderboardButton);
        buttonPanel.add(exitButton);
        
        add(buttonPanel, BorderLayout.CENTER);
    }
}
