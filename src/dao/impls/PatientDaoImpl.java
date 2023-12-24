package dao.impls;

import dao.GeneralDao;
import dao.PatientDao;
import db.Database;
import models.Hospital;
import models.Patient;

import java.util.*;
import java.util.stream.Collectors;

public class PatientDaoImpl implements PatientDao, GeneralDao<Patient> {
    private final Database database;

    public PatientDaoImpl(Database database) {
        this.database = database;
    }

    @Override
    public String add(Long hospitalId, Patient patient) {
        Hospital targetHospital = database.getHospitals().stream()
                .filter(hospital -> hospital.getId().equals(hospitalId))
                .findFirst()
                .orElse(null);

        if (targetHospital != null) {
            targetHospital.getPatients().add(patient);
            return "Пациент успешно добавлен!";
        }
        return null;
    }

    @Override
    public String removeById(Long id) {
        for (Hospital hospital : database.getHospitals()) {
            Patient patient = hospital.getPatients().stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
            if (patient != null) {
                hospital.getPatients().remove(patient);
                return "Пациент успешно удален!";
            }
        }
        return null;
    }

    @Override
    public String updateById(Long id, Patient patient) {
        for (Hospital hospital : database.getHospitals()) {
            Patient patient1 = hospital.getPatients().stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
            if (patient1 != null) {
                patient1.setFirstName(patient.getFirstName());
                patient1.setLastName(patient.getLastName());
                patient1.setAge(patient.getAge());
                patient1.setGender(patient.getGender());
                return "Пациент успешно обновлен!";
            }
        }
        return null;
    }

    @Override
    public String addPatientsToHospital(Long id, List<Patient> patients) {
        Hospital hospital = database.getHospitals().stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
        if(hospital != null){
            patients.forEach(x -> hospital.getPatients().add(x));
            return "Пациенты успешно добавлены!";
        }
        return null;
    }

    @Override
    public Patient getPatientById(Long id) {
        for (Hospital hospital : database.getHospitals()) {
            Patient patient = hospital.getPatients().stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
            if(patient != null){
                return patient;
            }
        }
        return null;
    }

    @Override
    public Map<Integer, List<Patient>> getPatientByAge() {
        return database.getHospitals().stream()
                .flatMap(hospital -> hospital.getPatients().stream())
                .collect(Collectors.groupingBy(Patient::getAge, LinkedHashMap::new, Collectors.toList()));
    }

    @Override
    public List<Patient> sortPatientsByAge(String ascOrDesc) {
        Comparator<Patient> ageComparator = Comparator.comparingInt(Patient::getAge);
        if ("desc".equals(ascOrDesc)) {
            ageComparator = ageComparator.reversed();
        }

        return database.getHospitals().stream()
                .flatMap(hospital -> hospital.getPatients().stream())
                .sorted(ageComparator)
                .collect(Collectors.toList());
    }
}
