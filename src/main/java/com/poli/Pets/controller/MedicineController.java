package com.poli.Pets.controller;
import com.poli.Pets.entity.MedicineEntity;
import com.poli.Pets.service.MedicineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/api/v1")
@Slf4j
public class MedicineController {

    @Autowired
    private MedicineService medicineService;


    @GetMapping("/medicines")
    public List<MedicineEntity> getAllMedicines(){
        return medicineService.getAllMedicines();
    }

    @GetMapping("/medicines/{id}")
    public Optional<MedicineEntity> getMedicineById(@PathVariable String id){
        return medicineService.getMedicineById(id);
    }

    @PostMapping("/medicines")
    public MedicineEntity saveMedicine(@RequestBody MedicineEntity medicine){
        return medicineService.saveMedicine(medicine);
    }

    @PutMapping("/medicines/{id}")
    public MedicineEntity updateMedicine(@PathVariable String id, @RequestBody MedicineEntity medicine){
        return medicineService.updateMedicine(id, medicine);
    }

    @DeleteMapping("/medicines/{id}")
    public void deleteMedicine(@PathVariable String id){
        medicineService.deleteMedicine(id);
    }

}
