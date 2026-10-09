package com.airtribe.learntrack.entity;

import com.airtribe.learntrack.utils.IdGenerator;

public class Trainer extends Person{
    private Long trainerId = IdGenerator.getNextTrainerId();
    private String specialization;
    private boolean active;

    public Trainer() {
        super();
        this.trainerId = 0L;
        this.specialization = "";
        this.active = true;
    }

    public Trainer(String firstName, String lastName, int age, String specialization) {
        super(firstName, lastName, age);
        this.specialization = specialization;
        this.active = true;
    }

    public Long getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Long trainerId) {
        this.trainerId = trainerId;
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
        System.out.println("Trainer ID: " + trainerId);
        System.out.println("Specialization: " + specialization);
        System.out.println("Active: " + active);
    }
}
