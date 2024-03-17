package com.poli.Pets.entity;

import com.poli.Pets.service.MedicineService;

import java.util.List;
import java.util.Optional;

public class DataReport {

    private List<PetEntity> pet;
    private ClientEntity client;
    private List<Optional<MedicineEntity>> medicine;

    public DataReport() {}

    public List<PetEntity> getPet() {
        return pet;
    }

    public void setPet(List<PetEntity> pet) {
        this.pet = pet;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    public List<Optional<MedicineEntity>> getMedicine() {
        return medicine;
    }

    public void setMedicine(List<Optional<MedicineEntity>> medicine) {
        this.medicine = medicine;
    }
}
