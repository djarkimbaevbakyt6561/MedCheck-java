package dao.impls;

import dao.DepartmentDao;
import dao.GeneralDao;
import db.Database;
import models.Department;
import models.Hospital;

import java.util.List;
import java.util.Optional;

public class DepartmentDaoImpl implements DepartmentDao, GeneralDao<Department> {
    private final Database database;

    public DepartmentDaoImpl(Database database) {
        this.database = database;
    }

    @Override
    public List<Department> getAllDepartmentByHospital(Long id) {
        Hospital hospital = database.getHospitals().stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
        if (hospital != null) {
            return hospital.getDepartments();
        }
        return null;
    }

    @Override
    public Department findDepartmentByName(String name) {
        return database.getHospitals().stream()
                .flatMap(hospital -> hospital.getDepartments().stream())
                .filter(department -> department.getDepartmentName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String add(Long hospitalId, Department department) {
        Hospital targetHospital = database.getHospitals().stream()
                .filter(hospital -> hospital.getId().equals(hospitalId))
                .findFirst()
                .orElse(null);

        if (targetHospital != null) {
            targetHospital.getDepartments().add(department);
            return "Отделение успешно добавлено!";
        }

        return null;
    }

    @Override
    public String removeById(Long id) {
        Optional<Hospital> hospitalToRemoveFrom = database.getHospitals().stream()
                .filter(hospital -> hospital.getDepartments().stream()
                        .anyMatch(department -> department.getId().equals(id)))
                .findFirst();

        if (hospitalToRemoveFrom.isPresent()) {
            Hospital hospital = hospitalToRemoveFrom.get();
            hospital.getDepartments().removeIf(department -> department.getId().equals(id));
            return "Отделение успешно удалено!";
        }

        return null;
    }

    @Override
    public String updateById(Long id, Department department) {
        for (Hospital hospital : database.getHospitals()) {
            Department department1 = hospital.getDepartments().stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
            if (department1 != null) {
                department1.setDepartmentName(department.getDepartmentName());
                return "Отделение успешно обновлено!";
            }
        }
        return null;
    }
}
