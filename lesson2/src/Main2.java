import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main2 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/users";
        String username = "root";
        String password = "Qwerty123!";

        String query = "SELECT * FROM employees WHERE name = ?";

        try (Connection connection = DriverManager.getConnection(url, username, password)){

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
