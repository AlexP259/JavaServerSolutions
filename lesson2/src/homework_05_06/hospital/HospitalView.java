package homework_05_06.hospital;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;

public class HospitalView {
    public int mainMenu(Scanner input) {
        System.out.println("СИСТЕМА УПРАВЛЕНИЯ БОЛЬНИЦЕЙ");
        System.out.println("1. Добавить пациента");
        System.out.println("2. Просмотр пациентов");
        System.out.println("3. Посмотреть врачей");
        System.out.println("4. Записаться на прием");
        System.out.println("5. Выход");
        System.out.print("Ваш выбор: ");
        return input.nextInt();
    }

    /* Добавляем пациента в базу */
    public Patient getPatientData(Scanner input) {
        String fullName;
        String email;
        long phoneNumber;
        int age;

        input.nextLine();
        System.out.print("Введите ФИО пациента: ");
        fullName = input.nextLine();
        System.out.print("Введите email пациента: ");
        email = input.nextLine();
        System.out.print("Введите номер телефона пациента: ");
        phoneNumber = input.nextLong();
        System.out.print("Введите возраст пациента: ");
        age = input.nextInt();

        return new Patient(fullName, email, phoneNumber, age);
    }

    /* Пациент добавлен успешно */
    public void successAddPatient(Patient patientData) {
        System.out.println("Пациент " + patientData.getFullName() + " добавлен успешно\n");
    }

    /* Пациент не добавлен */
    public void unsuccessAddPatient(Patient patientData) {
        System.out.println("Пациент " + patientData.getFullName() + " не был добавлен");
    }

    /* Просматриваем пациентов */
    public void showPatients(ResultSet resultSet) {
        try {
            System.out.println("\nПациенты:");
            System.out.println("+-------------------------+---------------------------------+-----------------------------+------------------+----------+");
            System.out.println("|  Идентификатор пациента |               ФИО               |            email            | Контактный номер | Возраст  |");
            System.out.println("+-------------------------+---------------------------------+-----------------------------+------------------+----------+");
            while (resultSet.next()) {
                int patientId = resultSet.getInt("patient_id");
                String fullName = resultSet.getString("full_name");
                String email = resultSet.getString("email");
                long phoneNumber = resultSet.getLong("phone_number");
                int age = resultSet.getInt("age");
                System.out.printf("|  %-22d | %-31s | %-27s | %-16d | %-7d  |%n", patientId, fullName, email, phoneNumber, age);
            }
            System.out.println("+-------------------------+---------------------------------+-----------------------------+------------------+----------+\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* Просматриваем врачей */
    public void showDoctors(ResultSet resultSet) {
        try {
            System.out.println("\nВрачи:");
            System.out.println("+------------------------+---------------------------------+---------------------------------+");
            System.out.println("|  Идентификатор доктора |               ФИО               |          Специальность          |");
            System.out.println("+------------------------+---------------------------------+---------------------------------+");
            while (resultSet.next()) {
                int doctorId = resultSet.getInt("doctor_id");
                String fullName = resultSet.getString("full_name");
                String speciality = resultSet.getString("speciality");
                System.out.printf("|  %-21d | %-31s | %-31s |%n", doctorId, fullName, speciality);
            }
            System.out.println("+------------------------+---------------------------------+---------------------------------+\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* Ниже то, что нужно для записи на прием */
    public int getPatientId(Scanner input){
        input.nextLine();
        System.out.print("Введите id пациента: ");
        return input.nextInt();
    }

    public int getDoctorId(Scanner input){
        input.nextLine();
        System.out.print("Введите id врача: ");
        return input.nextInt();
    }

    public void appointmentSuccess(Timestamp timestamp){
        System.out.println("Успешная запись на: " + timestamp + "\n");
    }

    public void exitMessage() {
        System.out.println("\nСПАСИБО ЗА ИСПОЛЬЗОВАНИЕ СИСТЕМЫ УПРАВЛЕНИЯ БОЛЬНИЦЕЙ!");
        System.out.println("Выход из системы!");
    }
}















