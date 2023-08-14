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
import  java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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

public SearchBarController(){
    columnChoices.put("all", "All");
    columnChoices.put("location", "Location");
    columnChoices.put("species", "Species");
    columnChoices.put("breed", "Breed");

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

}