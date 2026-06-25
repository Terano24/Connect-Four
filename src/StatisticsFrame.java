import javax.swing.*;
import java.awt.*;

public class StatisticsFrame extends JFrame {
    
    public StatisticsFrame(Player player) {
        setTitle("My Statistics - " + player.getUsername());
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        setLayout(new BorderLayout());
        
        JPanel statsPanel = new JPanel(new GridLayout(5, 1, 5, 5));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        statsPanel.add(new JLabel("Username: " + player.getUsername()));
        statsPanel.add(new JLabel("Wins: " + player.getWins()));
        statsPanel.add(new JLabel("Losses: " + player.getLosses()));
        statsPanel.add(new JLabel("Draws: " + player.getDraws()));
        statsPanel.add(new JLabel("Total Score: " + player.getScore()));
        
        add(statsPanel, BorderLayout.CENTER);
        
        JButton btnBack = new JButton("Back to Main Menu");
        btnBack.addActionListener(e -> {
            MainMenuFrame menuFrame = new MainMenuFrame(player);
            menuFrame.setVisible(true);
            this.dispose();
        });
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btnBack);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
