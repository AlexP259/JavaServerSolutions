package homework_05_06;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateHospitalDB {
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Qwerty123!";
    private static final String DB_NAME = "Hospital";

    public static void main(String[] args) {
        /* Создаем базу данных */
        String sql = "CREATE DATABASE IF NOT EXISTS Hospital;";

        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);
        } catch(SQLException e){
            e.printStackTrace();
        }

        /* Создаем таблицы */
        try(Connection connection = DriverManager.getConnection(URL + DB_NAME, USERNAME, PASSWORD);
            Statement statement = connection.createStatement()){
            statement.addBatch("CREATE TABLE IF NOT EXISTS Specialties(speciality VARCHAR(255) PRIMARY KEY)");
            statement.addBatch("CREATE TABLE IF NOT EXISTS Cabinets(cabinet_number INT PRIMARY KEY)");
            statement.addBatch("CREATE TABLE IF NOT EXISTS Doctors(doctor_id INT AUTO_INCREMENT," +
                    "full_name VARCHAR(255) NOT NULL," +
                    "speciality VARCHAR(255) NOT NULL," +
                    "PRIMARY KEY (doctor_id)," +
                    "FOREIGN KEY (speciality) REFERENCES Specialties (speciality))");
            statement.addBatch("CREATE TABLE IF NOT EXISTS Patients(" +
                    "patient_id INT AUTO_INCREMENT," +
                    "full_name VARCHAR(255) NOT NULL," +
                    "age INT NOT NULL," +
                    "PRIMARY KEY (patient_id))");
            statement.addBatch( "CREATE TABLE IF NOT EXISTS Appointment(" +
                    "id INT AUTO_INCREMENT," +
                    "appointment_date DATETIME NOT NULL," +
                    "patient_id INT NOT NULL," +
                    "doctor_id INT NOT NULL," +
                    "cabinet_number INT NOT NULL," +
                    "PRIMARY KEY (id)," +
                    "FOREIGN KEY (patient_id) REFERENCES Patients (patient_id)," +
                    "FOREIGN KEY (doctor_id) REFERENCES Doctors (doctor_id)," +
                    "FOREIGN KEY (cabinet_number) REFERENCES Cabinets (cabinet_number))");
            statement.executeBatch();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
