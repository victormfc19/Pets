package com.poli.Pets.controller;

import com.poli.Pets.entity.ClientEntity;
import com.poli.Pets.entity.PetEntity;
import com.poli.Pets.service.ClientService;
import com.poli.Pets.service.DetailMedicineService;
import com.poli.Pets.service.MedicineService;
import com.poli.Pets.service.PetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class ReportController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private PetService petService;

    @Autowired
    private DetailMedicineService detailMedicine;

    @GetMapping("/report/{id}")
    public ResponseEntity<Map<String, Object>> getReport(@PathVariable String id){
        Map<String, Object> response = new HashMap<>();

        response.put("cliente", clientService.getClientById(id));
        response.put("detalle", petService.infoPetByCedula(id));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
