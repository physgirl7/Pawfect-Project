package org.launchcode.Pawfect.Harmony.models;

import javax.persistence.Entity;

@Entity
public class AnimalProfileModel extends AbstractEntity {

//fields
    private String name;
    private String location;
    private String breed;
    private String species;
    private int age;
    private String comments;

    //figure out how to match if a user is wanting to adopt(match them with available animals)

    //how to add picture?


    //constructor
    public AnimalProfileModel(String name, String location, String breed, String species, int age, String comments) {
        this.name = name;
        this.location = location;
        this.breed = breed;
        this.species = species;
        this.age = age;
        this.comments = comments;
    }

//no arg constructor
    public AnimalProfileModel() {
    }

// getters setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}