package com.poli.Pets.service;

import com.poli.Pets.entity.MedicineEntity;
import com.poli.Pets.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicineService {

    @Autowired
    MedicineRepository medicineRepository;

    public List<MedicineEntity> getAllMedicines(){
        return medicineRepository.findAll();
    }

    public Optional<MedicineEntity> getMedicineById(String id){
        return medicineRepository.findById(id);
    }

    public void saveMedicine(MedicineEntity medicine){
        medicineRepository.save(medicine);
    }

    public void deleteMedicine(String id){
        medicineRepository.deleteById(id);
    }
}
