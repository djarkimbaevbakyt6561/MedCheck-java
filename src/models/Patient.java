package models;

import abstracts.Person;
import enums.Gender;

import java.util.Scanner;
import java.util.InputMismatchException;
public class Patient extends Person {
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public static Patient createPatient() {
        Patient patient = new Patient();
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.print("Введите имя пациента: ");
            patient.setFirstName(scanner.nextLine());
            if(patient.getFirstName().isEmpty()){
                System.out.println("Значение не должно быть пустым!");
            } else {
                break;
            }
        }
        while (true){
            System.out.print("Введите фамилию пациента: ");
            patient.setLastName(scanner.nextLine());
            if(patient.getLastName().isEmpty()){
                System.out.println("Значение не должно быть пустым!");
            } else {
                break;
            }
        }
        while (true){
            System.out.println("Введите пол пациента: (male/female)");
            String gender = scanner.nextLine();
            if(gender.equals("male")){
                patient.setGender(Gender.MALE);
                break;
            } else if (gender.equals("female")){
                patient.setGender(Gender.FEMALE);
                break;
            } else {
                System.out.println("Введите правильный пол!");
            }
        }
        while (true){
            try {
                System.out.print("Введите возраст пациента: ");
                patient.setAge(scanner.nextInt());
                break;
            } catch (InputMismatchException e){
                System.out.println("Введите число!");
                scanner.nextLine();
            }

         }

        patient.setId(System.currentTimeMillis());
        return patient;
    }

    @Override
    public String toString() {
        return "Patient" +
                ": age = " + age +
                ", id = " + getId() +
                ", firstName = " + getFirstName() +
                ", lastName = " + getLastName() +
                ", gender = " + getGender();
    }
}
