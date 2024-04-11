package com.poli.Pets.service;

import com.poli.Pets.entity.DetailMedicineEntity;
import com.poli.Pets.entity.MedicineEntity;
import com.poli.Pets.entity.PetEntity;
import com.poli.Pets.repository.DetailMedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DetailMedicineService {

    @Autowired
    private DetailMedicineRepository detailMedicine;

    @Autowired
    private MedicineService medicineService;

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

    public List<Optional<MedicineEntity>> infoMedicinesByPet(Integer id){
        List<String> infoMedicine = detailMedicine.infoMedicinesByPet(id);

        List<Optional<MedicineEntity>> medicines = new ArrayList<>();

        infoMedicine.forEach(medicine -> {
            String[] datos = medicine.split(",");
            String idPet = datos[0];

            medicines.add( medicineService.getMedicineById(idPet) );
        });

        return medicines;
    }

}
