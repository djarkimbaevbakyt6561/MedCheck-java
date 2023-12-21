import dao.impls.DepartmentDaoImpl;
import dao.impls.DoctorDaoImpl;
import dao.impls.HospitalDaoImpl;
import dao.impls.PatientDaoImpl;
import db.Database;
import enums.Gender;
import models.Department;
import models.Doctor;
import models.Hospital;
import models.Patient;
import services.impls.DepartmentServiceImpl;
import services.impls.DoctorServiceImpl;
import services.impls.HospitalServiceImpl;
import services.impls.PatientServiceImpl;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Database database = new Database();
        DepartmentDaoImpl departmentDao = new DepartmentDaoImpl(database);
        HospitalDaoImpl hospitalDao = new HospitalDaoImpl(database);
        PatientDaoImpl patientDao = new PatientDaoImpl(database);
        DoctorDaoImpl doctorDao = new DoctorDaoImpl(database);

        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(departmentDao);
        HospitalServiceImpl hospitalService = new HospitalServiceImpl(hospitalDao);
        PatientServiceImpl patientService = new PatientServiceImpl(patientDao);
        DoctorServiceImpl doctorService = new DoctorServiceImpl(doctorDao);
        boolean exit = false;
        while (!exit) {
            System.out.println("""
                    1.  Добавить больницу
                    2.  Добавить пациента к больнице
                    3.  Добавить пациентов к больнице
                    4.  Добавить доктора к больнице
                    5.  Добавить отделение к больнице
                    6.  Удалить пациента с больницы
                    7.  Удалить доктора с больницы
                    8.  Удалить отделение с больницы
                    9.  Удалить больницу по id
                    10. Обновить пациента с больницы
                    11. Обновить доктора с больницы
                    12. Обновить отделение с больницы
                    13. Получить пациента по id
                    14. Получить пациентов по возрасту
                    15. Сортировать пациентов по возрасту
                    16. Получить больницу по id
                    17. Получить все больницы
                    18. Получить всех пациентов с больницы
                    19. Получить все больницы по адресам
                    20. Отправить докторов в отделение
                    21. Получить всех докторов с больницы
                    22. Получить всех докторов с отделения
                    23. Получить все отделения с больницы
                    24. Найти отделение по названию
                    25. Выйти""");
            System.out.print("Введите команду: ");
            String num = scanner.nextLine();
            switch (num) {
                case "1" -> {
                    Hospital newHospital = Hospital.createHospital();
                    if (newHospital != null) {
                        System.out.println(hospitalService.addHospital(newHospital));
                    }
                }
                case "2" -> {
                    if (hospitalService.getAllHospital().isEmpty()) {
                        System.out.println("Больниц нету!");
                    } else {
                        System.out.println("Все больницы: ");
                        for (Hospital hospital : hospitalService.getAllHospital()) {
                            System.out.println(hospital);
                        }
                        try {
                            System.out.print("Выберите больницу по id: ");
                            Long id = scanner.nextLong();
                            boolean found = false;
                            for (Hospital hospital : hospitalService.getAllHospital()) {
                                if (hospital.getId().equals(id)) {
                                    found = true;
                                    break;
                                }
                            }
                            if (found) {
                                Patient newPatient = Patient.createPatient();
                                System.out.println(patientService.add(id, newPatient));
                            } else {
                                System.out.println("Больница с таким id не найден!");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Введите число!");
                        }
                        scanner.nextLine();
                    }
                }
                case "3" -> {
                    if (hospitalService.getAllHospital().isEmpty()) {
                        System.out.println("Больниц нету!");
                    } else {
                        System.out.println("Все больницы: ");
                        for (Hospital hospital : hospitalService.getAllHospital()) {
                            System.out.println(hospital);
                        }
                        try {
                            System.out.print("Выберите больницу по id: ");
                            Long id = scanner.nextLong();
                            boolean found = false;
                            for (Hospital hospital : hospitalService.getAllHospital()) {
                                if (hospital.getId().equals(id)) {
                                    found = true;
                                    break;
                                }
                            }
                            if (found) {
                                while (true) {
                                    try {
                                        System.out.print("Сколько пациентов хотите добавить: ");
                                        int count = scanner.nextInt();
                                        List<Patient> patients = new ArrayList<>();
                                        for (int i = 0; i < count; i++) {
                                            Patient newPatient = Patient.createPatient();
                                            patients.add(newPatient);
                                        }
                                        System.out.println(patientService.addPatientsToHospital(id, patients));

                                        break;
                                    } catch (InputMismatchException e) {
                                        System.out.println("Введите число!");
                                        scanner.nextLine();
                                    }
                                }
                            } else {
                                System.out.println("Больница с таким id не найден!");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Введите число!");
                        }
                        scanner.nextLine();
                    }
                }
                case "4" -> {
                    if (hospitalService.getAllHospital().isEmpty()) {
                        System.out.println("Больниц нету!");
                    } else {
                        System.out.println("Все больницы: ");
                        for (Hospital hospital : hospitalService.getAllHospital()) {
                            System.out.println(hospital);
                        }
                        try {
                            System.out.print("Выберите больницу по id: ");
                            Long id = scanner.nextLong();
                            boolean found = false;
                            for (Hospital hospital : hospitalService.getAllHospital()) {
                                if (hospital.getId().equals(id)) {
                                    found = true;
                                    break;
                                }
                            }
                            if (found) {
                                Doctor newDoctor = Doctor.createDoctor();
                                System.out.println(doctorService.add(id, newDoctor));
                            } else {
                                System.out.println("Больница с таким id не найден!");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Введите число!");
                        }
                        scanner.nextLine();
                    }
                }
                case "5" -> {
                    if (hospitalService.getAllHospital().isEmpty()) {
                        System.out.println("Больниц нету!");
                    } else {
                        System.out.println("Все больницы: ");
                        for (Hospital hospital : hospitalService.getAllHospital()) {
                            System.out.println(hospital);
                        }
                        try {
                            System.out.print("Выберите больницу по id: ");
                            Long id = scanner.nextLong();
                            boolean found = false;
                            for (Hospital hospital : hospitalService.getAllHospital()) {
                                if (hospital.getId().equals(id)) {
                                    found = true;
                                    break;
                                }
                            }
                            if (found) {
                                Department newDepartment = Department.createDepartment(hospitalService.getAllHospital());
                                System.out.println(departmentService.add(id, newDepartment));
                            } else {
                                System.out.println("Больница с таким id не найден!");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Введите число!");
                        }
                        scanner.nextLine();
                    }
                }
                case "6" -> {
                    if (hospitalService.getAllHospital().isEmpty()) {
                        System.out.println("Больниц нету!");
                    } else {
                        System.out.println("Все больницы: ");
                        for (Hospital hospital : hospitalService.getAllHospital()) {
                            System.out.println(hospital);
                        }
                        try {
                            System.out.print("Выберите больницу по id: ");
                            Long id = scanner.nextLong();
                            Hospital foundHospital = null;
                            for (Hospital hospital : hospitalService.getAllHospital()) {
                                if (hospital.getId().equals(id)) {
                                    foundHospital = hospital;
                                    break;
                                }
                            }
                            if (foundHospital != null) {
                                if (foundHospital.getPatients().isEmpty()) {
                                    System.out.println("Пациентов нет!");
                                } else {
                                    System.out.println("Все пациенты: ");
                                    for (Patient patient : foundHospital.getPatients()) {
                                        System.out.println(patient);
                                    }
                                    while (true) {
                                        try {
                                            System.out.print("Введите id пациента: ");
                                            String result = patientService.removeById(scanner.nextLong());
                                            if (result != null) {
                                                System.out.println(result);
                                                break;
                                            } else {
                                                System.out.println("Пациента с таким id нету!");
                                            }
                                        } catch (InputMismatchException e) {
                                            System.out.println("Введите число!;");
                                        }
                                    }
                                }

                            } else {
                                System.out.println("Больница с таким id не найден!");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Введите число!");
                        }
                        scanner.nextLine();
                    }
                }
                case "7" -> {
                    if (hospitalService.getAllHospital().isEmpty()) {
                        System.out.println("Больниц нету!");
                    } else {
                        System.out.println("Все больницы: ");
                        for (Hospital hospital : hospitalService.getAllHospital()) {
                            System.out.println(hospital);
                        }
                        try {
                            System.out.print("Выберите больницу по id: ");
                            Long id = scanner.nextLong();
                            Hospital foundHospital = null;
                            for (Hospital hospital : hospitalService.getAllHospital()) {
                                if (hospital.getId().equals(id)) {
                                    foundHospital = hospital;
                                    break;
                                }
                            }
                            if (foundHospital != null) {
                                if (foundHospital.getPatients().isEmpty()) {
                                    System.out.println("Докторов нет!");
                                } else {
                                    System.out.println("Все докторы: ");
                                    for (Doctor doctor : foundHospital.getDoctors()) {
                                        System.out.println(doctor);
                                    }
                                    while (true) {
                                        try {
                                            System.out.print("Введите id доктора: ");
                                            String result = doctorService.removeById(scanner.nextLong());
                                            if (result != null) {
                                                System.out.println(result);
                                                break;
                                            } else {
                                                System.out.println("Доктора с таким id нету!");
                                            }
                                        } catch (InputMismatchException e) {
                                            System.out.println("Введите число!;");
                                        }
                                    }
                                }

                            } else {
                                System.out.println("Больница с таким id не найден!");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Введите число!");
                        }
                        scanner.nextLine();
                    }
                }
                case "8" -> {
                    if (hospitalService.getAllHospital().isEmpty()) {
                        System.out.println("Больниц нету!");
                    } else {
                        System.out.println("Все больницы: ");
                        for (Hospital hospital : hospitalService.getAllHospital()) {
                            System.out.println(hospital);
                        }
                        try {
                            System.out.print("Выберите больницу по id: ");
                            Long id = scanner.nextLong();
                            Hospital foundHospital = null;
                            for (Hospital hospital : hospitalService.getAllHospital()) {
                                if (hospital.getId().equals(id)) {
                                    foundHospital = hospital;
                                    break;
                                }
                            }
                            if (foundHospital != null) {
                                if (foundHospital.getPatients().isEmpty()) {
                                    System.out.println("Отделений нет!");
                                } else {
                                    System.out.println("Все отделения: ");
                                    for (Department department : foundHospital.getDepartments()) {
                                        System.out.println(department);
                                    }
                                    while (true) {
                                        try {
                                            System.out.print("Введите id отделения: ");
                                            String result = departmentService.removeById(scanner.nextLong());
                                            if (result != null) {
                                                System.out.println(result);
                                                break;
                                            } else {
                                                System.out.println("Отделения с таким id нету!");
                                            }
                                        } catch (InputMismatchException e) {
                                            System.out.println("Введите число!;");
                                        }
                                    }
                                }

                            } else {
                                System.out.println("Больница с таким id не найден!");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Введите число!");
                        }
                        scanner.nextLine();
                    }
                }
                case "9" -> {
                    if (hospitalService.getAllHospital().isEmpty()) {
                        System.out.println("Больниц нету!");
                    } else {
                        System.out.println("Все больницы: ");
                        for (Hospital hospital : hospitalService.getAllHospital()) {
                            System.out.println(hospital);
                        }
                        try {
                            System.out.print("Выберите больницу по id: ");
                            Long id = scanner.nextLong();
                            Hospital foundHospital = null;
                            for (Hospital hospital : hospitalService.getAllHospital()) {
                                if (hospital.getId().equals(id)) {
                                    foundHospital = hospital;
                                    break;
                                }
                            }
                            if (foundHospital != null) {
                                System.out.println(hospitalService.deleteHospitalById(foundHospital.getId()));
                            } else {
                                System.out.println("Больница с таким id не найден!");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Введите число!");
                        }
                        scanner.nextLine();
                    }
                }
                case "10" -> {
                    if (hospitalService.getAllHospital().isEmpty()) {
                        System.out.println("Больниц нету!");
                    } else {
                        System.out.println("Все больницы: ");
                        for (Hospital hospital : hospitalService.getAllHospital()) {
                            System.out.println(hospital);
                        }
                        try {
                            System.out.print("Выберите больницу по id: ");
                            Long id = scanner.nextLong();
                            Hospital foundHospital = null;
                            for (Hospital hospital : hospitalService.getAllHospital()) {
                                if (hospital.getId().equals(id)) {
                                    foundHospital = hospital;
                                    break;
                                }
                            }
                            if (foundHospital != null) {
                                if (foundHospital.getPatients().isEmpty()) {
                                    System.out.println("Пациентов нет!");
                                } else {
                                    try {
                                        System.out.println("Все пациенты:");
                                        for (Patient patient : foundHospital.getPatients()) {
                                            System.out.println(patient);
                                        }
                                        System.out.print("Введите id пациента: ");
                                        Long idPatient = scanner.nextLong();
                                        Patient foundPatient = getPatient(foundHospital, idPatient);
                                        if (foundPatient != null) {
                                            boolean exitEdit = false;
                                            scanner.nextLine();
                                            while (!exitEdit) {
                                                System.out.println("""
                                                        Что вы хотите изменить?
                                                        1.Имя
                                                        2.Фамилия
                                                        3.Возраст
                                                        4.Пол
                                                        5.Сохронить изменения""");
                                                String num2 = scanner.nextLine();
                                                switch (num2) {
                                                    case "1" ->
                                                            foundPatient.setFirstName(inputString("Ввeдите новое имя: "));
                                                    case "2" ->
                                                            foundPatient.setLastName(inputString("Ввeдите новую фамилию: "));
                                                    case "3" -> {
                                                        System.out.print("Введите возраст: ");
                                                        while (true) {
                                                            if (scanner.hasNextInt()) {
                                                                foundPatient.setAge(scanner.nextInt());
                                                                break;
                                                            } else {
                                                                System.out.println("Введите число!");
                                                                scanner.nextLine();
                                                            }
                                                        }
                                                        scanner.nextLine();
                                                    }
                                                    case "4" -> {
                                                        while (true) {
                                                            System.out.println("Введите пол пациента: (male/female)");
                                                            String gender = scanner.nextLine();
                                                            if (gender.equals("male")) {
                                                                foundPatient.setGender(Gender.MALE);
                                                                break;
                                                            } else if (gender.equals("female")) {
                                                                foundPatient.setGender(Gender.FEMALE);
                                                                break;
                                                            } else {
                                                                System.out.println("Введите правильный пол!");
                                                            }
                                                        }
                                                    }

                                                    case "5" -> {
                                                        System.out.println(patientService.updateById(foundPatient.getId(), foundPatient));
                                                        exitEdit = true;
                                                    }
                                                }
                                            }
                                        } else {
                                            System.out.println("Пациента с таким id нету!");
                                        }
                                    } catch (InputMismatchException e) {
                                        System.out.println("Введите число!;");
                                    }
                                }

                            } else {
                                System.out.println("Больница с таким id не найден!");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Введите число!");
                        }
                        scanner.nextLine();
                    }
                }
                case "11" -> {
                    if (hospitalService.getAllHospital().isEmpty()) {
                        System.out.println("Больниц нету!");
                    } else {
                        System.out.println("Все больницы: ");
                        for (Hospital hospital : hospitalService.getAllHospital()) {
                            System.out.println(hospital);
                        }
                        try {
                            System.out.print("Выберите больницу по id: ");
                            Long id = scanner.nextLong();
                            Hospital foundHospital = null;
                            for (Hospital hospital : hospitalService.getAllHospital()) {
                                if (hospital.getId().equals(id)) {
                                    foundHospital = hospital;
                                    break;
                                }
                            }
                            if (foundHospital != null) {
                                if (foundHospital.getDoctors().isEmpty()) {
                                    System.out.println("Докторов нет!");
                                } else {
                                    try {
                                        System.out.print("Введите id доктора: ");
                                        Long idDoctor = scanner.nextLong();
                                        Doctor foundDoctor = getDoctor(foundHospital, idDoctor);
                                        if (foundDoctor != null) {
                                            boolean exitEdit = false;
                                            scanner.nextLine();
                                            while (!exitEdit) {
                                                System.out.println("""
                                                        Что вы хотите изменить?
                                                        1.Имя
                                                        2.Фамилия
                                                        3.Опыт работы
                                                        4.Пол
                                                        5.Сохронить изменения""");
                                                String num2 = scanner.nextLine();
                                                switch (num2) {
                                                    case "1" ->
                                                            foundDoctor.setFirstName(inputString("Ввeдите новое имя: "));
                                                    case "2" ->
                                                            foundDoctor.setLastName(inputString("Ввeдите новую фамилию: "));
                                                    case "3" -> {
                                                        System.out.print("Введите опыт работы (в годах): ");
                                                        while (true) {
                                                            if (scanner.hasNextInt()) {
                                                                foundDoctor.setExperienceYear(scanner.nextInt());
                                                                break;
                                                            } else {
                                                                System.out.println("Введите число!");
                                                                scanner.nextLine();
                                                            }
                                                        }
                                                        scanner.nextLine();
                                                    }
                                                    case "4" -> {
                                                        while (true) {
                                                            System.out.println("Введите пол пациента: (male/female)");
                                                            String gender = scanner.nextLine();
                                                            if (gender.equals("male")) {
                                                                foundDoctor.setGender(Gender.MALE);
                                                                break;
                                                            } else if (gender.equals("female")) {
                                                                foundDoctor.setGender(Gender.FEMALE);
                                                                break;
                                                            } else {
                                                                System.out.println("Введите правильный пол!");
                                                            }
                                                        }
                                                    }

                                                    case "5" -> {
                                                        System.out.println(doctorService.updateById(foundDoctor.getId(), foundDoctor));
                                                        exitEdit = true;
                                                    }
                                                }
                                            }
                                        } else {
                                            System.out.println("Доктора с таким id нету!");
                                        }
                                    } catch (InputMismatchException e) {
                                        System.out.println("Введите число!;");
                                    }
                                }

                            } else {
                                System.out.println("Больница с таким id не найден!");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Введите число!");
                            scanner.nextLine();
                        }
                    }
                }
                case "12" -> {
                    if (hospitalService.getAllHospital().isEmpty()) {
                        System.out.println("Больниц нету!");
                    } else {
                        System.out.println("Все больницы: ");
                        for (Hospital hospital : hospitalService.getAllHospital()) {
                            System.out.println(hospital);
                        }
                        try {
                            System.out.print("Выберите больницу по id: ");
                            Long id = scanner.nextLong();
                            Hospital foundHospital = null;
                            for (Hospital hospital : hospitalService.getAllHospital()) {
                                if (hospital.getId().equals(id)) {
                                    foundHospital = hospital;
                                    break;
                                }
                            }
                            if (foundHospital != null) {
                                if (foundHospital.getDepartments().isEmpty()) {
                                    System.out.println("Отделений нет!");
                                } else {
                                    try {
                                        System.out.print("Введите id отделения: ");
                                        Long idDepartment = scanner.nextLong();
                                        Department foundDepartment = getDepartment(foundHospital, idDepartment);
                                        if (foundDepartment != null) {
                                            boolean exitEdit = false;
                                            scanner.nextLine();
                                            while (!exitEdit) {
                                                System.out.println("""
                                                        Что вы хотите изменить?
                                                        1.Название отделения
                                                        2.Сохронить изменения""");
                                                String num2 = scanner.nextLine();
                                                switch (num2) {
                                                    case "1" ->
                                                            foundDepartment.setDepartmentName(inputString("Ввeдите название: "));
                                                    case "2" -> {
                                                        System.out.println(departmentDao.updateById(foundDepartment.getId(), foundDepartment));
                                                        exitEdit = true;
                                                    }
                                                }
                                            }
                                        } else {
                                            System.out.println("Отделения с таким id нету!");
                                        }
                                    } catch (InputMismatchException e) {
                                        System.out.println("Введите число!;");
                                    }
                                }

                            } else {
                                System.out.println("Больница с таким id не найден!");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Введите число!");
                            scanner.nextLine();
                        }
                    }
                }
                case "13" -> {
                    if (hospitalService.getAllHospital().isEmpty()) {
                        System.out.println("Больниц нету!");
                    } else {
                        System.out.println("Все больницы: ");
                        for (Hospital hospital : hospitalService.getAllHospital()) {
                            System.out.println(hospital);
                        }
                        try {
                            System.out.print("Выберите больницу по id: ");
                            Long id = scanner.nextLong();
                            Hospital foundHospital = null;
                            for (Hospital hospital : hospitalService.getAllHospital()) {
                                if (hospital.getId().equals(id)) {
                                    foundHospital = hospital;
                                    break;
                                }
                            }
                            if (foundHospital != null) {
                                if (foundHospital.getPatients().isEmpty()) {
                                    System.out.println("Пациентов нет!");
                                } else {
                                    while (true) {
                                        try {
                                            System.out.print("Введите id пациента: ");
                                            Patient result = patientService.getPatientById(scanner.nextLong());
                                            if (result != null) {
                                                System.out.println(result);
                                                break;
                                            } else {
                                                System.out.println("Пациента с таким id нету!");
                                            }
                                        } catch (InputMismatchException e) {
                                            System.out.println("Введите число!;");
                                        }
                                    }
                                }

                            } else {
                                System.out.println("Больница с таким id не найден!");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Введите число!");
                            scanner.nextLine();

                        }
                    }
                }
                case "14" -> {
                    if (hospitalService.getAllHospital().isEmpty()) {
                        System.out.println("Больниц нету!");
                    } else {
                        System.out.println("Все больницы: ");
                        for (Hospital hospital : hospitalService.getAllHospital()) {
                            System.out.println(hospital);
                        }
                        try {
                            System.out.print("Выберите больницу по id: ");
                            Long id = scanner.nextLong();
                            Hospital foundHospital = null;
                            for (Hospital hospital : hospitalService.getAllHospital()) {
                                if (hospital.getId().equals(id)) {
                                    foundHospital = hospital;
                                    break;
                                }
                            }
                            if (foundHospital != null) {
                                if (foundHospital.getPatients().isEmpty()) {
                                    System.out.println("Пациентов нет!");
                                } else {
                                    int age;
                                    System.out.print("Введите возраст: ");
                                    while (true) {
                                        if (scanner.hasNextInt()) {
                                            age = scanner.nextInt();
                                            break;
                                        } else {
                                            System.out.println("Введите число!");
                                            scanner.nextLine();
                                        }
                                    }
                                    Map<Integer, List<Patient>> mapPatient = patientService.getPatientByAge();
                                    List<Patient> foundPatients = mapPatient.get(age);
                                    if (foundPatients != null) {
                                        System.out.println("Все пациенты с возрастом " + age);
                                        for (Patient foundPatient : foundPatients) {
                                            System.out.println(foundPatient);
                                        }
                                    } else {
                                        System.out.println("Пациентов с таким возрастом нету!");
                                    }
                                }
                            } else {
                                System.out.println("Больница с таким id не найден!");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Введите число!");
                        }
                        scanner.nextLine();
                    }
                }
                case "15" -> {
                    if (hospitalService.getAllHospital().isEmpty()) {
                        System.out.println("Больниц нету!");
                    } else {
                        while (true) {
                            System.out.println("Как хотите сортировать: (asc/desc)");
                            String sortString = scanner.nextLine();
                            if (sortString.equals("ask")) {
                                List<Patient> sortedPatients = patientService.sortPatientsByAge(sortString);
                                System.out.println("Все пациенты по возрастанию: ");
                                for (Patient sortedPatient : sortedPatients) {
                                    System.out.println(sortedPatient);
                                }
                                break;
                            } else if (sortString.equals("desk")) {
                                List<Patient> sortedPatients = patientService.sortPatientsByAge(sortString);
                                System.out.println("Все пациенты по убыванию: ");
                                for (Patient sortedPatient : sortedPatients) {
                                    System.out.println(sortedPatient);
                                }
                                break;
                            } else {
                                System.out.println("Неправильное значение!");
                            }
                        }
                        scanner.nextLine();
                    }
                }
                case "16" -> {
                    if (hospitalService.getAllHospital().isEmpty()) {
                        System.out.println("Больниц нету!");
                    } else {
                        try {
                            System.out.print("Выберите больницу по id: ");
                            Long id = scanner.nextLong();
                            Hospital foundHospital = null;
                            for (Hospital hospital : hospitalService.getAllHospital()) {
                                if (hospital.getId().equals(id)) {
                                    foundHospital = hospital;
                                    break;
                                }
                            }
                            if (foundHospital != null) {
                                System.out.println(foundHospital);
                            } else {
                                System.out.println("Больница с таким id не найден!");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Введите число!");
                        }
                        scanner.nextLine();
                    }
                }
                case "17" -> {
                    if (hospitalService.getAllHospital().isEmpty()) {
                        System.out.println("Больниц нету!");
                    } else {
                        System.out.println("Все больницы: ");
                        for (Hospital hospital : hospitalService.getAllHospital()) {
                            System.out.println(hospital);
                        }
                    }
                }
                case "18" -> {
                    if (hospitalService.getAllHospital().isEmpty()) {
                        System.out.println("Больниц нету!");
                    } else {
                        System.out.println("Все больницы: ");
                        for (Hospital hospital : hospitalService.getAllHospital()) {
                            System.out.println(hospital);
                        }
                        try {
                            System.out.print("Выберите больницу по id: ");
                            Long id = scanner.nextLong();
                            Hospital foundHospital = null;
                            for (Hospital hospital : hospitalService.getAllHospital()) {
                                if (hospital.getId().equals(id)) {
                                    foundHospital = hospital;
                                    break;
                                }
                            }
                            if (foundHospital != null) {
                                System.out.println("Все пациенты: ");
                                for (Patient patient : foundHospital.getPatients()) {
                                    System.out.println(patient);
                                }
                            } else {
                                System.out.println("Больница с таким id не найден!");
                                scanner.nextLine();

                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Введите число!");
                            scanner.nextLine();

                        }
                    }
                }
                case "19" -> {
                    if (hospitalService.getAllHospital().isEmpty()) {
                        System.out.println("Больниц нету!");
                    } else {
                        System.out.println("Все больницы: ");
                        for (Hospital hospital : hospitalService.getAllHospital()) {
                            System.out.println(hospital);
                        }
                        try {
                            System.out.print("Выберите больницу по id: ");
                            Long id = scanner.nextLong();
                            boolean found = false;
                            for (Hospital hospital : hospitalService.getAllHospital()) {
                                if (hospital.getId().equals(id)) {
                                    found = true;
                                    break;
                                }
                            }
                            if (found) {
                                while (true) {
                                    try {
                                        System.out.print("Сколько больниц хотите найти по адресам: ");
                                        int count = scanner.nextInt();
                                        List<String> addresses = new ArrayList<>();
                                        for (int i = 0; i < count; i++) {
                                            System.out.print("Введите адрес: ");
                                            addresses.add(scanner.nextLine());
                                        }
                                        Map<String, Hospital> hospitalMap = hospitalService.getAllHospitalByAddress(addresses);
                                        if (hospitalMap.isEmpty()) {
                                            System.out.println("Больниц не обнаружено");
                                            scanner.nextLine();

                                        } else {
                                            for (Map.Entry<String, Hospital> entry : hospitalMap.entrySet()) {
                                                String address = entry.getKey();
                                                Hospital hospital = entry.getValue();
                                                System.out.println("Адрес: " + address + ", " + hospital);
                                            }
                                        }
                                        break;
                                    } catch (InputMismatchException e) {
                                        System.out.println("Введите число!");
                                        scanner.nextLine();
                                    }
                                }
                            } else {
                                System.out.println("Больница с таким id не найден!");
                                scanner.nextLine();

                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Введите число!");
                            scanner.nextLine();
                        }
                    }
                }
                case "20" -> {
                    if (hospitalService.getAllHospital().isEmpty()) {
                        System.out.println("Больниц нету!");
                    } else {
                        System.out.println("Все больницы: ");
                        for (Hospital hospital : hospitalService.getAllHospital()) {
                            System.out.println(hospital);
                        }
                        try {
                            System.out.print("Выберите больницу по id: ");
                            Long id = scanner.nextLong();
                            Hospital foundHospital = null;
                            for (Hospital hospital : hospitalService.getAllHospital()) {
                                if (hospital.getId().equals(id)) {
                                    foundHospital = hospital;
                                    break;
                                }
                            }
                            if (foundHospital != null) {
                                if (foundHospital.getDepartments().isEmpty() || foundHospital.getDoctors().isEmpty()) {
                                    System.out.println("Отделений или докторов нет!");
                                    scanner.nextLine();
                                } else {
                                    System.out.println("Все отделения: ");
                                    for (Department department : foundHospital.getDepartments()) {
                                        System.out.println(department);
                                    }
                                    System.out.println("Выберите отделение по id: ");
                                    Long idDepartment = scanner.nextLong();
                                    boolean found = false;
                                    for (Department department : foundHospital.getDepartments()) {
                                        if (department.getId().equals(idDepartment)) {
                                            found = true;
                                            break;
                                        }
                                    }
                                    if (found) {
                                        while (true) {
                                            System.out.print("Сколько докторов хотите добавить: ");
                                            int count = scanner.nextInt();
                                            if (count <= foundHospital.getDoctors().size()) {
                                                System.out.println("Все доктора: ");
                                                for (Doctor doctor : foundHospital.getDoctors()) {
                                                    System.out.println(doctor);
                                                }
                                                List<Long> doctorsId = new ArrayList<>();
                                                for (int i = 0; i < count; i++) {
                                                    try {
                                                        System.out.print("Выберите доктора по id: ");
                                                        Long idDoctor = scanner.nextLong();
                                                        boolean foundDoctor = false;
                                                        for (Doctor doctor : foundHospital.getDoctors()) {
                                                            if (doctor.getId().equals(idDoctor)) {
                                                                foundDoctor = true;
                                                                break;
                                                            }
                                                        }
                                                        if (foundDoctor) {
                                                            doctorsId.add(idDoctor);
                                                        } else {
                                                            throw new InputMismatchException();
                                                        }

                                                    } catch (InputMismatchException e) {
                                                        System.out.println("Неправильное id!");
                                                        scanner.nextLine();
                                                    }
                                                }
                                                System.out.println(doctorService.assignDoctorToDepartment(idDepartment, doctorsId));
                                                break;
                                            } else {
                                                System.out.println("Превышен лимит доступных докторов!");
                                            }
                                        }
                                    } else {
                                        System.out.println("Отделений не обнаружено!");
                                    }
                                }
                            } else {
                                System.out.println("Больница с таким id не найден!");
                                scanner.nextLine();

                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Введите число!");
                            scanner.nextLine();

                        }
                    }
                }
                case "21" -> {
                    if (hospitalService.getAllHospital().isEmpty()) {
                        System.out.println("Больниц нету!");
                    } else {
                        System.out.println("Все больницы: ");
                        for (Hospital hospital : hospitalService.getAllHospital()) {
                            System.out.println(hospital);
                        }
                        try {
                            System.out.print("Выберите больницу по id: ");
                            Long id = scanner.nextLong();
                            List<Doctor> doctors = doctorService.getAllDoctorsByHospitalId(id);
                            if (!doctors.isEmpty()) {
                                System.out.println("Все доктора: ");
                                for (Doctor doctor : doctors) {
                                    System.out.println(doctor);
                                }
                            } else {
                                System.out.println("Больница с таким id не найден!");
                            }

                        } catch (InputMismatchException e) {
                            System.out.println("Введите число!");
                        }
                        scanner.nextLine();
                    }
                }
                case "22" -> {
                    if (hospitalService.getAllHospital().isEmpty()) {
                        System.out.println("Больниц нету!");
                    } else {
                        System.out.println("Все больницы: ");
                        for (Hospital hospital : hospitalService.getAllHospital()) {
                            System.out.println(hospital);
                        }
                        try {
                            System.out.print("Выберите больницу по id: ");
                            Long id = scanner.nextLong();
                            Hospital foundHospital = null;
                            for (Hospital hospital : hospitalService.getAllHospital()) {
                                if (hospital.getId().equals(id)) {
                                    foundHospital = hospital;
                                    break;
                                }
                            }
                            if (foundHospital != null) {
                                System.out.println("Все отделения: ");
                                for (Department department : foundHospital.getDepartments()) {
                                    System.out.println(department);
                                }
                                System.out.print("Введите id отделения: ");
                                Long idDepartment = scanner.nextLong();
                                List<Doctor> doctors = doctorService.getAllDoctorsByDepartmentId(idDepartment);
                                if (!doctors.isEmpty()) {
                                    System.out.println("Все доктора: ");
                                    for (Doctor doctor : doctors) {
                                        System.out.println(doctor);
                                    }
                                } else {
                                    System.out.println("Отделения с таким id не обнаружено");
                                }
                            } else {
                                System.out.println("Больница с таким id не найден!");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Введите число!");
                            scanner.nextLine();

                        }
                    }
                }
                case "23" -> {
                    if (hospitalService.getAllHospital().isEmpty()) {
                        System.out.println("Больниц нету!");
                    } else {
                        System.out.println("Все больницы: ");
                        for (Hospital hospital : hospitalService.getAllHospital()) {
                            System.out.println(hospital);
                        }
                        try {
                            System.out.print("Выберите больницу по id: ");
                            Long id = scanner.nextLong();
                            List<Department> departments = departmentService.getAllDepartmentByHospital(id);
                            if (!departments.isEmpty()) {
                                System.out.println("Все отделения: ");
                                for (Department department : departments) {
                                    System.out.println(department);
                                }
                            } else {
                                System.out.println("Больница с таким id не найден!");
                            }

                        } catch (InputMismatchException e) {
                            System.out.println("Введите число!");
                        }
                        scanner.nextLine();
                    }
                }
                case "24" -> {
                    if (hospitalService.getAllHospital().isEmpty()) {
                        System.out.println("Больниц нету!");
                    } else {
                        System.out.println("Введите название отделения:");
                        String name = scanner.nextLine();
                        Department department = departmentService.findDepartmentByName(name);
                        if (department != null) {
                            System.out.println(department);
                        } else {
                            System.out.println("Отделение не обнаружено!");
                        }
                        scanner.nextLine();
                    }
                }
                case "25" -> {
                    exit = true;
                    System.out.println("Вы успешно вышли!");
                }
            }
        }

    }

    public static String inputString(String prompt) {
        System.out.print(prompt);
        while (true) {
            String name = scanner.nextLine();
            if (name.isEmpty()) {
                System.out.println("Значение не должно быть пустым!");
            } else {
                return name;
            }
        }
    }

    private static Doctor getDoctor(Hospital foundHospital, Long idDoctor) {
        Doctor foundDoctor = null;
        for (Doctor doctor : foundHospital.getDoctors()) {
            if (idDoctor.equals(doctor.getId())) {
                foundDoctor = new Doctor();
                foundDoctor.setGender(doctor.getGender());
                foundDoctor.setExperienceYear(doctor.getExperienceYear());
                foundDoctor.setLastName(doctor.getLastName());
                foundDoctor.setFirstName(doctor.getFirstName());
                foundDoctor.setId(doctor.getId());
            }
        }
        return foundDoctor;
    }

    private static Department getDepartment(Hospital foundHospital, Long idDoctor) {
        Department foundDepartment = null;
        for (Department department : foundHospital.getDepartments()) {
            if (idDoctor.equals(department.getId())) {
                foundDepartment = new Department();
                foundDepartment.setDepartmentName(department.getDepartmentName());
                foundDepartment.setId(department.getId());
            }
        }
        return foundDepartment;
    }
    private static Patient getPatient(Hospital foundHospital, Long idPatient) {
        Patient foundPatient = null;
        for (Patient patient : foundHospital.getPatients()) {
            if (idPatient.equals(patient.getId())) {
                foundPatient = new Patient();
                foundPatient.setGender(patient.getGender());
                foundPatient.setAge(patient.getAge());
                foundPatient.setLastName(patient.getLastName());
                foundPatient.setFirstName(patient.getFirstName());
                foundPatient.setId(patient.getId());
            }
        }
        return foundPatient;
    }
}