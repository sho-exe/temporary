import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseTester {

    /**
     * Tests the connection to a database.
     * * @param url      The JDBC connection URL (e.g., "jdbc:mysql://localhost:3306/mydb")
     * @param user     The database username
     * @param password The database password
     * @return true if the connection was successful, false otherwise
     */
    public static boolean testDBConnection(String url, String user, String password) {
        // Try-with-resources automatically closes the connection when the block exits
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            
            // Optional: Verify if the connection is actually valid (1-second timeout)
            if (connection != null && connection.isValid(1)) {
                System.out.println("Success: Successfully connected to the database!");
                return true;
            } else {
                System.out.println("Failure: Connection was established but is invalid.");
                return false;
            }
            
        } catch (SQLException e) {
            System.err.println("Database connection failed!");
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        // Example Usage (Adjust URL, User, and Password for your specific DB)
        String dbUrl = "jdbc:mysql://localhost:3306/your_database_name";
        String dbUser = "postgres";
        String dbPass = "secure_password";

        boolean isConnected = testDBConnection(dbUrl, dbUser, dbPass);
        System.out.println("Connection test result: " + isConnected);
    }
}
