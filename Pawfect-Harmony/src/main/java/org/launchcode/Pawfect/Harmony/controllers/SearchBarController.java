package org.launchcode.Pawfect.Harmony.controllers;

import org.launchcode.Pawfect.Harmony.data.UserRepository;
import org.launchcode.Pawfect.Harmony.models.SearchBar;
import org.launchcode.Pawfect.Harmony.models.AnimalProfile;
import org.launchcode.Pawfect.Harmony.data.AnimalProfileRepository;
import org.launchcode.Pawfect.Harmony.data.UserRepository;
import org.launchcode.Pawfect.Harmony.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("search")
public class SearchBarController {

    @Autowired
    private AnimalProfileRepository animalProfileRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public String showSearchPage(Model model) {
        model.addAttribute("searchBar", new SearchBar("", ""));
        return "search";
    }

    @PostMapping("/results")
    public String performSearch(@ModelAttribute("searchBar") SearchBar searchBar, Model model) {
        String query = searchBar.getTitle();

        List<AnimalProfile> searchResults = searchProfiles(query);

        model.addAttribute("query", query);
        model.addAttribute("results", searchResults);

        return "results";
    }

    private List<AnimalProfile> searchProfiles(String query) {
        List<AnimalProfile> searchResults = animalProfileRepository.searchProfiles(query);

        return searchResults;
    }

    @GetMapping("animalprofile/animalfile/{animalProfileId}")
    public String showAnimalProfile(@PathVariable int animalProfileId, Model model) {
        Optional<AnimalProfile> animalProfile = animalProfileRepository.findById(animalProfileId);
        if (animalProfile.isPresent()) {
            User user = userRepository.findByUsername();
            model.addAttribute("animalProfile", animalProfile.get());
            model.addAttribute("user", user);
            return "animalprofile/animalfile";
        } else {
            return "search";
        }
    }