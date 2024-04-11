package com.poli.Pets.entity;

import java.util.List;
import java.util.Optional;

public class PetReportEntity {

    private Optional<PetEntity> pet;
    private List<Optional<MedicineEntity>> medicines;

    public PetReportEntity(Optional<PetEntity> pet, List<Optional<MedicineEntity>> medicines) {
        this.pet = pet;
        this.medicines = medicines;
    }

    public Optional<PetEntity> getPet() {
        return pet;
    }

    public void setPet(Optional<PetEntity> pet) {
        this.pet = pet;
    }

    public List<Optional<MedicineEntity>> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Optional<MedicineEntity>> medicines) {
        this.medicines = medicines;
    }
}
