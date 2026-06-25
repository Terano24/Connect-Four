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
    private JButton[][] gridButtons;

    public GameFrame(String username) {
        this.loggedInUser = username;
        this.gameLogic = new GameLogic();
        this.userService = new UserService();
        
        setTitle("Connect Four - Game Board");
        setSize(700, 600); // Made slightly larger for 6x7 grid
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        setLayout(new BorderLayout());
        
        JLabel headerLabel = new JLabel("Player: " + username + " (X) vs Computer (O)", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(headerLabel, BorderLayout.NORTH);
        
        // 6x7 Grid
        JPanel gridPanel = new JPanel(new GridLayout(GameLogic.ROWS, GameLogic.COLS));
        gridButtons = new JButton[GameLogic.ROWS][GameLogic.COLS];
        
        for (int row = 0; row < GameLogic.ROWS; row++) {
            for (int col = 0; col < GameLogic.COLS; col++) {
                gridButtons[row][col] = new JButton("");
                gridButtons[row][col].setFont(new Font("Arial", Font.BOLD, 40));
                gridButtons[row][col].setFocusPainted(false);
                
                // When any button in a column is clicked, drop token into that column
                final int clickedCol = col;
                gridButtons[row][col].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        handlePlayerMove(clickedCol);
                    }
                });
                gridPanel.add(gridButtons[row][col]);
            }
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
    
    private void handlePlayerMove(int col) {
        int rowLanded = gameLogic.makeMove(col, 'X');
        
        if (rowLanded != -1) {
            // Update the specific button where the token landed
            gridButtons[rowLanded][col].setText("X");
            // Optionally change color here to Red: gridButtons[rowLanded][col].setForeground(Color.RED);
            
            if (checkGameOver()) return;
            
            // Computer turn
            int compCol = gameLogic.getComputerMove();
            if (compCol != -1) {
                int compRowLanded = gameLogic.makeMove(compCol, 'O');
                if (compRowLanded != -1) {
                    gridButtons[compRowLanded][compCol].setText("O");
                    checkGameOver();
                }
            }
        } else {
            // Column is full
            JOptionPane.showMessageDialog(this, "That column is full! Try another one.", "Invalid Move", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private boolean checkGameOver() {
        char state = gameLogic.checkWinState();
        if (state != ' ') {
            // Disable all buttons
            for (int row = 0; row < GameLogic.ROWS; row++) {
                for (int col = 0; col < GameLogic.COLS; col++) {
                    gridButtons[row][col].setEnabled(false);
                }
            }
            
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
