package com.example.laba.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface  Services {
    String getInf(String action);
    void saveInfCat(String action) throws JsonProcessingException;
}


