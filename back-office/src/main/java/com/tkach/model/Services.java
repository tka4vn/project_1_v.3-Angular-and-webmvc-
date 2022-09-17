package com.tkach.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "SERVICE")
public class Services {

    @Id
    @Column(name = "SERVICE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idServices;

    @Column(name = "NAME")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "ROLE_HAS_SERVICE",
            joinColumns = { @JoinColumn(name = "SERVICE_ID") },
            inverseJoinColumns = { @JoinColumn(name = "ROLE_ID_") }
    )
    @JsonIgnoreProperties(value = { "idRolSer" })
    private Set<Roles> idSerRol = new HashSet<>();

    @OneToMany(mappedBy = "idIngSer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Ingress> idSerIng;

    public Services() {
    }

    public Services(String name) {
        this.name = name;
    }

    public int getIdServices() {
        return idServices;
    }

    public void setIdServices(int idServices) {
        this.idServices = idServices;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Set<Roles> getIdSerRol() {
        return idSerRol;
    }

    @JsonProperty("idSerRol")
    public void setIdSerRol(Set<Roles> idSerRol) {
        this.idSerRol = idSerRol;
    }


    public Set<Ingress> getIdSerIng() {
        return idSerIng;
    }

    public void setIdSerIng(Set<Ingress> idSerIng) {
        this.idSerIng = idSerIng;
    }
}
