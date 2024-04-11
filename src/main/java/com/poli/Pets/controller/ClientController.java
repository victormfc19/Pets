package com.poli.Pets.controller;

import com.poli.Pets.entity.ClientEntity;
import com.poli.Pets.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/clients")
    public List<ClientEntity> getAllClients(){
        return clientService.getAllClients();
    }

    @GetMapping("/clients/{id}")
    public Optional<ClientEntity> getClientById(@PathVariable String id){
        return clientService.getClientById(id);
    }

    @PostMapping("/clients")
    public ClientEntity saveClient(@RequestBody ClientEntity client){
        return clientService.saveClient(client);
    }

    @PutMapping("/clients/{id}")
    public ClientEntity updateClient(@PathVariable String id, @RequestBody ClientEntity client){
        return clientService.updateClient(id, client);
    }

    @DeleteMapping("/clients/{id}")
    public void deleteClient(@PathVariable String id){
        clientService.deleteClient(id);
    }

}
