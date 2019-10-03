package com.example.test.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Employee {

    @Id
    @GeneratedValue
    int employeeNr;
    String nameEmployee;

    public Employee() {
        this ("");
        this.employeeNr = 0;
    }

    public Employee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }
}
