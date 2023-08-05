package org.launchcode.Pawfect.Harmony.controllers;

import org.launchcode.Pawfect.Harmony.models.SearchBar;
import org.launchcode.Pawfect.Harmony.models.AnimalProfile;
import org.launchcode.Pawfect.Harmony.data.AnimalProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("search")
public class SearchBarController {

    @Autowired
    private AnimalProfileRepository animalProfileRepository;

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
}
