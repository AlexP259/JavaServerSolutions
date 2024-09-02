package homework_05_06.hospital;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;

public class Hospital {
        public static void main(String[] args) {
        HospitalDB hospitalDB = new HospitalDB();
        HospitalView hospitalView = new HospitalView();
        Scanner input = new Scanner(System.in);

        while(true){
            int choice = hospitalView.mainMenu(input);

            switch(choice){
                case 1:
                    Patient patientData = hospitalView.getPatientData(input);
                    try{
                        hospitalDB.addPatient(patientData);
                        hospitalView.successAddPatient(patientData);
                    } catch (SQLException e){
                        hospitalView.unsuccessAddPatient(patientData);
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    ResultSet resultSet1 = hospitalDB.getListPatients();
                    hospitalView.showPatients(resultSet1);
                    break;
                case 3:
                    ResultSet resultSet2 = hospitalDB.getListDoctors();
                    hospitalView.showDoctors(resultSet2);
                    break;
                case 4:
                    int patientId = hospitalView.getPatientId(input);
                    int doctorId = hospitalView.getDoctorId(input);
                    Timestamp timestamp = hospitalDB.createAppointment(patientId, doctorId);
                    hospitalView.appointmentSuccess(timestamp);
                    break;
                case 5:
                    input.close();
                    hospitalView.exitMessage();
                    return;
            }
        }

    }
}
