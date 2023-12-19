package models;

import abstracts.Person;

public class Doctor extends Person {
    private int experienceYear;

    public int getExperienceYear() {
        return experienceYear;
    }

    public void setExperienceYear(int experienceYear) {
        this.experienceYear = experienceYear;
    }
}
