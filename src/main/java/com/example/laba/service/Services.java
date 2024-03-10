package com.example.laba.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public interface  Services {
    JsonNode getInf(String action);
    void saveInfCat(String action) throws JsonProcessingException;
}


