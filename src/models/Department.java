package models;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Department {
    private Long id;
    private String departmentName;
    private final List<Doctor> doctors = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }
    public static Department createDepartment(List<Hospital> hospitals){
        Scanner scanner = new Scanner(System.in);
        Department department = new Department();
        System.out.print("Введите название отделения: ");
        department.setDepartmentName(scanner.nextLine());
        for (Hospital hospital : hospitals) {
            for (Department hospitalDepartment : hospital.getDepartments()) {
                if(hospitalDepartment.getDepartmentName().equals(department.getDepartmentName())){
                    return null;
                }
            }
        }
        if(department.getDepartmentName().isEmpty()){
            System.out.println("Значение не должно быть пустым!");
            return null;
        } else {
            department.setId(System.currentTimeMillis());
            return department;
        }
    }

    @Override
    public String toString() {
        return "Department" +
                ": id = " + id +
                ", departmentName = " + departmentName +
                ", doctors = " + doctors;
    }
}
