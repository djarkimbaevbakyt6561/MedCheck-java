package dao.impls;

import dao.HospitalDao;
import db.Database;
import models.Hospital;
import models.Patient;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HospitalDaoImpl implements HospitalDao {
    private final Database database;

    public HospitalDaoImpl(Database database) {
        this.database = database;
    }

    @Override
    public String addHospital(Hospital hospital) {
        database.getHospitals().add(hospital);
        return "Больница успешно добавлена!";
    }

    @Override
    public Hospital findHospitalById(Long id) {
        for (Hospital hospital : database.getHospitals()) {
            if(hospital.getId().equals(id)){
                return hospital;
            }
        }
        return null;
    }

    @Override
    public List<Hospital> getAllHospital() {
        return database.getHospitals();
    }

    @Override
    public List<Patient> getAllPatientFromHospital(Long id) {
        for (Hospital hospital : database.getHospitals()) {
            if(hospital.getId().equals(id)){
                return hospital.getPatients();
            }
        }
        return null;
    }

    @Override
    public String deleteHospitalById(Long id) {
        for (Hospital hospital : database.getHospitals()) {
            if(hospital.getId().equals(id)){
                database.getHospitals().remove(hospital);
                return "Больница успешно удалена!";
            }
        }
        return null;
    }

    @Override
    public Map<String, Hospital> getAllHospitalByAddress(List<String> addresses) {
        Map<String, Hospital> hospitalMap = new LinkedHashMap<>();
        for (Hospital hospital : database.getHospitals()) {
            for (String address : addresses) {
                if(hospital.getAddress().equals(address)){
                    hospitalMap.put(address, hospital);
                }
            }
        }
        return hospitalMap;
    }
}
