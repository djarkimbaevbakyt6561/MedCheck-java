package models;

import abstracts.Person;
import enums.Gender;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Doctor extends Person {
    private int experienceYear;

    public int getExperienceYear() {
        return experienceYear;
    }

    public void setExperienceYear(int experienceYear) {
        this.experienceYear = experienceYear;
    }
    public static Doctor createDoctor() {
        Doctor doctor = new Doctor();
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.print("Введите имя доктора: ");
            doctor.setFirstName(scanner.nextLine());
            if(doctor.getFirstName().isEmpty()){
                System.out.println("Значение не должно быть пустым!");
            } else {
                break;
            }
        }
        while (true){
            System.out.print("Введите фамилию доктора: ");
            doctor.setLastName(scanner.nextLine());
            if(doctor.getLastName().isEmpty()){
                System.out.println("Значение не должно быть пустым!");
            } else {
                break;
            }
        }
        while (true){
            System.out.println("Введите пол доктора: (male/female)");
            String gender = scanner.nextLine();
            if(gender.equals("male")){
                doctor.setGender(Gender.MALE);
                break;
            } else if (gender.equals("female")){
                doctor.setGender(Gender.FEMALE);
                break;
            } else {
                System.out.println("Введите правильный пол!");
            }
        }
        while (true){
            try {
                System.out.print("Введите опыт доктора (в годах): ");
                doctor.setExperienceYear(scanner.nextInt());
                break;
            } catch (InputMismatchException e){
                System.out.println("Введите число!");
                scanner.nextLine();
            }
        }

        doctor.setId(System.currentTimeMillis());
        return doctor;
    }

    @Override
    public String toString() {
        return "Doctor" +
                ": experienceYear = " + experienceYear +
                ", id = " + getId() +
                ", firstName = " + getFirstName() +
                ", lastName =" + getLastName() +
                ", gender =" + getGender();
    }
}
