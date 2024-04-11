package com.poli.Pets.grahpql.controller;
import com.poli.Pets.entity.ClientEntity;
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
import static com.poli.Pets.AppConfig.REST_API_CLIENTS;
import static com.poli.Pets.AppConfig.REST_API_PETS;


@Controller
public class ClientGraphqlController {

    @Autowired
    private RestTemplate restTemplate;

    @QueryMapping()
    public Map[] getClients() {
        return restTemplate.getForObject(REST_API_CLIENTS, Map[].class);
    }

    @QueryMapping()
    public Map getClientById(@Argument String id) {
        return restTemplate.getForObject(REST_API_CLIENTS.concat("/" + id), Map.class);
    }

    @MutationMapping
    public Map saveClient(
            @Argument String id,
            @Argument String names,
            @Argument String lastNames,
            @Argument String address,
            @Argument String phone
    ){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ClientEntity client = new ClientEntity(id, names, lastNames, address, phone);
        HttpEntity<ClientEntity> requestEntity = new HttpEntity<>(client, headers);

        return restTemplate.postForObject(REST_API_CLIENTS, requestEntity, Map.class);
    }

    @MutationMapping
    public void updateClient(
            @Argument String id,
            @Argument String names,
            @Argument String lastNames,
            @Argument String address,
            @Argument String phone
    ){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ClientEntity client = new ClientEntity(id, names, lastNames, address, phone);
        client.setId(id);
        HttpEntity<ClientEntity> requestEntity = new HttpEntity<>(client, headers);

        restTemplate.exchange(REST_API_CLIENTS.concat("/" + id), HttpMethod.PUT, requestEntity, void.class );
    }

    @MutationMapping
    public void deleteClient(@Argument String id){
        restTemplate.delete(REST_API_CLIENTS.concat("/" + id));
    }

}
