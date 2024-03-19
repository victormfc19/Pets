package com.poli.Pets.entity;

import java.util.List;

public class Pet {

    private PetEntity pet;
    private List<MedicineEntity> medicines;


    public Pet() {
    }

    public PetEntity getPet() {
        return pet;
    }

    public void setPet(PetEntity pet) {
        this.pet = pet;
    }

    public List<MedicineEntity> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<MedicineEntity> medicines) {
        this.medicines = medicines;
    }
}
