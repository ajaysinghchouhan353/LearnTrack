package com.airtribe.learntrack.entity;

public class Person {
    private String firstName;
    private String lastName;
    private int age;
    private String email;

    public Person() {
        this.firstName = "";
        this.lastName = "";
        this.age = 0;
        this.email = "";
    }

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public Person(String firstName, String lastName, int age, String email) {
        this(firstName, lastName, age);
        this.email = email;
    }

    public String getName() {
        if(lastName.isEmpty()) {
            return firstName;
        }
        return firstName + " " + lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    void displayInfo() {
        System.out.println("Name: " + getName());
        System.out.println("Age: " + age);
        System.out.println("Email: " + email);
    }
}
