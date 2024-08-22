import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/users";
        String username = "root";
        String password = "Qwerty123!";

//        String query = "SELECT * FROM employees;";
//        String query = "INSERT INTO employees (name, job, salary) VALUES ('Irina', 'Full Stack Developer', 87000.0)";
//        String query = "DELETE FROM Employees WHERE id = 2;";
        String query = """
                UPDATE employees
                SET job = 'Full Stack Developer', salary = 95000.0
                WHERE id = 1;
                """;

        try{
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            int row = statement.executeUpdate(query);
            if (row > 0) {
                System.out.println("Изменено строк: " + row);
            } else {
                System.out.println("Ошибка изменения строк");
            }

//            statement.executeUpdate("CREATE TABLE employees (id INT PRIMARY KEY AUTO_INCREMENT, " +
//                    "name VARCHAR(255), job VARCHAR(255), salary DOUBLE)");
//            statement.executeUpdate("INSERT INTO employees VALUES (1, 'Roman', 'Java Developer', 75000.0)");
//            statement.executeUpdate("INSERT INTO employees (name, job, salary) VALUES ('Sergey', " +
//                    "'Devops Engineer', 65000.0)");
//
//            ResultSet rs = statement.executeQuery(query);
//
//            while(rs.next()){
//                int id = rs.getInt("id");
//                String name = rs.getString("name");
//                String job = rs.getString("job");
//                double salary = rs.getDouble("salary");
//                System.out.println("=====================================");
//                System.out.println("ID: " + id);
//                System.out.println("Name: " + name);
//                System.out.println("Job: " + job);
//                System.out.println("Salary: " + salary);
//            }
//
//            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}