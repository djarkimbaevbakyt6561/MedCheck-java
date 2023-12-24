package dao.impls;

import dao.DoctorDao;
import dao.GeneralDao;
import db.Database;
import models.Department;
import models.Doctor;
import models.Hospital;

import java.util.List;
import java.util.Optional;

public class DoctorDaoImpl implements DoctorDao, GeneralDao<Doctor> {
    private final Database database;

    public DoctorDaoImpl(Database database) {
        this.database = database;
    }

    @Override
    public Doctor findDoctorById(Long id) {
        return database.getHospitals().stream()
                .flatMap(hospital -> hospital.getDoctors().stream())
                .filter(doctor -> doctor.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public String assignDoctorToDepartment(Long departmentId, List<Long> doctorsId) {
        Optional<Hospital> hospitalWithDepartment = database.getHospitals().stream()
                .filter(hospital -> hospital.getDepartments().stream()
                        .anyMatch(department -> department.getId().equals(departmentId)))
                .findFirst();

        if (hospitalWithDepartment.isPresent()) {
            Hospital hospital = hospitalWithDepartment.get();
            Department department = hospital.getDepartments().stream()
                    .filter(dep -> dep.getId().equals(departmentId))
                    .findFirst()
                    .orElse(null);

            if (department != null) {
                List<Doctor> foundDoctors = hospital.getDoctors().stream()
                        .filter(doctor -> doctorsId.contains(doctor.getId()))
                        .toList();

                if (foundDoctors.isEmpty()) {
                    return "Докторов не обнаружено!";
                } else {
                    department.getDoctors().addAll(foundDoctors);
                    return "Докторы успешно добавлены!";
                }
            }
        }

        return null;
    }

    @Override
    public List<Doctor> getAllDoctorsByHospitalId(Long id) {
        return database.getHospitals().stream()
                .filter(hospital -> hospital.getId().equals(id))
                .findFirst()
                .map(Hospital::getDoctors)
                .orElse(null);
    }

    @Override
    public List<Doctor> getAllDoctorsByDepartmentId(Long id) {
        return database.getHospitals().stream()
                .flatMap(hospital -> hospital.getDepartments().stream())
                .filter(department -> department.getId().equals(id))
                .findFirst()
                .map(Department::getDoctors)
                .orElse(null);
    }

    @Override
    public String add(Long hospitalId, Doctor doctor) {
        Hospital targetHospital = database.getHospitals().stream()
                .filter(hospital -> hospital.getId().equals(hospitalId))
                .findFirst()
                .orElse(null);

        if (targetHospital != null) {
            targetHospital.getDoctors().add(doctor);
            return "Доктор успешно добавлен!";
        }

        return null;
    }

    @Override
    public String removeById(Long id) {
        for (Hospital hospital : database.getHospitals()) {
            Doctor doctor = hospital.getDoctors().stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
            if (doctor != null) {
                hospital.getDoctors().remove(doctor);
                return "Доктор успешно удален!";
            }
        }
        return null;
    }

    @Override
    public String updateById(Long id, Doctor doctor) {
        for (Hospital hospital : database.getHospitals()) {
            Doctor doctor1 = hospital.getDoctors().stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
            if (doctor1 != null) {
                doctor1.setFirstName(doctor.getFirstName());
                doctor1.setLastName(doctor.getLastName());
                doctor1.setExperienceYear(doctor.getExperienceYear());
                doctor1.setGender(doctor.getGender());
                return "Доктор успешно обновлен!";
            }
        }
        return null;
    }
}
