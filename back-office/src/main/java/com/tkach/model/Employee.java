package com.tkach.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {

    @Id
    @Column(name = "EMPLOYEE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "FULL_NAME")
    private String fullName;

    @OneToMany(mappedBy = "idAppEmp", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Appointment> idEmpApp;

    public Employee() {
    }

    public Employee(String fullName) {
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Set<Appointment> getIdEmpApp() {
        return idEmpApp;
    }

    public void setIdEmpApp(Set<Appointment> idEmpApp) {
        this.idEmpApp = idEmpApp;
    }
}
