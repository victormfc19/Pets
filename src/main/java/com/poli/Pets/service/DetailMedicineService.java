package com.poli.Pets.service;

import com.poli.Pets.entity.DetailMedicineEntity;
import com.poli.Pets.repository.DetailMedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetailMedicineService {

    @Autowired
    private DetailMedicineRepository detailMedicine;

    public List<DetailMedicineEntity> findAll(){
        return detailMedicine.findAll();
    }

    public Optional<DetailMedicineEntity> findById(Integer id){
        return detailMedicine.findById(id);
    }

    public void save(DetailMedicineEntity medicine){
        detailMedicine.save(medicine);
    }

    public void delete(DetailMedicineEntity medicine){
        detailMedicine.delete(medicine);
    }

    public int deleteAllById(Integer id){
        return detailMedicine.deleteAllById(id);
    }

}
