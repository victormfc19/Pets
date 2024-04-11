package com.poli.Pets.grahpql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import java.util.Map;
import static com.poli.Pets.AppConfig.REST_API_REPORT;

@Controller
public class ReportGraphqlController {

    @Autowired
    private RestTemplate restTemplate;

    @QueryMapping()
    public Map getReport(@Argument String id) {
        return restTemplate.getForObject(REST_API_REPORT.concat("/" + id), Map.class);
    }
}
