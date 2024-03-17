package com.poli.Pets.service;

import com.poli.Pets.entity.PetEntity;
import com.poli.Pets.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    PetRepository petRepository;

    public List<PetEntity> getAllPets(){
        return petRepository.findAll();
    }

    public Optional<PetEntity> getPetById(Integer id){
        return petRepository.findById(id);
    }

    public List<PetEntity> getPetByCedula(String id){
        return petRepository.dataByCedula(id);
    }

    public void savePet(PetEntity pet){
        petRepository.save(pet);
    }
    public void deletePet(Integer id){
        petRepository.deleteById(id);
    }

    public void updatePet(Integer id, PetEntity newPet){

        Optional<PetEntity> pet = petRepository.findById(id);

        if(pet.isPresent()){

            PetEntity updatedPet = pet.get();
            updatedPet.setName(newPet.getName());
            updatedPet.setAge(newPet.getAge());
            updatedPet.setRace(newPet.getRace());
            updatedPet.setWeight(newPet.getWeight());
            updatedPet.setIdCliente(newPet.getIdCliente());

            petRepository.save(updatedPet);
        }
    }

    public List<String> dataFromReport(String cedula){
        return petRepository.dataFromReport(cedula);
    }


}
