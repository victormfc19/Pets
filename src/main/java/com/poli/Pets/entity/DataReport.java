package com.poli.Pets.entity;
import java.util.List;


public class DataReport {

    private List<Pet> pet;
    private ClientEntity client;

    public DataReport() {}

    public List<Pet> getPet() {
        return pet;
    }

    public void setPet(List<Pet> pet) {
        this.pet = pet;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

}

