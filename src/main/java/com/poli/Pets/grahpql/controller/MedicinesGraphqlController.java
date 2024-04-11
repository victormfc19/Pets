package com.poli.Pets.grahpql.controller;

import com.poli.Pets.entity.ClientEntity;
import com.poli.Pets.entity.MedicineEntity;
import com.poli.Pets.entity.PetEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static com.poli.Pets.AppConfig.*;

@Controller
@Slf4j
public class MedicinesGraphqlController {

    @Autowired
    private RestTemplate restTemplate;

    @QueryMapping()
    public Map[] getMedicines() {
        return restTemplate.getForObject(REST_API_MEDICINES, Map[].class);
    }

    @QueryMapping()
    public Map getMedicineById(@Argument String id) {
        return restTemplate.getForObject(REST_API_MEDICINES.concat("/" + id), Map.class);
    }

    @MutationMapping
    public Map saveMedicine(
            @Argument String id,
            @Argument String name,
            @Argument String description,
            @Argument Integer dose
    ){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        MedicineEntity medicine = new MedicineEntity(id, name, description, dose);
        HttpEntity<MedicineEntity> requestEntity = new HttpEntity<>(medicine, headers);

        return restTemplate.postForObject(REST_API_MEDICINES, requestEntity, Map.class);
    }

    @MutationMapping
    public void updateMedicine(
            @Argument String id,
            @Argument String name,
            @Argument String description,
            @Argument Integer dose
    ){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        MedicineEntity medicine = new MedicineEntity(id, name, description, dose);
        medicine.setId(id);
        HttpEntity<MedicineEntity> requestEntity = new HttpEntity<>(medicine, headers);

        restTemplate.exchange(REST_API_MEDICINES.concat("/" + id), HttpMethod.PUT, requestEntity, void.class );
    }


    @MutationMapping
    public void deleteMedicine(@Argument String id){
        restTemplate.delete(REST_API_MEDICINES.concat("/" + id));
    }

}
