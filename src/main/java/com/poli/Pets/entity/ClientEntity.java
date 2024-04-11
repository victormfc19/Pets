package com.poli.Pets.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "cliente")
public class ClientEntity {

    @Id
    @Column(name = "cedula")
    private String id;

    @Column(name = "nombres")
    private String names;

    @Column(name = "apellidos")
    private String lastNames;

    @Column(name = "direccion")
    private String address;

    @Column(name = "telefono")
    private String phone;

    public ClientEntity(String id, String names, String lastNames, String address, String phone) {
        this.id = id;
        this.names = names;
        this.lastNames = lastNames;
        this.address = address;
        this.phone = phone;
    }

    public ClientEntity() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getLastNames() {
        return lastNames;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
