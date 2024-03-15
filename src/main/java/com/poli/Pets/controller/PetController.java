package com.poli.Pets.controller;
import com.poli.Pets.entity.ClientEntity;
import com.poli.Pets.entity.DataForm;
import com.poli.Pets.entity.PetEntity;
import com.poli.Pets.service.ClientService;
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

    @Autowired
    private ClientService clientService;

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
    @GetMapping("/addPet")
    public String registerPetForm(Model model){

        DataForm data = new DataForm();
        model.addAttribute("dataForm", data);

        return "addPet";
    }

    @PostMapping("/addPet")
    public String savePet(@ModelAttribute("data") DataForm data){

        PetEntity pet = new PetEntity();
        pet.setName(data.getName());
        pet.setRace(data.getRace());
        pet.setAge(data.getAge());
        pet.setWeight( data.getWeight());
        pet.setIdCliente(data.getId_client());
        petService.savePet(pet);

        ClientEntity client = new ClientEntity();
        client.setId(data.getId_client());
        client.setNames(data.getNames());
        client.setLastNames(data.getLastNames());
        client.setAddress(data.getAddress());
        client.setPhone(data.getPhone());
        clientService.saveClient(client);

        return "redirect:pets";
    }


    @GetMapping("/pets/{id}")
    public Optional<PetEntity> getPetById(@PathVariable String id){
        return petService.getAllPetById(id);
    }


    @DeleteMapping("/pets/{id}")
    public void deletePet(@PathVariable String id){
        petService.deletePet(id);
    }
    @PutMapping("/pets")
    public void updatePet(@RequestBody PetEntity pet){
        petService.updatePet(pet);
    }

}
