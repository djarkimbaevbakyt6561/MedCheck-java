package dao.impls;

import dao.GeneralDao;
import dao.PatientDao;
import db.Database;
import models.Hospital;
import models.Patient;

import java.util.*;

public class PatientDaoImpl implements PatientDao, GeneralDao<Patient> {
    private final Database database;

    public PatientDaoImpl(Database database) {
        this.database = database;
    }

    @Override
    public String add(Long hospitalId, Patient patient) {
        for (Hospital hospital : database.getHospitals()) {
            if(hospital.getId().equals(hospitalId)){
                hospital.getPatients().add(patient);
                return "Пациент успешно добавлен!";
            }
        }
        return null;
    }

    @Override
    public String removeById(Long id) {
        for (Hospital hospital : database.getHospitals()) {
            for (Patient patient : hospital.getPatients()) {
                if(patient.getId().equals(id)){
                    hospital.getPatients().remove(patient);
                    return "Пациент успешно удален!";
                }
            }
        }
        return null;
    }

    @Override
    public String updateById(Long id, Patient patient) {
        for (Hospital hospital : database.getHospitals()) {
            for (int i = 0; i < hospital.getPatients().size(); i++) {
                if(hospital.getPatients().get(i).getId().equals(id)){
                    hospital.getPatients().set(i, patient);
                    return "Пациент успешно обновлен!";
                }
            }
        }
        return null;
    }

    @Override
    public String addPatientsToHospital(Long id, List<Patient> patients) {
        for (Hospital hospital : database.getHospitals()) {
            if(hospital.getId().equals(id)){
                for (Patient patient : patients) {
                    hospital.getPatients().add(patient);
                }
                return "Пациенты успешно добавлены!";
            }
        }
        return null;
    }

    @Override
    public Patient getPatientById(Long id) {
        for (Hospital hospital : database.getHospitals()) {
            for (Patient patient : hospital.getPatients()) {
                if(patient.getId().equals(id)){
                    return patient;
                }
            }
        }
        return null;
    }

    @Override
    public Map<Integer, List<Patient>> getPatientByAge() {
       Map<Integer, List<Patient>> patientMap = new LinkedHashMap<>();
        for (Hospital hospital : database.getHospitals()) {
            for (Patient patient : hospital.getPatients()) {
                int age = patient.getAge();
                patientMap.computeIfAbsent(age, k -> new ArrayList<>()).add(patient);
            }
        }
        return patientMap;
    }

    @Override
    public List<Patient> sortPatientsByAge(String ascOrDesc) {
        List<Patient> patients = new ArrayList<>();
        for (Hospital hospital : database.getHospitals()) {
            patients.addAll(hospital.getPatients());
        }
        Comparator<Patient> ageComparator = Comparator.comparingInt(Patient::getAge);
        if (ascOrDesc.equals("desc")) {
            ageComparator = ageComparator.reversed();
        }
        patients.sort(ageComparator);

        return patients;

    }
}
