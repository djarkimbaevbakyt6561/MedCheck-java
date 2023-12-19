package db;

import models.Hospital;

import java.util.ArrayList;
import java.util.List;

public class Database {
   private final List<Hospital> hospitals = new ArrayList<>();

    public List<Hospital> getHospitals() {
        return hospitals;
    }
}
