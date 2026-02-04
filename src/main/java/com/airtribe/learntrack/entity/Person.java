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

    public Person(String name, int age) {
        setFirstAndLastName(name);
        this.age = age;
    }

    public Person(String name, int age, String email) {
        this(name, age);
        this.email = email;
    }

    private void setFirstAndLastName(String name) {
        String[] str = name.split(" ");
        this.firstName = str[0];
        this.lastName = str.length > 1 ? str[1] : "";
    }

    public String getName() {
        return firstName + " " + lastName;
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
