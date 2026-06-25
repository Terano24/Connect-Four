package service;

import util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {

    // Authenticate a user by checking if the username and password match a record
    public boolean authenticate(String username, String password) {
        String query = "SELECT id FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, username);
            stmt.setString(2, password);
            
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // True if a matching record is found
            }
        } catch (SQLException e) {
            System.err.println("Database error during authentication: " + e.getMessage());
        }
        return false;
    }

    // Register a new user
    public boolean register(String username, String password) {
        String query = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, username);
            stmt.setString(2, password);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Database error during registration: " + e.getMessage());
        }
        return false;
    }

    // Update player statistics after a game
    public void updateStatistics(String username, boolean isWin, boolean isDraw) {
        String query = "UPDATE users SET games_played = games_played + 1, " +
                       "games_won = games_won + ?, " +
                       "games_lost = games_lost + ?, " +
                       "games_drawn = games_drawn + ?, " +
                       "score = score + ? " +
                       "WHERE username = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            int won = isWin ? 1 : 0;
            int drawn = isDraw ? 1 : 0;
            int lost = (!isWin && !isDraw) ? 1 : 0;
            int scoreAdd = isWin ? 3 : (isDraw ? 1 : 0);
            
            stmt.setInt(1, won);
            stmt.setInt(2, lost);
            stmt.setInt(3, drawn);
            stmt.setInt(4, scoreAdd);
            stmt.setString(5, username);
            
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Database error during statistics update: " + e.getMessage());
        }
    }
}
