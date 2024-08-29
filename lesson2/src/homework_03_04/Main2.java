package homework_03_04;

import java.sql.*;
import java.util.Locale;
import java.util.Scanner;

public class Main2 {
    private static final String URL_BD = "jdbc:mysql://localhost:3306/cars_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Qwerty123!";

    public static void main(String[] args) {
        /* Сначала подключимся к базе данных. Драйвер JDBC лежит в Platform_settings/SDK/ClassPath,
        * поэтому обходимся ато-регистрацией драйвера в момент создания подключения */

        try (Connection connection = DriverManager.getConnection(URL_BD, USERNAME, PASSWORD);
        Statement statement = connection.createStatement();
        Scanner input = new Scanner(System.in)) {
            while(true){
                System.out.println("База данных " + connection.getCatalog() + "\nДоступные операции:");
                System.out.println("1. Отобразить все содержимое таблицы с автомобилями");
                System.out.println("2. Показать всех производителей автомобилей");
                System.out.println("3. Показать все автомобили конкретного года выпуска");
                System.out.println("4. Показать все автомобили конкретного производителя");
                System.out.println("5. Создать фильтр для отображения автомобилей по указанному цвету");
                System.out.println("6. Создать фильтр по объему двигателя");
                System.out.println("7. Создать фильтр по типу двигателя");
                System.out.print("\nВыберете операцию: ");
                int choice = input.nextInt();
                switch(choice){
                    case 1:
                        showAllCars(statement);
                        break;
                    case 2:
                        showAllMakers(statement);
                        break;
                    case 3:
                        carsForTheSpecifiedYear(statement, input);
                        break;
                    case 4:
                        carsForTheSpecifiedMaker(statement, input);
                        break;
                    case 5:
                        carsBySpecifiedColor(statement, input);
                        break;
                    case 6:
                        carsByEngineCapacity(statement, input);
                        break;
                    case 7:
                        carsByType(statement, input);
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private enum Type{
        СЕДАН ("Седан"),
        ХЭТЧБЕК ("Хэтчбек"),
        УНИВЕРСАЛ ("Универсал");

        private String title;

        Type(String title){
            this.title = title;
        }

        @Override
        public String toString() {
            return title;
        }
    }

    private static void showAllCars(Statement statement){
        String sql = "SELECT * FROM cars";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("+-----------------+--------------------+-----------------+-------------+----------+-----------------+");
            System.out.println("|  Производитель  |      Название      | Объём двигателя | Год выпуска |   Цвет   | Тип автомобиля  |");
            System.out.println("+-----------------+--------------------+-----------------+-------------+----------+-----------------+");
            while(resultSet.next()){
                String maker = resultSet.getString("maker");
                String model = resultSet.getString("model");
                String engineCapacity = resultSet.getString("engine_capacity");
                int year = resultSet.getInt("year");
                String color = resultSet.getString("color");
                String type = resultSet.getString("type").toUpperCase();
                Type typeValue = Type.valueOf(type);
                System.out.printf("| %-15s | %-18s | %-15s | %-11d | %-8s | %-15s |%n", maker, model, engineCapacity, year, color, typeValue);
            }
            System.out.println("+-----------------+--------------------+-----------------+-------------+----------+-----------------+\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void showAllMakers(Statement statement){
        String sql = "SELECT DISTINCT maker FROM cars";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("+-----------------+");
            System.out.println("|  Производители  |");
            System.out.println("+-----------------+");
            while(resultSet.next()){
                String maker = resultSet.getString("maker");
                System.out.printf("| %-15s |%n", maker);
            }
            System.out.println("+-----------------+\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void carsForTheSpecifiedYear(Statement statement, Scanner input){
        System.out.print("Введите год выпуска автомобиля: ");
        int yearChoice = input.nextInt();
        String sql = "SELECT * " +
                "FROM cars " +
                "WHERE year=" + yearChoice;
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("+-----------------+--------------------+-----------------+-------------+----------+-----------------+");
            System.out.println("|  Производитель  |      Название      | Объём двигателя | Год выпуска |   Цвет   | Тип автомобиля  |");
            System.out.println("+-----------------+--------------------+-----------------+-------------+----------+-----------------+");
            while(resultSet.next()){
                String maker = resultSet.getString("maker");
                String model = resultSet.getString("model");
                String engineCapacity = resultSet.getString("engine_capacity");
                int year = resultSet.getInt("year");
                String color = resultSet.getString("color");
                String type = resultSet.getString("type").toUpperCase();
                Type typeValue = Type.valueOf(type);
                System.out.printf("| %-15s | %-18s | %-15s | %-11d | %-8s | %-15s |%n", maker, model, engineCapacity, year, color, typeValue);
            }
            System.out.println("+-----------------+--------------------+-----------------+-------------+----------+-----------------+\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void carsForTheSpecifiedMaker(Statement statement, Scanner input){
        input.nextLine();
        System.out.print("Введите имя производителя: ");
        String makerChoice = input.nextLine();
        String sql = "SELECT * " +
                "FROM cars " +
                "WHERE maker='" + makerChoice + "';";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("+-----------------+--------------------+-----------------+-------------+----------+-----------------+");
            System.out.println("|  Производитель  |      Название      | Объём двигателя | Год выпуска |   Цвет   | Тип автомобиля  |");
            System.out.println("+-----------------+--------------------+-----------------+-------------+----------+-----------------+");
            while(resultSet.next()){
                String maker = resultSet.getString("maker");
                String model = resultSet.getString("model");
                String engineCapacity = resultSet.getString("engine_capacity");
                int year = resultSet.getInt("year");
                String color = resultSet.getString("color");
                String type = resultSet.getString("type").toUpperCase();
                Type typeValue = Type.valueOf(type);
                System.out.printf("| %-15s | %-18s | %-15s | %-11d | %-8s | %-15s |%n", maker, model, engineCapacity, year, color, typeValue);
            }
            System.out.println("+-----------------+--------------------+-----------------+-------------+----------+-----------------+\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void carsBySpecifiedColor(Statement statement, Scanner input){
        input.nextLine();
        System.out.print("Введите цвет: ");
        String colorChoice = input.nextLine();
        String sql = "SELECT * " +
                "FROM cars " +
                "WHERE color='" + colorChoice + "';";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("+-----------------+--------------------+-----------------+-------------+----------+-----------------+");
            System.out.println("|  Производитель  |      Название      | Объём двигателя | Год выпуска |   Цвет   | Тип автомобиля  |");
            System.out.println("+-----------------+--------------------+-----------------+-------------+----------+-----------------+");
            while(resultSet.next()){
                String maker = resultSet.getString("maker");
                String model = resultSet.getString("model");
                String engineCapacity = resultSet.getString("engine_capacity");
                int year = resultSet.getInt("year");
                String color = resultSet.getString("color");
                String type = resultSet.getString("type").toUpperCase();
                Type typeValue = Type.valueOf(type);
                System.out.printf("| %-15s | %-18s | %-15s | %-11d | %-8s | %-15s |%n", maker, model, engineCapacity, year, color, typeValue);
            }
            System.out.println("+-----------------+--------------------+-----------------+-------------+----------+-----------------+\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void carsByEngineCapacity(Statement statement, Scanner input){
        input.nextLine();
        System.out.print("Введите объем двигателя: ");
        String engineCapacityChoice = input.nextLine();
        String sql = "SELECT * " +
                "FROM cars " +
                "WHERE engine_capacity LIKE '%" + engineCapacityChoice + "%';";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("+-----------------+--------------------+-----------------+-------------+----------+-----------------+");
            System.out.println("|  Производитель  |      Название      | Объём двигателя | Год выпуска |   Цвет   | Тип автомобиля  |");
            System.out.println("+-----------------+--------------------+-----------------+-------------+----------+-----------------+");
            while(resultSet.next()){
                String maker = resultSet.getString("maker");
                String model = resultSet.getString("model");
                String engineCapacity = resultSet.getString("engine_capacity");
                int year = resultSet.getInt("year");
                String color = resultSet.getString("color");
                String type = resultSet.getString("type").toUpperCase();
                Type typeValue = Type.valueOf(type);
                System.out.printf("| %-15s | %-18s | %-15s | %-11d | %-8s | %-15s |%n", maker, model, engineCapacity, year, color, typeValue);
            }
            System.out.println("+-----------------+--------------------+-----------------+-------------+----------+-----------------+\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void carsByType(Statement statement, Scanner input){
        input.nextLine();
        System.out.print("Введите тип автомобиля: ");
        String typeChoice = input.nextLine();
        String sql = "SELECT * " +
                "FROM cars " +
                "WHERE type LIKE '" + typeChoice + "';";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("+-----------------+--------------------+-----------------+-------------+----------+-----------------+");
            System.out.println("|  Производитель  |      Название      | Объём двигателя | Год выпуска |   Цвет   | Тип автомобиля  |");
            System.out.println("+-----------------+--------------------+-----------------+-------------+----------+-----------------+");
            while(resultSet.next()){
                String maker = resultSet.getString("maker");
                String model = resultSet.getString("model");
                String engineCapacity = resultSet.getString("engine_capacity");
                int year = resultSet.getInt("year");
                String color = resultSet.getString("color");
                String type = resultSet.getString("type").toUpperCase();
                Type typeValue = Type.valueOf(type);
                System.out.printf("| %-15s | %-18s | %-15s | %-11d | %-8s | %-15s |%n", maker, model, engineCapacity, year, color, typeValue);
            }
            System.out.println("+-----------------+--------------------+-----------------+-------------+----------+-----------------+\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}













