import models.Patient;

public class Main {
    public static void main(String[] args) {
        Patient patient = new Patient();
        patient.setAge(23);
        patient.setFirstName("asdfdas");
        System.out.println(patient.toString());
    }
}