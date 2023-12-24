package models;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hospital {
    private Long id;
    private String hospitalName;
    private String address;
    private final List<Department> departments = new ArrayList<>();
    private final List<Doctor> doctors = new ArrayList<>();
    private final List<Patient> patients = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Department> getDepartments() {
        return departments;
    }


    public List<Doctor> getDoctors() {
        return doctors;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public static Hospital createHospital() {
        Hospital hospital = new Hospital();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите название больницы: ");
        hospital.setHospitalName(scanner.nextLine());
        System.out.print("Введите адрес больницы: ");
        hospital.setAddress(scanner.nextLine());
        hospital.setId(System.currentTimeMillis());
        if (hospital.address.isEmpty() || hospital.hospitalName.isEmpty()) {
            System.out.println("Значение не должно быть пустым!");
        } else {
            return hospital;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Hospital " +
                "id = " + id +
                ", hospitalName = " + hospitalName +
                ", address = " + address;
    }
}
