package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatisticsFrame extends JFrame {
    private String loggedInUser;
    
    public StatisticsFrame(String username) {
        this.loggedInUser = username;
        setTitle("Connect Four - My Statistics");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        setLayout(new BorderLayout());
        
        JLabel placeholderLabel = new JLabel("Personal Statistics Will Go Here", SwingConstants.CENTER);
        placeholderLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(placeholderLabel, BorderLayout.CENTER);
        
        JButton backButton = new JButton("Back to Main Menu");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SwingUtilities.invokeLater(() -> new MainMenuFrame(loggedInUser).setVisible(true));
            }
        });
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
