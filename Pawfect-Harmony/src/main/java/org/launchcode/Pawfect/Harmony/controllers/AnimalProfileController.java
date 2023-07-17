package org.launchcode.Pawfect.Harmony.controllers;

import org.launchcode.Pawfect.Harmony.models.AnimalProfile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.launchcode.Pawfect.Harmony.data.AnimalProfileRepository;


@Controller
@RequestMapping("animalprofile")
public class AnimalProfileController {

    @GetMapping("myanimals")
    public String displayAnimalProfile(Model model, AnimalProfile animalProfile) {

        model.addAttribute("name", animalProfile.getName());
        model.addAttribute("location", animalProfile.getLocation());
        model.addAttribute("species", animalProfile.getSpecies());
        model.addAttribute("breed", animalProfile.getBreed());
        model.addAttribute("age", animalProfile.getAge());
        model.addAttribute("comments", animalProfile.getComments());

        return "animalprofile/myanimals";
    }

    @GetMapping("form")
    public String createAnimalForm(@ModelAttribute AnimalProfile animalProfile, Model model){
        model.addAttribute(new AnimalProfile());
        return "animalprofile/form";
    }

    //create submit page to redirect to myanimals page

}


