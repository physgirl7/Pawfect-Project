package org.launchcode.Pawfect.Harmony.models;

import java.util.ArrayList;
import java.util.HashMap;

public class AnimalProfileData {

    public static ArrayList<AnimalProfile> findByColumnAndValue(String column, String value, Iterable<AnimalProfile> allAnimals) {

        ArrayList<AnimalProfile> results = new ArrayList<>();

        if (value.toLowerCase().equals("all")){
            return (ArrayList<AnimalProfile>) allAnimals;
        }

        if (column.equals("all")){
            results = findByValue(value, allAnimals);
            return results;
        }
        for (AnimalProfile animal : allAnimals) {

            String aValue = getFieldValue(animal, column);

            if (aValue != null && aValue.toLowerCase().contains(value.toLowerCase())) {
                results.add(animal);
            }
        }

        return results;
    }

    public static String getFieldValue(AnimalProfile animal, String fieldName){
        String theValue;
        if (fieldName.equals("name")){
            theValue = animal.getName();
        } else if (fieldName.equals("location")){
            theValue = animal.getLocation();
        } else if (fieldName.equals("breed")){
            theValue = animal.getBreed();
        } else {
            theValue = animal.getSpecies();
        }
        return theValue;
    }

    public static ArrayList<AnimalProfile> findByValue(String value, Iterable<AnimalProfile> allAnimals) {
        String lower_val = value.toLowerCase();

        ArrayList<AnimalProfile> results = new ArrayList<>();

        for (AnimalProfile animal : allAnimals) {
            if (animal.getName().toLowerCase().contains(lower_val)) {
                results.add(animal);
            } else if (animal.getLocation().toLowerCase().contains(lower_val)) {
                results.add(animal);
            } else if (animal.getSpecies().toLowerCase().contains(lower_val)) {
                results.add(animal);
            } else if (animal.getBreed().toLowerCase().contains(lower_val)) {
                    results.add(animal);
            }
        }
        return results;
    }
}





    /**
     * Returns the results of searching the Jobs data by field and search term.
     *
     * For example, searching for employer "Enterprise" will include results
     * with "Enterprise Holdings, Inc".
     *
     * @param column Job field that should be searched.
     * @param value Value of the field to search for.
     * @param allJobs The list of jobs to search.
     * @return List of all jobs matching the criteria.
     */

    /**
     * Search all Job fields for the given term.
     *
     * @param value The search term to look for.
     * @param allJobs The list of jobs to search.
     * @return      List of all jobs with at least one field containing the value.
     */



