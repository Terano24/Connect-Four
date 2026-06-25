import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PlayerService {

    public Player login(String username, String password) {
        String sql = "SELECT * FROM players WHERE username = ? AND password = ?";
        try {
            Connection conn = DatabaseManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String uname = rs.getString("username");
                int wins = rs.getInt("wins");
                int losses = rs.getInt("losses");
                int draws = rs.getInt("draws");
                int score = rs.getInt("score");

                return new Player(id, uname, wins, losses, draws, score);
            }
        } catch (Exception e) {
            System.out.println("Login error: " + e.getMessage());
        }
        return null;
    }

    public boolean register(String username, String password) {
        String sql = "INSERT INTO players (username, password) VALUES (?, ?)";
        try {
            Connection conn = DatabaseManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            System.out.println("Registration error: " + e.getMessage());
        }
        return false;
    }

    public void updateStatistics(Player player, String result) {
        int additionalScore = 0;
        String sql = "";

        if (result.equalsIgnoreCase("WIN")) {
            additionalScore = 10;
            sql = "UPDATE players SET wins = wins + 1, score = score + ? WHERE id = ?";
            player.setWins(player.getWins() + 1);
        } else if (result.equalsIgnoreCase("LOSE")) {
            additionalScore = 0;
            sql = "UPDATE players SET losses = losses + 1, score = score + ? WHERE id = ?";
            player.setLosses(player.getLosses() + 1);
        } else if (result.equalsIgnoreCase("DRAW")) {
            additionalScore = 3;
            sql = "UPDATE players SET draws = draws + 1, score = score + ? WHERE id = ?";
            player.setDraws(player.getDraws() + 1);
        }
        
        player.setScore(player.getScore() + additionalScore);

        try {
            Connection conn = DatabaseManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, additionalScore);
            stmt.setInt(2, player.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Update statistics error: " + e.getMessage());
        }
    }
    
    public ArrayList<Player> getTopFiveScorers() {
        ArrayList<Player> topScorers = new ArrayList<>();
        String sql = "SELECT username, wins, losses, draws, score FROM players ORDER BY score DESC, wins DESC LIMIT 5";
        try {
            Connection conn = DatabaseManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String uname = rs.getString("username");
                int wins = rs.getInt("wins");
                int losses = rs.getInt("losses");
                int draws = rs.getInt("draws");
                int score = rs.getInt("score");
                // Id is not strictly needed for the table display, using 0
                topScorers.add(new Player(0, uname, wins, losses, draws, score));
            }
        } catch (Exception e) {
            System.out.println("Top scorers error: " + e.getMessage());
        }
        return topScorers;
    }
}
