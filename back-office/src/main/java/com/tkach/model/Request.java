package com.tkach.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "REQUEST")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Request {

    @Id
    @Column(name = "REQUEST_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "DATE_REQUEST")
    private String dateReq;

    @ManyToOne
    @JoinColumn(name = "USERS_ID", referencedColumnName = "USERS_ID")
    @JsonIgnoreProperties(value = { "idRolSer", "password", "active", "idUseEmp" })
    private Users idReqUse;

    @ManyToOne
    @JoinColumn(name = "SERVICE_ID", referencedColumnName = "SERVICE_ID")
    @JsonIgnoreProperties(value = { "idSerRol" })
    private Services idReqSer;

    @ManyToOne
    @JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID")
    @JsonIgnoreProperties(value = { "idRolSer" })
    private Roles idReqRol;

    @ManyToOne
    @JoinColumn(name = "STATUS_ID", referencedColumnName = "STATUS_ID")
    private Status idReqSta;

    public Request() {
    }

    public Request(String dateReq, Users idReqUse, Services idReqSer, Roles idReqRol, Status idReqSta) {
        this.dateReq = dateReq;
        this.idReqUse = idReqUse;
        this.idReqSer = idReqSer;
        this.idReqRol = idReqRol;
        this.idReqSta = idReqSta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateReq() {
        return dateReq;
    }

    public void setDateReq(String dateReq) {
        this.dateReq = dateReq;
    }

    public Users getIdReqUse() {
        return idReqUse;
    }

    public void setIdReqUse(Users idReqUse) {
        this.idReqUse = idReqUse;
    }

    public Services getIdReqSer() {
        return idReqSer;
    }

    public void setIdReqSer(Services idReqSer) {
        this.idReqSer = idReqSer;
    }

    public Roles getIdReqRol() {
        return idReqRol;
    }

    public void setIdReqRol(Roles idReqRol) {
        this.idReqRol = idReqRol;
    }

    public Status getIdReqSta() {
        return idReqSta;
    }

    public void setIdReqSta(Status idReqSta) {
        this.idReqSta = idReqSta;
    }
}
