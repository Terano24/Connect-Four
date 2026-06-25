import java.sql.Connection;
import util.DatabaseConnection;

public class Main {
    public static void main(String[] args) {
        System.out.println("Testing Database Connection...");
        Connection conn = DatabaseConnection.getConnection();
        
        if (conn != null) {
            DatabaseConnection.closeConnection(conn);
        }
    }
}
