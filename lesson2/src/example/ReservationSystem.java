package example;

import java.sql.*;
import java.util.Scanner;

public class ReservationSystem {
    private static final String url = "jdbc:mysql://localhost:3306/hotel_db";
    private static final String username = "root";
    private static final String password = "Qwerty123!";

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            while (true) {
                System.out.println("\nСИСТЕМА БРОНИРОВАНИЯ ОТЕЛЯ");
                Scanner input = new Scanner(System.in);
                System.out.println("1. Бронирование номера");
                System.out.println("2. Просмотр бронирования");
                System.out.println("3. Уточнить номер комнаты");
                System.out.println("4. Обновить бронирование");
                System.out.println("5. Удалить бронирование");
                System.out.println("0. Выход");
                System.out.print("Выберите опцию: ");
                int choice = input.nextInt();
                switch (choice) {
                    case 1:
                        reserveRoom(connection, input);
                        break;
                    case 2:
                        viewReservations(connection);
                        break;
                    case 3:
                        getRoomNumber(connection, input);
                        break;
                    case 0:
                        exit();
                        input.close();
                        return;
                    default:
                        System.out.println("Неверный выбор. Попробуйте еще раз.");
                }
            }
        } catch (SQLException e) { // catch (SQLException | InterruptedException e)
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void exit() throws InterruptedException {
        System.out.print("Выход из системы");
        int i = 5;
        while (i != 0) {
            System.out.print(".");
            Thread.sleep(1000);
            i--;
        }
        System.out.println();
        System.out.println("Спасибо за использование системы бронирования отеля");
    }

    public static void reserveRoom(Connection connection, Scanner input) {
        try {
            System.out.print("Введите имя гостя: ");
            String guestName = input.next();

            System.out.print("Введите количество гостей: ");
            int roomNumber = input.nextInt();

            System.out.print("Введите контактный номер: ");
            String contactNumber = input.next();

            String sql = "INSERT INTO reservations (guest_name, root_number, contact_number) VALUES ('"
                    + guestName + "', " + roomNumber + ", '" + contactNumber + "')";
            try (Statement statement = connection.createStatement()) {
                int row = statement.executeUpdate(sql);
                if (row > 0) {
                    System.out.println("Бронирование успешно");
                } else {
                    System.out.println("Бронирование не удалось");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void viewReservations(Connection connection) throws SQLException {
        String sql = "SELECT * FROM reservations";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            System.out.println("Текущее бронирование:");
            System.out.println("+-----------------------------+-------------+---------------+------------------+-------------------------+");
            System.out.println("|  Идентификатор бронирования | Гость       | Кол-во гостей | Контактный номер | Дата бронирования       |");
            System.out.println("+-----------------------------+-------------+---------------+------------------+-------------------------+");
            while(resultSet.next()){
                int reservationId = resultSet.getInt("reservation_id");
                String guestName = resultSet.getString("guest_name");
                int roomNumber = resultSet.getInt("root_number");
                String contactNumber = resultSet.getString("contact_number");
                String reservationDate = resultSet.getTimestamp("reservation_date").toString();
                System.out.printf("| %27d | %-11s | %-13d | %-16s | %-23s |%n", reservationId, guestName, roomNumber, contactNumber, reservationDate);
            }
            System.out.println("+-----------------------------+-------------+---------------+------------------+-------------------------+");
        }
    }

    public static void getRoomNumber(Connection connection, Scanner input){
        System.out.println("Введите идентификатор бронирования: ");
        int reservationId = input.nextInt();
        System.out.println("Введите имя гостя: ");
        String guestName = input.next();
    }
}
