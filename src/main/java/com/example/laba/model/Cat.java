package com.example.laba.model;


import lombok.Data;

@Data
public class Cat {

    private String breed;
    private String country;
    private String origin;
    private String coat;
    private String pattern;

    @Override
    public String toString() {
        return breed + country + origin + coat + pattern;
    }

    public Cat() {
    }


    public Cat(String breed, String country, String origin, String coat, String pattern) {
        this.breed = breed;
        this.country = country;
        this.origin = origin;
        this.coat = coat;
        this.pattern = pattern;
    }


    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getCoat() {
        return coat;
    }

    public void setCoat(String coat) {
        this.coat = coat;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
