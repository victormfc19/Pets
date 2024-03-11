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

    public Optional<PetEntity> getAllPetById(String id){
        return petRepository.findById(id);
    }

    public void savePet(PetEntity pet){
        petRepository.save(pet);
    }

    public void deletePet(String id){
        petRepository.deleteById(id);
    }
}
