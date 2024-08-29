import java.sql.*;

public class Main5 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/users";
        String username = "root";
        String password = "Qwerty123!";

        /* Пакетная обработка запросов */

        try {
            Connection connection = DriverManager.getConnection(url, username, password); // как обычно создаем подключение
            connection.setAutoCommit(false); // отключаем авто-коммит

            try {
                Statement statement = connection.createStatement(); // как обычно создаем объект стейтмент

                /* С помощью метода addBatch явно добавляем новые запросы в пакет запросов */
//            statement.addBatch("INSERT INTO employees (name, job, salary) VALUES ('Igor', 'HR manager', 65000.0)");
//            statement.addBatch("INSERT INTO employees (name, job, salary) VALUES ('Oleg', 'C++ developer', 82000.0)");
//            statement.addBatch("INSERT INTO employees (name, job, salary) VALUES ('Petr', 'JS developer', 67000.0)");
                statement.addBatch("INSERT INTO employees VALUES (10, 'Igor', 'HR manager', 65000.0)");
                statement.addBatch("INSERT INTO employees VALUES (11, 'Oleg', 'C++ developer', 82000.0)");
                statement.addBatch("INSERT INTO employees VALUES (12, 'Petr', 'JS developer', 67000.0)");

                /* Посылаем пакет запросов серверу баз данных */
                statement.executeBatch();

                /* Применяем коммит. Без этого никаких изменений в БД не увидим */
                connection.commit();
                System.out.println("Пакетная обработка прошла успешно");
            } catch(BatchUpdateException e){
                connection.rollback(); // если коммит не отрабатывает, по факту мы все равно не увидим данные, но более правильно сделать rollback(), иначе не загрузившиеся данные из пакета будет висеть в оперативной памяти
                System.out.println("Ошибка пакетной обработки");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }
}
