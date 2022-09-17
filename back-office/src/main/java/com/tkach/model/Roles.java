package com.tkach.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ROLE")
public class Roles {

    @Id
    @Column(name = "ROLE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRole;

    @Column(name = "NAME")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "ROLE_HAS_SERVICE",
            joinColumns = { @JoinColumn(name = "ROLE_ID_") },
            inverseJoinColumns = { @JoinColumn(name = "SERVICE_ID") }
    )
    @JsonIgnoreProperties(value = { "idSerRol" })
    private Set<Services> idRolSer = new HashSet<>();

    public Roles() {
    }

    public Roles(String name) {
        this.name = name;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Services> getIdRolSer() {
        return idRolSer;
    }

    @JsonProperty("idRolSer")
    public void setIdRolSer(Set<Services> idRolSer) {
        this.idRolSer = idRolSer;
    }
}
