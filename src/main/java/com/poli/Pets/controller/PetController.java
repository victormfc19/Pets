package com.poli.Pets.controller;
import com.poli.Pets.entity.PetEntity;
import com.poli.Pets.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/api/v1")
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/pets")
    public String getAllPets(Model model){

        List<PetEntity> pets = petService.getAllPets();
        model.addAttribute("pets", pets);

        return "pet";
    }

    @GetMapping("/pets/{id}")
    public Optional<PetEntity> getPetById(@PathVariable String id){
        return petService.getAllPetById(id);
    }

    @PostMapping("/pets")
    public String savePet(@RequestBody PetEntity pet){
        petService.savePet(pet);

        return "home";
    }

    @DeleteMapping("/pets/{id}")
    public void deletePet(@PathVariable String id){
        petService.deletePet(id);
    }

}
