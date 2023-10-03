package org.launchcode.Pawfect.Harmony.controllers;

import org.launchcode.Pawfect.Harmony.data.UserRepository;
import org.launchcode.Pawfect.Harmony.models.AnimalProfileData;
import org.launchcode.Pawfect.Harmony.models.SearchBar;
import org.launchcode.Pawfect.Harmony.models.AnimalProfile;
import org.launchcode.Pawfect.Harmony.data.AnimalProfileRepository;
import org.launchcode.Pawfect.Harmony.data.UserRepository;
import org.launchcode.Pawfect.Harmony.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
@RequestMapping("search")
public class SearchBarController {

    @Autowired
    private AnimalProfileRepository animalProfileRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationController authenticationController;

    static HashMap<String, String> columnChoices = new HashMap<>();

    static HashMap<String, String> columnChoicesAdvanced = new HashMap<>();

public SearchBarController(){
    columnChoices.put("all", "All");
    columnChoices.put("location", "Location");
    columnChoices.put("species", "Species");
    columnChoices.put("breed", "Breed");
    columnChoicesAdvanced.put("location", "Location");
    columnChoicesAdvanced.put("species", "Species");
    columnChoicesAdvanced.put("breed", "Breed");

}

    @RequestMapping("")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm){
        Iterable<AnimalProfile> animals;
        if (searchTerm.toLowerCase().equals("all") || searchTerm.equals("")){
            animals = animalProfileRepository.findAll();
        } else {
            animals = AnimalProfileData.findByColumnAndValue(searchType, searchTerm, animalProfileRepository.findAll());
        }
        model.addAttribute("columns", columnChoices);
        model.addAttribute("title", "Pets by " + columnChoices.get(searchType) + ": " + searchTerm);
        model.addAttribute("animals", animals);

        return "search";
    }

    @RequestMapping("advanced")
    public String advancedSearch(Model model) {
        model.addAttribute("columns", columnChoicesAdvanced);
        SearchBar advancedSearch = new SearchBar();
        model.addAttribute("advancedSearch", advancedSearch);
        return "advancedsearch";
    }

    @PostMapping("advanced/results")
    public String displayAdvancedSearchResults(Model model, @ModelAttribute SearchBar advancedSearch){
        List<AnimalProfile> animals = new ArrayList<>();
        if(advancedSearch.getLocation().equals("all")){
        advancedSearch.setLocation("%");
        }
        if(advancedSearch.getSpecies().equals("all")){
            advancedSearch.setSpecies("%");
        }
        if(advancedSearch.getGender().equals("all")){
            advancedSearch.setGender("%");
        }
        if(advancedSearch.getBreed().equals("")){
            advancedSearch.setBreed("%");
        }
    animals= animalProfileRepository.findByLocationLikeAndSpeciesLikeAndGenderLikeAndBreedLikeIgnoreCase(advancedSearch.getLocation(), advancedSearch.getSpecies(), advancedSearch.getGender(), advancedSearch.getBreed());
        model.addAttribute("columns", columnChoicesAdvanced);
        model.addAttribute("advancedSearch", advancedSearch);
        model.addAttribute("animals", animals);
        return "advancedsearch";
    }
}