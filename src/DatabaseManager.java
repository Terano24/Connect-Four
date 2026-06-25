import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    // Database configuration
    private static final String URL = "jdbc:mysql://localhost:3307/game_project";
    private static final String USER = "bn_processmaker";
    private static final String PASSWORD = "5659ecd8ab";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
