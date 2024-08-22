import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String userName = "root";
        String password = "Qwerty123!";
        String connectionUrl = "jdbc:mysql://localhost:3306/test";

        try(Connection connection = DriverManager.getConnection(connectionUrl, userName, password);
            Statement statement = connection.createStatement()){
            /* драйвер я прописал в ProjectStructure/Libraries, так эта библиотека используется только для этого
            проекта, а мог бы засунуть в SDK/sourcePath, тогда драйвер работал бы для нескольких проектов */
            // Class.forName("com.mysql.cj.jdbc.Driver"); // "com.mysql.jdbc.Driver" - deprecated
            System.out.println("We're connected");

//            statement.executeUpdate("drop TABLE Books");
//            statement.executeUpdate("create TABLE Books (id INT not null auto_increment, name VARCHAR(30) " +
//                    "not null, PRIMARY KEY(id))");
//            statement.executeUpdate("insert into Books (name) values('Inferno')");
//            statement.executeUpdate("insert into Books set name = 'Solomon key'");

            ResultSet resultSet = statement.executeQuery("SELECT * FROM books");
            System.out.println(resultSet);
            while(resultSet.next()){
//                System.out.print(resultSet.getInt(1) + " - ");
//                System.out.println(resultSet.getString(2));
                System.out.print(resultSet.getInt("id") + " - ");
                System.out.println(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}