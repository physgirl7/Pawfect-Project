package org.launchcode.Pawfect.Harmony.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class AnimalProfile extends AbstractEntity {

    //fields


    private String name;


    private String location;


    private String species;


    private String breed;


    private int age;


    private String comments;

    //figure out how to match if a user is wanting to adopt(match them with available animals)

    //how to add picture?


    //constructor
    public AnimalProfile(String name, String location, String species, String breed, int age, String comments) {
        this.name = name;
        this.location = location;
        this.species = species;
        this.breed = breed;
        this.age = age;
        this.comments = comments;
    }

    //no arg constructor
    public AnimalProfile() {
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

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
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


