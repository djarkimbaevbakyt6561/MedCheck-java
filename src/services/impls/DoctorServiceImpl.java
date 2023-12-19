package services.impls;

import models.Doctor;
import services.DoctorService;
import services.GenericService;

import java.util.List;

public class DoctorServiceImpl implements DoctorService, GenericService<DoctorServiceImpl> {
    @Override
    public Doctor findDoctorById(Long id) {
        return null;
    }

    @Override
    public String assignDoctorToDepartment(Long departmentId, List<Long> doctorsId) {
        return null;
    }

    @Override
    public List<Doctor> getAllDoctorsByHospitalId(Long id) {
        return null;
    }

    @Override
    public List<Doctor> getAllDoctorsByDepartmentId(Long id) {
        return null;
    }


    @Override
    public String add(Long hospitalId, DoctorServiceImpl doctorService) {
        return null;
    }

    @Override
    public void removeById(Long id) {

    }

    @Override
    public String updateById(Long id, DoctorServiceImpl doctorService) {
        return null;
    }

}
