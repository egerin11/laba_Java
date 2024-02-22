package com.example.laba.service;

import com.example.laba.model.Cat;
import com.example.laba.repository.CatRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@AllArgsConstructor
public class CatService implements Services {
    private static String catInfApiUrl = "https://catfact.ninja/{action}";

    private final RestTemplate restTemplate;
    private final CatRepository catRepository;
    @Override
    public String getInf(String action) {
        String url = catInfApiUrl.replace("{action}", action);
        return restTemplate.getForObject(url, String.class);
    }
    @Override
    public void saveInfCat(String action) throws JsonProcessingException {
        String json = getInf(action);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readValue(json, JsonNode.class);
        JsonNode dataArray = jsonNode.get("data");
        for (JsonNode catNode: dataArray) {
            Cat cat = mapper.convertValue(catNode, Cat.class);
            System.out.println(cat);
            catRepository.saveCat(cat);
        }
    }
}
