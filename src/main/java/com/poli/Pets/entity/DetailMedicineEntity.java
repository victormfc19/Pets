package com.poli.Pets.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "detalle_medicamento")
public class DetailMedicineEntity {


    @Column(name = "id_mascota")
    private Integer id_pet;

    @Id
    @Column(name = "id_medicamento")
    private String id_medicine;

    public DetailMedicineEntity() {}

    public DetailMedicineEntity(Integer id_pet, String id_medicine) {
        this.id_pet = id_pet;
        this.id_medicine = id_medicine;
    }

    public Integer getId_pet() {
        return id_pet;
    }

    public void setId_pet(Integer id_pet) {
        this.id_pet = id_pet;
    }

    public String getId_medicine() {
        return id_medicine;
    }

    public void setId_medicine(String id_medicine) {
        this.id_medicine = id_medicine;
    }
}
