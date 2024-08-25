package homework_03_04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/";
        String userName = "root";
        String password = "Qwerty123!";


        String databaseNameCars = "cars_db";
        try {
            /* Подключаемся к СУБД и создаем базу данных */
            Connection connection = DriverManager.getConnection(url, userName, password);
            Statement statement = connection.createStatement();

            String createDatabaseCars = "CREATE DATABASE IF NOT EXISTS " + databaseNameCars;
            statement.executeUpdate(createDatabaseCars);
            System.out.println("База данных " + databaseNameCars + " создана");

            /* Закрываем интерфейс statement и соединение с СУБД */
            statement.close();
            connection.close();

            /* Создаем новое соединение, уже к созданной базе данных, и новый statement */
            connection = DriverManager.getConnection(url + databaseNameCars, userName, password);
            statement = connection.createStatement();

            /* Создадим таблицу cars */
            String createTableCars = "CREATE TABLE IF NOT EXISTS cars (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "maker VARCHAR(50) NOT NULL," +
                    "model VARCHAR(50) NOT NULL," +
                    "engine_capacity VARCHAR(50) NOT NULL," +
                    "year INT NOT NULL," +
                    "color VARCHAR(50) NOT NULL," +
                    "type ENUM('Хэтчбек', 'Универсал', 'Седан') NOT NULL);";
            statement.executeUpdate(createTableCars);
            System.out.println("Таблица cars создана");

            /* Заполним таблицу cars */
            String fillTableCars = "INSERT INTO cars (maker, model, engine_capacity, year, color, type) VALUES " +
                    "('BMW', '1-Series E82', '2 литра', 2010, 'Красный', 'хэтчбек')," +
                    "('BMW', '7-Series E23', '3.2 литра', 1983, 'Черный', 'седан')," +
                    "('BMW', 'i7 xDrive60', '4.4 литра', 2022, 'Белый', 'седан')," +
                    "('Audi', 'A1', '1.4 литра', 2012, 'Красный', 'хэтчбек')," +
                    "('Audi', 'A6', '2.4 литра', 2017, 'Синий', 'седан')," +
                    "('Rolls-Royce', 'Phantom II', '5.2 литра', 1932, 'Черный', 'универсал')," +
                    "('Mercedes-Benz', 'E-Class', '4.3 литра', 2002, 'Серый', 'универсал');";
            statement.executeUpdate(fillTableCars);
            System.out.println("Таблица cars заполнена");

            /* Закрываем интерфейс statement и соединение с СУБД */
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
