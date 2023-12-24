package dao.impls;

import dao.HospitalDao;
import db.Database;
import models.Hospital;
import models.Patient;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
       return database.getHospitals().stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Hospital> getAllHospital() {
        return database.getHospitals();
    }

    @Override
    public List<Patient> getAllPatientFromHospital(Long id) {
        Hospital hospital = database.getHospitals().stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
        if(hospital !=null){
            return hospital.getPatients();
        }
        return null;
    }

    @Override
    public String deleteHospitalById(Long id) {
        boolean removed = database.getHospitals().removeIf(hospital -> hospital.getId().equals(id));
        return removed ? "Больница успешно удалена!" : null;
    }

    @Override
    public Map<String, Hospital> getAllHospitalByAddress(List<String> addresses) {
        return database.getHospitals().stream()
                .filter(hospital -> addresses.contains(hospital.getAddress()))
                .collect(Collectors.toMap(Hospital::getAddress, Function.identity(), (existing, replacement) -> existing, LinkedHashMap::new));
    }
}
