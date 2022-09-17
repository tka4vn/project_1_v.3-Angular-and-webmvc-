package com.tkach.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "STATUS")
public class Status {

    @Id
    @Column(name = "STATUS_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idStatus;

    @Column(name = "NAME")
    private String name;

    public Status() {
    }

    public Status(String name, Set<Request> requestList) {
        this.name = name;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
