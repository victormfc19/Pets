package com.poli.Pets.controller;
import com.poli.Pets.entity.*;
import com.poli.Pets.service.DetailMedicineService;
import com.poli.Pets.service.PetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/api/v1")
@Slf4j
public class PetController {

    @Autowired
    private PetService petService;

    @Autowired
    private DetailMedicineService detailMedicineService;

    @GetMapping("/pets")
    public List<PetEntity> getAllPets(){
        return petService.getAllPets();
    }

    @GetMapping("/pets/{id}")
    public Optional<PetEntity> getPetById(@PathVariable Integer id){
        return petService.getPetById(id);
    }
    @PostMapping("/pets")
    public PetEntity savePet(@RequestBody PetEntity pet){ //, @RequestParam(required = false) String[] medicamentos){
        //if(medicamentos != null)
        //    for (String medicamento : medicamentos)
        //        detailMedicineService.save(new DetailMedicineEntity(pet.getId(), medicamento));

        return petService.savePet(pet);
    }

    @PutMapping("/pets/{id}")
    public PetEntity updatePet(@PathVariable Integer id, @RequestBody PetEntity newPet){
        log.info("Ingresé al metodo PUT");
        return petService.updatePet(id, newPet);
    }

    @DeleteMapping("/pets/{id}")
    public ResponseEntity<Object> deletePet(@PathVariable Integer id){
        petService.deletePet(id);
        return ResponseEntity
                .status(202)
                .body("Mascota eliminada exitosamente");
    }

}
