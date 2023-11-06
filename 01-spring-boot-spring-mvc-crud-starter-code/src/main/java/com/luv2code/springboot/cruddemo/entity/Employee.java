package com.luv2code.springboot.cruddemo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="employee")
public class Employee {

    // define fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="organization_name")
    private String organizationName;

    @Column(name="email")
    private String email;

    @Column(name="pov_number")
    private String povNumber;


    // define constructors
    public Employee() {

    }

    public Employee(String firstName, String lastName, String organizationName, String email, String povNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.organizationName = organizationName;
        this.email = email;
        this.povNumber = povNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPovNumber() {
        return povNumber;
    }

    public void setPovNumber(String povNumber) {
        this.povNumber = povNumber;
    }

    // define toString
    @Override
    public String toString() {
        return "Employee{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", organizationName='"+ organizationName+ '\'' +
                ", email='" + email + '\'' +
                ", povNumber='" + povNumber +'\'' +
                '}';
    }
}








