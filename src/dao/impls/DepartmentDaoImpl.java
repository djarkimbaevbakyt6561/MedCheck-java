package dao.impls;

import dao.DepartmentDao;
import dao.GeneralDao;
import db.Database;
import models.Department;
import models.Hospital;

import java.util.List;

public class DepartmentDaoImpl implements DepartmentDao, GeneralDao<Department> {
    private final Database database;

    public DepartmentDaoImpl(Database database) {
        this.database = database;
    }

    @Override
    public List<Department> getAllDepartmentByHospital(Long id) {
        for (Hospital hospital : database.getHospitals()) {
            if (hospital.getId().equals(id)) {
                return hospital.getDepartments();
            }
        }
        return null;
    }

    @Override
    public Department findDepartmentByName(String name) {
        for (Hospital hospital : database.getHospitals()) {
            for (Department department : hospital.getDepartments()) {
                if (department.getDepartmentName().equals(name)) {
                    return department;
                }
            }
        }
        return null;
    }

    @Override
    public String add(Long hospitalId, Department department) {
        for (Hospital hospital : database.getHospitals()) {
            if (hospital.getId().equals(hospitalId)) {
                hospital.getDepartments().add(department);
                return "Отделение успешно добавлено!";
            }
        }
        return null;
    }

    @Override
    public String removeById(Long id) {
        for (Hospital hospital : database.getHospitals()) {
            for (Department department : hospital.getDepartments()) {
                if (department.getId().equals(id)) {
                    hospital.getDepartments().remove(department);
                    return "Отделение успешно удалено!";
                }
            }
        }
        return null;
    }

    @Override
    public String updateById(Long id, Department department) {
        for (Hospital hospital : database.getHospitals()) {
            for (int i = 0; i < hospital.getDepartments().size(); i++) {
                if (hospital.getDepartments().get(i).getId().equals(id)) {
                    hospital.getDepartments().set(i, department);
                    return "Отделение успешно обновлено!";
                }
            }
        }
        return null;
    }
}
