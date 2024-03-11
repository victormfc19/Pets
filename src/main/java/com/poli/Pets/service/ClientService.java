package com.poli.Pets.service;


import com.poli.Pets.entity.ClientEntity;
import com.poli.Pets.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public List<ClientEntity> getAllClients(){
        return clientRepository.findAll();
    }

    public Optional<ClientEntity> getClientById(String id){
        return clientRepository.findById(id);
    }

    public void saveClient(ClientEntity client){
        clientRepository.save(client);
    }

    public void deleteClient(String id){
        clientRepository.deleteById(id);
    }

}
