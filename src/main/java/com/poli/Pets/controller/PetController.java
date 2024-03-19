package com.poli.Pets.controller;
import com.poli.Pets.entity.*;
import com.poli.Pets.service.ClientService;
import com.poli.Pets.service.DetailMedicineService;
import com.poli.Pets.service.MedicineService;
import com.poli.Pets.service.PetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@Controller
@RequestMapping("/api/v1")
@Slf4j
public class PetController {

    @Autowired
    private PetService petService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private DetailMedicineService detailMedicineService;

    @Autowired
    private MedicineService medicineService;

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
    public String registerPetForm( Model model){
        DataForm data = new DataForm();

        model.addAttribute("dataForm", data);
        model.addAttribute("medicines", medicineService.getAllMedicines());

        return "addPet";
    }

    @PostMapping("/addPet")
    public String savePet(@ModelAttribute("data") DataForm data, @RequestParam(value = "opciones", required = false) List<String> opciones){

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

        if (opciones != null)
            for (String opcion : opciones) {

                DetailMedicineEntity newMedicine = new DetailMedicineEntity();
                newMedicine.setId_pet(pet.getId());
                newMedicine.setId_medicine(opcion);
                detailMedicineService.save(newMedicine);

            }


        return "redirect:pets";
    }

    @GetMapping("/pets/edit/{id}")
    public String editFormPet(@PathVariable("id") Integer id, Model model){
        model.addAttribute("id", id);
        model.addAttribute("mascota_editada", petService.getPetById(id));
        model.addAttribute("medicines", medicineService.getAllMedicines());
        return "editPet";
    }

    @PostMapping("/petsUpdated/{id}")
    public String updatePet(@PathVariable Integer id, @ModelAttribute("mascota_editada") PetEntity pet, @RequestParam(value = "opciones", required = false) List<String> opciones){

        detailMedicineService.deleteAllById(id);
        petService.updatePet(id, pet);

        if (opciones != null)
            for (String opcion : opciones) {
                DetailMedicineEntity newMedicine = new DetailMedicineEntity();
                newMedicine.setId_pet(id);
                newMedicine.setId_medicine(opcion);
                detailMedicineService.save(newMedicine);
            }

        return "redirect:/api/v1/pets";
    }


    @GetMapping("/pets/delete/{id}")
    public String deletePetFromId(@PathVariable("id") Integer id){

        List<DetailMedicineEntity> listMedicines = detailMedicineService.findAll();

        if(listMedicines.size() > 0)
            for(DetailMedicineEntity medicine: listMedicines){
                log.info("Medicine: " + medicine.getId_medicine());
                log.info("Mascota: " + medicine.getId_pet());
                detailMedicineService.delete(medicine);
            }


        petService.deletePet(id);
        return "redirect:/api/v1/pets";
    }

    @GetMapping("/viewReport")
    public String report(Model model){

        List<ClientEntity> clients = clientService.getAllClients();



        List<DataReport> listData = new ArrayList<>();

        for(ClientEntity c: clients){

            DataReport data = new DataReport();
            List<String> infoPets = petService.infoPetByCedula(c.getId());
            List<Pet> petsByClient = new ArrayList<>();

            infoPets.forEach(pet -> {

                String[] datosPet;
                datosPet = pet.split(",");
                List<String> infoMedicines = detailMedicineService.infoMedicinesByPet( Integer.valueOf(datosPet[0] ));
                PetEntity petEntity = new PetEntity(Integer.valueOf(datosPet[0]), datosPet[1], datosPet[2], Integer.parseInt(datosPet[3]), Integer.parseInt(datosPet[4]));
                Pet dataPet = new Pet();
                dataPet.setPet(petEntity);
                List<MedicineEntity> medicineByPet = new ArrayList<>();
                infoMedicines.forEach(medicine -> {

                    String[] datosMedicamento;
                    datosMedicamento = medicine.split(",");
                    medicineByPet.add( new MedicineEntity( datosMedicamento[0], datosMedicamento[1], Integer.parseInt(datosMedicamento[2])));

                });

                dataPet.setMedicines(medicineByPet);
                petsByClient.add(dataPet);

            });

            data.setPet(petsByClient);
            data.setClient(c);
            listData.add(data);

        }



        model.addAttribute("listaCompleta", listData);
        return "viewReport";
    }

}
