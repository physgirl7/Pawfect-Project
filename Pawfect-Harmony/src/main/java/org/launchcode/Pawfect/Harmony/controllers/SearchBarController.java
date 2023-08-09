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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    @GetMapping("")
    public String showSearchPage(Model model) {
        model.addAttribute("searchBar", new SearchBar("", ""));
        return "search";
    }

    @PostMapping("/results")
    public String performSearch(@ModelAttribute("searchBar") SearchBar searchBar, Model model, HttpServletRequest request) {
        String query = searchBar.getTitle();

        List<AnimalProfile> searchResults = searchProfiles(query);
        HttpSession userSession = request.getSession();
        User user = authenticationController.getUserFromSession(userSession);

        model.addAttribute("query", query);
        model.addAttribute("results", searchResults);
        model.addAttribute("user", user);

        return "results";
    }

    private List<AnimalProfile> searchProfiles(String query) {
        List<AnimalProfile> searchResults = animalProfileRepository.searchProfiles(query);

        return searchResults;
    }


}