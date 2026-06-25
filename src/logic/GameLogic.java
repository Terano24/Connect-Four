package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameLogic {
    private char[][] board; // 4x4 array
    private Random random;

    public GameLogic() {
        board = new char[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = ' '; // Empty space
            }
        }
        random = new Random();
    }

    // Antigravity move: token is placed exactly at the index (0-15)
    public boolean makeMove(int index, char player) {
        int row = index / 4;
        int col = index % 4;
        if (board[row][col] == ' ') {
            board[row][col] = player;
            return true;
        }
        return false;
    }

    // Basic AI: pick a random empty spot
    public int getComputerMove() {
        List<Integer> emptySpots = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            int row = i / 4;
            int col = i % 4;
            if (board[row][col] == ' ') {
                emptySpots.add(i);
            }
        }
        if (emptySpots.isEmpty()) return -1;
        return emptySpots.get(random.nextInt(emptySpots.size()));
    }

    // Checks if the given player has won (4 in a row)
    private boolean checkWin(char player) {
        // Check rows
        for (int i = 0; i < 4; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player && board[i][3] == player)
                return true;
        }
        // Check columns
        for (int j = 0; j < 4; j++) {
            if (board[0][j] == player && board[1][j] == player && board[2][j] == player && board[3][j] == player)
                return true;
        }
        // Check diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player && board[3][3] == player)
            return true;
        if (board[0][3] == player && board[1][2] == player && board[2][1] == player && board[3][0] == player)
            return true;
            
        return false;
    }

    // Returns 'X' (Player won), 'O' (Computer won), 'D' (Draw), or ' ' (Ongoing)
    public char checkWinState() {
        if (checkWin('X')) return 'X';
        if (checkWin('O')) return 'O';
        
        // Check for draw (board full)
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == ' ') {
                    return ' '; // Game still ongoing
                }
            }
        }
        return 'D'; // Draw
    }
}
