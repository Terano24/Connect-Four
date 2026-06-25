package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame {
    public GameFrame() {
        setTitle("Connect Four - Game Board");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        setLayout(new BorderLayout());
        
        JLabel placeholderLabel = new JLabel("4x4 Antigravity Board Will Go Here", SwingConstants.CENTER);
        placeholderLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(placeholderLabel, BorderLayout.CENTER);
        
        JButton backButton = new JButton("Back to Main Menu");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SwingUtilities.invokeLater(() -> new MainMenuFrame().setVisible(true));
            }
        });
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
