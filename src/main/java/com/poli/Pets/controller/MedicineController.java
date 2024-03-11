package com.poli.Pets.controller;


import com.poli.Pets.entity.MedicineEntity;
import com.poli.Pets.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class MedicineController {

    @Autowired
    MedicineService medicineService;

    @GetMapping("/medicines")
    public List<MedicineEntity> getAllMedicines(){
        return medicineService.getAllMedicines();
    }

    @GetMapping("/medicines/{id}")
    public Optional<MedicineEntity> getMedicineById(@PathVariable String id){
        return medicineService.getMedicineById(id);
    }

    @PostMapping("/medicines")
    public void saveMedicine(@RequestBody MedicineEntity medicine){
        medicineService.saveMedicine(medicine);
    }

    @DeleteMapping("/medicines/{id}")
    public void deleteMedicine(@PathVariable String id){
        medicineService.deleteMedicine(id);
    }


}
