package com.example.laba.model;

public class CatFact {
    private String facts;

    private int length;
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


