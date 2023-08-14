package org.launchcode.Pawfect.Harmony.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class AnimalProfile extends AbstractEntity {

    //fields

    @ManyToOne
    private User user;

    private String photo;
//    @NotBlank
    private String name;
//    @NotBlank
    private String location;

//    @NotBlank
    private String species;


    private String breed;

//    @NotBlank
    private String gender;

    private int age;

    private String comments;


    //constructor
    public AnimalProfile(String photo, String name, String location, String species, String breed, String gender, int age, String comments) {
        this.photo = photo;
        this.name = name;
        this.location = location;
        this.species = species;
        this.breed = breed;
        this.gender = gender;
        this.age = age;
        this.comments = comments;
    }

    //no arg constructor
    public AnimalProfile() {
    }

    // getters setters


    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}


