package gui;

import logic.GameLogic;
import service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame {
    private String loggedInUser;
    private GameLogic gameLogic;
    private UserService userService;
    private JButton[] gridButtons;

    public GameFrame(String username) {
        this.loggedInUser = username;
        this.gameLogic = new GameLogic();
        this.userService = new UserService();
        
        setTitle("Connect Four - Game Board");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        setLayout(new BorderLayout());
        
        JLabel headerLabel = new JLabel("Player: " + username + " (X) vs Computer (O)", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(headerLabel, BorderLayout.NORTH);
        
        // 4x4 Grid
        JPanel gridPanel = new JPanel(new GridLayout(4, 4));
        gridButtons = new JButton[16];
        
        for (int i = 0; i < 16; i++) {
            final int index = i;
            gridButtons[i] = new JButton("");
            gridButtons[i].setFont(new Font("Arial", Font.BOLD, 40));
            gridButtons[i].setFocusPainted(false);
            
            gridButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handlePlayerMove(index);
                }
            });
            gridPanel.add(gridButtons[i]);
        }
        add(gridPanel, BorderLayout.CENTER);
        
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
    
    private void handlePlayerMove(int index) {
        if (gameLogic.makeMove(index, 'X')) {
            gridButtons[index].setText("X");
            gridButtons[index].setEnabled(false);
            
            if (checkGameOver()) return;
            
            // Computer turn
            int compMove = gameLogic.getComputerMove();
            if (compMove != -1) {
                gameLogic.makeMove(compMove, 'O');
                gridButtons[compMove].setText("O");
                gridButtons[compMove].setEnabled(false);
                checkGameOver();
            }
        }
    }
    
    private boolean checkGameOver() {
        char state = gameLogic.checkWinState();
        if (state != ' ') {
            // Disable all buttons
            for (JButton btn : gridButtons) btn.setEnabled(false);
            
            String message = "";
            if (state == 'X') {
                message = "Congratulations! You won!";
                userService.updateStatistics(loggedInUser, true, false);
            } else if (state == 'O') {
                message = "Computer wins! Better luck next time.";
                userService.updateStatistics(loggedInUser, false, false);
            } else if (state == 'D') {
                message = "It's a draw!";
                userService.updateStatistics(loggedInUser, false, true);
            }
            
            JOptionPane.showMessageDialog(this, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);
            
            // Route back to main menu
            this.dispose();
            SwingUtilities.invokeLater(() -> new MainMenuFrame(loggedInUser).setVisible(true));
            return true;
        }
        return false;
    }
}
