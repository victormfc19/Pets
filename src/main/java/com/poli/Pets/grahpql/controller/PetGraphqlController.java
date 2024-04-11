package com.poli.Pets.grahpql.controller;

import com.poli.Pets.entity.PetEntity;
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
import static com.poli.Pets.AppConfig.REST_API_PETS;


@Controller
public class PetGraphqlController {

    @Autowired
    private RestTemplate restTemplate;

    @QueryMapping()
    public Map[] getPets() {
        return restTemplate.getForObject(REST_API_PETS, Map[].class);
    }

    @QueryMapping()
    public Map getPetById(@Argument Integer id) {
        return restTemplate.getForObject(REST_API_PETS.concat("/" + id), Map.class);
    }

    @MutationMapping
    public Map savePet(
            @Argument String name,
            @Argument String race,
            @Argument Integer age,
            @Argument Integer weight,
            @Argument String idCliente
    ){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        PetEntity pet = new PetEntity(name, race, age, weight, idCliente);
        HttpEntity<PetEntity> requestEntity = new HttpEntity<>(pet, headers);

        return restTemplate.postForObject(REST_API_PETS, requestEntity, Map.class);
    }

    @MutationMapping
    public void updatePet(
            @Argument Integer id,
            @Argument String name,
            @Argument String race,
            @Argument Integer age,
            @Argument Integer weight,
            @Argument String idCliente
    ){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        PetEntity pet = new PetEntity(name, race, age, weight, idCliente);
        pet.setId(id);
        HttpEntity<PetEntity> requestEntity = new HttpEntity<>(pet, headers);

        restTemplate.exchange(REST_API_PETS.concat("/" + id), HttpMethod.PUT, requestEntity, void.class );
    }

    @MutationMapping
    public void deletePet(@Argument Integer id){
        restTemplate.delete(REST_API_PETS.concat("/" + id));
    }

}
