package com.poli.Pets.controller;


import com.poli.Pets.entity.ClientEntity;
import com.poli.Pets.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping("/clients")
    public List<ClientEntity> getAllClients(){
        return clientService.getAllClients();
    }

    @GetMapping("/clients/{id}")
    public Optional<ClientEntity> getClientById(@PathVariable String id){
        return clientService.getClientById(id);
    }

    @PostMapping("/clients")
    public void saveClient(@RequestBody ClientEntity client){
        clientService.saveClient(client);
    }

    @DeleteMapping("clients/{id}")
    public void deleteClient(@PathVariable String id){
        clientService.deleteClient(id);
    }


}
