package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameLogic {
    public static final int ROWS = 6;
    public static final int COLS = 7;
    private char[][] board; 
    private Random random;

    public GameLogic() {
        board = new char[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = ' '; // Empty space
            }
        }
        random = new Random();
    }

    // Drops a token into the specified column. Returns the row it landed in, or -1 if column is full.
    public int makeMove(int col, char player) {
        if (col < 0 || col >= COLS) return -1;
        
        // Start from bottom row (ROWS - 1) and go up
        for (int row = ROWS - 1; row >= 0; row--) {
            if (board[row][col] == ' ') {
                board[row][col] = player;
                return row; // Return the row where the token landed
            }
        }
        return -1; // Column is full
    }

    // Basic AI: pick a random valid column and drop token. Returns the column it chose, or -1 if board full.
    public int getComputerMove() {
        List<Integer> validCols = new ArrayList<>();
        for (int col = 0; col < COLS; col++) {
            if (board[0][col] == ' ') { // If the top of the column is empty, it's a valid move
                validCols.add(col);
            }
        }
        
        if (validCols.isEmpty()) return -1; // Board is completely full
        
        // Random column selection
        int chosenCol = validCols.get(random.nextInt(validCols.size()));
        return chosenCol;
    }

    // Checks if the given player has won (4 in a row)
    private boolean checkWin(char player) {
        // Check horizontal
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS - 3; col++) {
                if (board[row][col] == player && board[row][col+1] == player && 
                    board[row][col+2] == player && board[row][col+3] == player) {
                    return true;
                }
            }
        }
        
        // Check vertical
        for (int row = 0; row < ROWS - 3; row++) {
            for (int col = 0; col < COLS; col++) {
                if (board[row][col] == player && board[row+1][col] == player && 
                    board[row+2][col] == player && board[row+3][col] == player) {
                    return true;
                }
            }
        }
        
        // Check diagonal (top-left to bottom-right)
        for (int row = 0; row < ROWS - 3; row++) {
            for (int col = 0; col < COLS - 3; col++) {
                if (board[row][col] == player && board[row+1][col+1] == player && 
                    board[row+2][col+2] == player && board[row+3][col+3] == player) {
                    return true;
                }
            }
        }
        
        // Check diagonal (bottom-left to top-right)
        for (int row = 3; row < ROWS; row++) {
            for (int col = 0; col < COLS - 3; col++) {
                if (board[row][col] == player && board[row-1][col+1] == player && 
                    board[row-2][col+2] == player && board[row-3][col+3] == player) {
                    return true;
                }
            }
        }
            
        return false;
    }

    // Returns 'X' (Player won), 'O' (Computer won), 'D' (Draw), or ' ' (Ongoing)
    public char checkWinState() {
        if (checkWin('X')) return 'X';
        if (checkWin('O')) return 'O';
        
        // Check for draw (top row full means board is full)
        boolean isDraw = true;
        for (int col = 0; col < COLS; col++) {
            if (board[0][col] == ' ') {
                isDraw = false;
                break;
            }
        }
        if (isDraw) return 'D';
        
        return ' '; // Game still ongoing
    }
}
