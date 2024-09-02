package homework_05_06.hospital;

/* Этот класс по большей части нужен чтобы данные о пациенте, получаемые в представлении, перенести в модель
для занесения в БД */

public class Patient {
    private int patientId;
    private String fullName;
    private String email;
    private long phoneNumber;
    private int age;

    public Patient(int patientId, String fullName, String email, int phoneNumber, int age) {
        this.patientId = patientId;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.age = age;
    }

    public Patient(String fullName, String email, long phoneNumber, int age) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.age = age;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public int getAge() {
        return age;
    }
}



















