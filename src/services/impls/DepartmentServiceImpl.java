package services.impls;

import dao.impls.DepartmentDaoImpl;
import models.Department;
import services.DepartmentService;
import services.GenericService;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService, GenericService<Department> {
    private final DepartmentDaoImpl departmentDao;

    public DepartmentServiceImpl(DepartmentDaoImpl departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public List<Department> getAllDepartmentByHospital(Long id) {
        return departmentDao.getAllDepartmentByHospital(id);
    }

    @Override
    public Department findDepartmentByName(String name) {
        return departmentDao.findDepartmentByName(name);
    }

    @Override
    public String add(Long hospitalId, Department department) {
        return departmentDao.add(hospitalId, department);
    }

    @Override
    public String removeById(Long id) {
        return departmentDao.removeById(id);
    }

    @Override
    public String updateById(Long id, Department department) {
        return departmentDao.updateById(id, department);
    }

}
