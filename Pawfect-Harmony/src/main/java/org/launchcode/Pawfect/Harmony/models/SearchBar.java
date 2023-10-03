package org.launchcode.Pawfect.Harmony.models;

public class SearchBar {

    private String title;

    private String description;

    private String location;

    private String species;

    private String gender;

    private String breed;

    public SearchBar(String title, String description, String location, String species, String gender, String breed) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.species = species;
        this.gender = gender;
        this.breed = breed;
    }

    public SearchBar() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getBreed() {
        return breed;
    }
    public void setBreed(String breed) {
        this.breed = breed;
    }
}
