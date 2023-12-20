package services.impls;

import dao.impls.DoctorDaoImpl;
import models.Doctor;
import services.DoctorService;
import services.GenericService;

import java.util.List;

public class DoctorServiceImpl implements DoctorService, GenericService<Doctor> {
    private final DoctorDaoImpl doctorDao;

    public DoctorServiceImpl(DoctorDaoImpl doctorDao) {
        this.doctorDao = doctorDao;
    }

    @Override
    public Doctor findDoctorById(Long id) {
        return doctorDao.findDoctorById(id);
    }

    @Override
    public String assignDoctorToDepartment(Long departmentId, List<Long> doctorsId) {
        return doctorDao.assignDoctorToDepartment(departmentId, doctorsId);
    }

    @Override
    public List<Doctor> getAllDoctorsByHospitalId(Long id) {
        return doctorDao.getAllDoctorsByHospitalId(id);
    }

    @Override
    public List<Doctor> getAllDoctorsByDepartmentId(Long id) {
        return doctorDao.getAllDoctorsByDepartmentId(id);
    }


    @Override
    public String add(Long hospitalId, Doctor doctor) {
        return doctorDao.add(hospitalId, doctor);
    }

    @Override
    public String removeById(Long id) {
        return doctorDao.removeById(id);
    }

    @Override
    public String updateById(Long id, Doctor doctor) {
        return doctorDao.updateById(id, doctor);
    }
}
