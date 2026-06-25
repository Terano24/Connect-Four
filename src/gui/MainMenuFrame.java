package gui;

import javax.swing.*;
import java.awt.*;

public class MainMenuFrame extends JFrame {

    public MainMenuFrame() {
        setTitle("Connect Four - Main Menu");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        setLayout(new BorderLayout());
        
        JLabel welcomeLabel = new JLabel("Welcome to Connect Four!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(welcomeLabel, BorderLayout.CENTER);
        
        // Placeholder for future buttons (Play, Statistics, Top Scorers, Exit)
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(new JLabel("(Buttons will be added later)"));
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
