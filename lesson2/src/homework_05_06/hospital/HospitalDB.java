package homework_05_06.hospital;

import java.sql.*;
import java.time.LocalDateTime;

public class HospitalDB {
    private final String URL = "jdbc:mysql://localhost:3306/hospital";
    private final String USERNAME = "root";
    private final String PASSWORD = "Qwerty123!";

    /* Добавляем пациента в базу */
    public void addPatient(Patient patientData) throws SQLException{
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        String addPatientIntoDB = "INSERT INTO patients (full_name, email, phone_number, age) " +
                    "VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(addPatientIntoDB);
        preparedStatement.setString(1, patientData.getFullName());
        preparedStatement.setString(2, patientData.getEmail());
        preparedStatement.setLong(3, patientData.getPhoneNumber());
        preparedStatement.setInt(4, patientData.getAge());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }

    /* Просматриваем пациентов */
    public ResultSet getListPatients(){
        String showPatientsQuery = "SELECT * FROM patients";
        try{
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(showPatientsQuery);
            return resultSet;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /* Просматриваем врачей */
    public ResultSet getListDoctors(){
        String showPatientsQuery = "SELECT * FROM doctors";
        try{
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(showPatientsQuery);
            return resultSet;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /* Ниже то, что нужно для записи на прием */

    private boolean patientExists(int patientId){
        String searchPatient = "SELECT * FROM patients WHERE patient_id = ?";
        try{
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(searchPatient);
            preparedStatement.setInt(1, patientId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            } else {
                System.out.println("Пациента с таким ID не существует");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    private boolean doctorExists(int doctorId){
        String searchPatient = "SELECT * FROM doctors WHERE doctor_id = ?";
        try{
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(searchPatient);
            preparedStatement.setInt(1, doctorId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            } else {
                System.out.println("Врача с таким ID не существует");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    private LocalDateTime getFreeDatetime(int doctorId){
        String sql = "SELECT MAX(appointment_date) FROM appointment WHERE doctor_id = ?";
        LocalDateTime nextDateTime = null;
        try{
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, doctorId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Timestamp latestTimestamp = resultSet.getTimestamp(1);
                if (latestTimestamp != null) {
                    LocalDateTime latestDateTime = latestTimestamp.toLocalDateTime();
                    // Прибавляем 30 минут
                    nextDateTime = latestDateTime.plusMinutes(30);

                    // Проверяем, не превышает ли 18:00 текущего дня
                    LocalDateTime endOfDay = latestDateTime.toLocalDate().atTime(18, 0);
                    if (nextDateTime.isAfter(endOfDay)) {
                        // Если превышает, возвращаем 8:00 следующего дня
                        nextDateTime = endOfDay.toLocalDate().plusDays(1).atTime(8, 0);
                    }
                } else {
                    // Если нет записей, относится к следующему дню
                    nextDateTime = LocalDateTime.now().toLocalDate().plusDays(1).atTime(8, 0);
                }
            }
            } catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println(nextDateTime);
        return nextDateTime;
    }

    public Timestamp createAppointment(int patientId, int doctorId){
        if(patientExists(patientId) && doctorExists(doctorId)){
            String addAppointment = "INSERT INTO appointment (appointment_date, patient_id, doctor_id, cabinet_number) VALUES (?, ?, ?, ?)";
            Timestamp timestamp = Timestamp.valueOf(getFreeDatetime(doctorId));

            try{
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(addAppointment);
                preparedStatement.setTimestamp(1, timestamp);
                preparedStatement.setInt(2, patientId);
                preparedStatement.setInt(3, doctorId);
                preparedStatement.setInt(4, 5);
                preparedStatement.executeUpdate();
                return timestamp;
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return null;
    }

}
















