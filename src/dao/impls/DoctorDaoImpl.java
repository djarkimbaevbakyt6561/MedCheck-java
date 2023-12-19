package dao.impls;

import dao.DoctorDao;
import dao.GeneralDao;
import db.Database;
import models.Department;
import models.Doctor;
import models.Hospital;

import java.util.ArrayList;
import java.util.List;

public class DoctorDaoImpl implements DoctorDao, GeneralDao<Doctor> {
    private final Database database;

    public DoctorDaoImpl(Database database) {
        this.database = database;
    }

    @Override
    public Doctor findDoctorById(Long id) {
        for (Hospital hospital : database.getHospitals()) {
            for (Doctor doctor : hospital.getDoctors()) {
                if(doctor.getId().equals(id)){
                    return doctor;
                }
            }
        }
        return null;
    }

    @Override
    public String assignDoctorToDepartment(Long departmentId, List<Long> doctorsId) {
        for (Hospital hospital : database.getHospitals()) {
            List<Doctor> foundedDoctors = new ArrayList<>();
            for (Long id : doctorsId) {
                for (Doctor doctor : hospital.getDoctors()) {
                    if(doctor.getId().equals(id)){
                        foundedDoctors.add(doctor);
                    }
                }
            }
            for (Department department : hospital.getDepartments()) {
                if(department.getId().equals(departmentId)){
                    if(foundedDoctors.isEmpty()){
                        return "Докторов не обнаружено!";
                    } else {
                        for (Doctor foundedDoctor : foundedDoctors) {
                            department.getDoctors().add(foundedDoctor);
                        }
                        return "Докторы успешно добавлены!";
                    }
                }
            }
        }
        return null;
    }

    @Override
    public List<Doctor> getAllDoctorsByHospitalId(Long id) {
        for (Hospital hospital : database.getHospitals()) {
            if(hospital.getId().equals(id)){
                return hospital.getDoctors();
            }
        }
        return null;
    }

    @Override
    public List<Doctor> getAllDoctorsByDepartmentId(Long id) {
        for (Hospital hospital : database.getHospitals()) {
            for (Department department : hospital.getDepartments()) {
                if(department.getId().equals(id)){
                    return department.getDoctors();
                }
            }
        }
        return null;
    }

    @Override
    public String add(Long hospitalId, Doctor doctor) {
        for (Hospital hospital : database.getHospitals()) {
            if(hospital.getId().equals(hospitalId)){
                hospital.getDoctors().add(doctor);
                return "Доктор успешно добавлен!";
            }
        }
        return null;
    }

    @Override
    public String removeById(Long id) {
        for (Hospital hospital : database.getHospitals()) {
            for (Doctor doctor : hospital.getDoctors()) {
                if(doctor.getId().equals(id)){
                    hospital.getDoctors().remove(doctor);
                    return "Доктор успешно удален!";
                }
            }
        }
        return null;
    }

    @Override
    public String updateById(Long id, Doctor doctor) {
        for (Hospital hospital : database.getHospitals()) {
            for (int i = 0; i < hospital.getDoctors().size(); i++) {
                if(hospital.getDoctors().get(i).getId().equals(id)){
                    hospital.getDoctors().set(i, doctor);
                    return "Доктор успешно обновлен!";
                }
            }
        }
        return null;
    }
}
