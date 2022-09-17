package com.tkach.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "INGRESS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Ingress {

    @Id
    @Column(name = "INGRESS_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idIngress;

    @ManyToOne
    @JoinColumn(name = "USERS_ID", referencedColumnName = "USERS_ID")
    private Users idIngUse;

    @ManyToOne
    @JoinColumn(name = "SERVICE_ID", referencedColumnName = "SERVICE_ID")
    private Services idIngSer;

    @ManyToOne
    @JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID")
    private Roles idIngRol;

    public Ingress() {
    }

    public Ingress(Users idIngUse, Services idIngSer, Roles idIngRol) {
        this.idIngUse = idIngUse;
        this.idIngSer = idIngSer;
        this.idIngRol = idIngRol;
    }

    public int getIdIngress() {
        return idIngress;
    }

    public void setIdIngress(int idIngress) {
        this.idIngress = idIngress;
    }

    public Users getIdIngUse() {
        return idIngUse;
    }

    @JsonProperty("idIngUse")
    public void setIdIngUse(Users idIngUse) {
        this.idIngUse = idIngUse;
    }

    public Services getIdIngSer() {
        return idIngSer;
    }

    @JsonProperty("idIngSer")
    public void setIdIngSer(Services idIngSer) {
        this.idIngSer = idIngSer;
    }

    public Roles getIdIngRol() {
        return idIngRol;
    }

    @JsonProperty("idIngRol")
    public void setIdIngRol(Roles idIngRol) {
        this.idIngRol = idIngRol;
    }
}
