package com.example.laba.service;

import com.example.laba.model.Breed;
import com.example.laba.model.CatFact;
import com.example.laba.repository.CatInfRepository;
import com.example.laba.repository.CatRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@RequiredArgsConstructor
public class CatService implements Services {

    private static String catInfApiUrl = "https://catfact.ninja/{action}";

    private final RestTemplate restTemplate;
    private final CatRepository catRepository;
    private final CatInfRepository catInfRepository;

    @Override
    public JsonNode getInf(String action) {
        String url = catInfApiUrl.replace("{action}", action);
        return restTemplate.getForObject(url, JsonNode.class);
    }


    @Override
    public void saveInfCat(String action) throws JsonProcessingException {
        JsonNode json = getInf(action);
        JsonNode dataArray = json.get("data");
        if (action.equals("fact")) {
            String fact = json.get("fact").asText();
            int length = json.get("length").asInt();
            CatFact catFact = new CatFact(fact, length);
            catInfRepository.saveCatFact(catFact);
            return;
        }
        for (JsonNode catNode : dataArray) {
            if (action.equals("breeds")) {
                String breed = catNode.get("breed").asText();
                String country = catNode.get("country").asText();
                String origin = catNode.get("origin").asText();
                String coat = catNode.get("coat").asText();
                String pattern = catNode.get("pattern").asText();
                Breed cat = new Breed(breed, country, origin, coat, pattern);
                catRepository.saveCat(cat);
            } else if (action.equals("facts")) {
                String fact = catNode.get("fact").asText();
                int length = catNode.get("length").asInt();
                CatFact catFact = new CatFact(fact, length);
                catInfRepository.saveCatFact(catFact);
            }
        }
    }
}
