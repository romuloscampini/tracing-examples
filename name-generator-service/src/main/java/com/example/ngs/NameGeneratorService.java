package com.example.ngs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static strman.Strman.toKebabCase;

@SpringBootApplication
@EnableFeignClients
public class NameGeneratorService {

    public static void main(String[] args) {
        SpringApplication.run(NameGeneratorService.class, args);
    }

}


@FeignClient(name = "scientist-service-client", url = "${scientist.service.prefix.url}")
interface ScientistServiceClient {

    @GetMapping("/api/v1/scientists/random")
    String randomScientistName();

}

@FeignClient(name = "animal-service-client", url = "${animal.service.prefix.url}")
interface AnimalServiceClient {

    @GetMapping("/api/v1/animals/random")
    String randomAnimalName();

}


@RestController
@RequestMapping("/api/v1/names")
class NameResource {

    @Autowired
    private AnimalServiceClient animalServiceClient;
    @Autowired
    private ScientistServiceClient scientistServiceClient;

    @Value("${animal.service.prefix.url}")
    private String animalServiceUrl;

    @Value("${scientist.service.prefix.url}")
    private String scientistServiceUrl;


    @GetMapping(path = "/random")
    public String name() throws Exception {
        String animal = animalServiceClient.randomAnimalName();
        String scientist = scientistServiceClient.randomScientistName();
        String name = toKebabCase(scientist) + "-" + toKebabCase(animal);
        return name;
    }

    @GetMapping(path = "/restRandom")
    public String restName() throws Exception {
//        String animal = animalServiceClient.randomAnimalName();
//        String scientist = scientistServiceClient.randomScientistName();
        String animal = doRequestAndGetBody(animalServiceUrl + "/api/v1/animals/random");
        String scientist = doRequestAndGetBody(scientistServiceUrl + "/api/v1/scientists/random");
        String name = toKebabCase(scientist) + "-" + toKebabCase(animal);
        return name;
    }

    private String doRequestAndGetBody(String url) {
        RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<String> response
                = restTemplate.getForEntity(url, String.class);
        String body = response.getBody();
        return body;
    }


}
