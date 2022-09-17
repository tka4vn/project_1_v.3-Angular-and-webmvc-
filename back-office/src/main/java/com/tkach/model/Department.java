package com.tkach.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "DEPARTMENT")
public class Department {

    @Id
    @Column(name = "DEPARTMENT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "idAppDep", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Appointment> idDepApp;

    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Appointment> getIdDepApp() {
        return idDepApp;
    }

    public void setIdDepApp(Set<Appointment> idDepApp) {
        this.idDepApp = idDepApp;
    }
}
