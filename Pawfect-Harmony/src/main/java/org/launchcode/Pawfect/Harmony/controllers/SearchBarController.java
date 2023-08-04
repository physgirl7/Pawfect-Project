package org.launchcode.Pawfect.Harmony.controllers;

import org.launchcode.Pawfect.Harmony.models.SearchBar;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("searchbar")
public class SearchBarController {

        @GetMapping("")
        public String showSearchPage(Model model) {
            model.addAttribute("searchBar", new SearchBar("", ""));
            return "search";
        }

    @PostMapping("")
    public String performSearch(@ModelAttribute("searchBar") SearchBar searchBar, Model model) {
        String breed = searchBar.getTitle();
        String location = searchBar.getDescription();

        String[] results = getDummySearchResults(breed, location);

        model.addAttribute("breed", breed);
        model.addAttribute("location", location);
        model.addAttribute("results", results);

        return "searchresults";
    }

    private String[] getDummySearchResults(String breed, String location) {
        // Dummy search results
        return new String[]{
                "Result 1: Animal with breed " + breed + " in " + location,
                "Result 2: Another animal with breed " + breed + " in " + location
        };
    }
}
