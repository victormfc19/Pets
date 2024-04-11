package com.poli.Pets.service;

import com.poli.Pets.entity.MedicineEntity;
import com.poli.Pets.entity.PetEntity;
import com.poli.Pets.entity.PetReportEntity;
import com.poli.Pets.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    PetRepository petRepository;

    @Autowired
    private DetailMedicineService detailMedicineService;

    public List<PetEntity> getAllPets(){
        return petRepository.findAll();
    }

    public Optional<PetEntity> getPetById(Integer id){
        return petRepository.findById(id);
    }

    public PetEntity savePet(PetEntity pet){
        return petRepository.save(pet);
    }
    public void deletePet(Integer id){
        petRepository.deleteById(id);
    }

    public PetEntity updatePet(Integer id, PetEntity newPet){

        Optional<PetEntity> pet = petRepository.findById(id);

        if(pet.isPresent()){

            PetEntity updatedPet = pet.get();
            updatedPet.setName(newPet.getName());
            updatedPet.setAge(newPet.getAge());
            updatedPet.setRace(newPet.getRace());
            updatedPet.setWeight(newPet.getWeight());
            updatedPet.setIdCliente(newPet.getIdCliente());

            return petRepository.save(updatedPet);
        }
        return null;
    }


    public List<PetReportEntity> infoPetByCedula(String cedula){

        List<String> infoPets = petRepository.infoByCedula(cedula);
        List<Optional<PetEntity>> pets = new ArrayList<>();

        List<PetReportEntity> reportePet = new ArrayList<>();

        infoPets.forEach(pet -> {

            String[] datos = pet.split(",");
            Integer idPet = Integer.valueOf(datos[0]);
            List<Optional<MedicineEntity>> medicines = detailMedicineService.infoMedicinesByPet(idPet);

            reportePet.add( new PetReportEntity( getPetById(idPet), medicines ));
        });

        return reportePet;
    }

    public List<PetEntity> petsByCedula(String cedula){
        return petRepository.petsFromClient(cedula);
    }
}
