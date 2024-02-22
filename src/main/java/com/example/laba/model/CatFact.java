package com.example.laba.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CatFact {
    @JsonProperty("fact")
    private String facts;

    private int length;

    @Override
    public String toString() {

        return facts + length;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getFacts() {
        return facts;
    }

    public CatFact() {
    }

    public void setFacts(String facts) {
        this.facts = facts;
    }

    public CatFact(String facts, int length) {
        this.facts = facts;
        this.length = length;
    }
}


