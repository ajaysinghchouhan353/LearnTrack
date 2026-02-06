package com.airtribe.learntrack.entity;

import com.airtribe.learntrack.utils.IdGenerator;

public class Trainer extends Person{
    private Long trainerID;
    private String specialization;
    private boolean active;

    public Trainer() {
        super();
        this.trainerID = 0L;
        this.specialization = "";
        this.active = true;
    }

    public Trainer(String name, int age, String specialization) {
        super(name, age);
        this.trainerID = IdGenerator.getNextTrainerId();
        this.specialization = specialization;
        this.active = true;
    }

    public Long getTrainerID() {
        return trainerID;
    }

    public void setTrainerID(Long trainerID) {
        this.trainerID = trainerID;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void displayInfo() {
        super.displayInfo();
        System.out.println("Trainer ID: " + trainerID);
        System.out.println("Specialization: " + specialization);
        System.out.println("Active: " + active);
    }
}
