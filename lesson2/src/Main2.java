import java.sql.*;

public class Main2 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/users";
        String username = "root";
        String password = "Qwerty123!";

//        String query = "SELECT * FROM employees WHERE name = ?";
        String query = "INSERT INTO employees (name, job, salary) VALUES(?, ?, ?)";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            /* execute выполнять пока рано, сначала передать что будет вместо вопросительного знака */
//            preparedStatement.setInt(1,5);
            preparedStatement.setString(1,"Alexander");
            preparedStatement.setString(2,"Full Stack Developer");
            preparedStatement.setDouble(3,120000.0);

            int row = preparedStatement.executeUpdate();
            if(row > 0){
                System.out.println("Записано успешно");
            } else {
                System.out.println("Ошибка");
            }

//            preparedStatement.setString(1, "irina"); // нумерованы знаки вопроса начиная с 1
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                int id = resultSet.getInt("id");
//                String name = resultSet.getNString("name");
//                String job = resultSet.getString("job");
//                double salary = resultSet.getDouble("salary");
//
//                System.out.println("ID: " + id);
//                System.out.println("NAME: " + name);
//                System.out.println("JOB: " + job);
//                System.out.println("SALARY: " + salary);
//            }s
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
