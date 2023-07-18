package org.launchcode.Pawfect.Harmony.controllers;

import org.launchcode.Pawfect.Harmony.models.AnimalProfile;
import org.launchcode.Pawfect.Harmony.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.launchcode.Pawfect.Harmony.data.AnimalProfileRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("animalprofile")
public class AnimalProfileController {

    @Autowired
    private AnimalProfileRepository animalProfileRepository;


    @GetMapping("myanimals")
    public String displayAnimalProfile(Model model) {
        List<AnimalProfile> animalProfiles = (List<AnimalProfile>) animalProfileRepository.findAll();
        model.addAttribute("animalProfiles", animalProfiles);
        return "animalprofile/myanimals";
    }

    @GetMapping("/form")
    public String createAnimalForm(Model model) {
        model.addAttribute(new AnimalProfile());
        return "animalprofile/form";
    }

    @PostMapping("/form")
    public String processAnimalForm(@ModelAttribute @Valid AnimalProfile animalProfile, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "/form";
        }
        animalProfileRepository.save(animalProfile);
        return "redirect:/animalprofile/myanimals";
    }

    @GetMapping("/edit/{animalProfileId}")
    public String editAnimalProfile(Model model, @PathVariable int animalProfileId) {
        Optional<AnimalProfile> result = animalProfileRepository.findById(animalProfileId);
        if (result.isPresent()) {
            AnimalProfile animalProfile = result.get();
            model.addAttribute("animalProfile", animalProfile);
            return "animalprofile/edit";
        }
        return "animalprofile/myanimals";
    }

    @PostMapping("/edit/{animalProfileId}")
    public String processEditAnimalProfile(@PathVariable int animalProfileId, @ModelAttribute AnimalProfile animalProfile) {
        Optional<AnimalProfile> result = animalProfileRepository.findById(animalProfileId);
        if (result.isPresent()) {
            AnimalProfile existingAnimalProfile = result.get();
            existingAnimalProfile.setName(animalProfile.getName());
            existingAnimalProfile.setLocation(animalProfile.getLocation());
            existingAnimalProfile.setSpecies(animalProfile.getSpecies());
            existingAnimalProfile.setBreed(animalProfile.getBreed());
            existingAnimalProfile.setAge(animalProfile.getAge());
            existingAnimalProfile.setComments(animalProfile.getComments());
            animalProfileRepository.save(existingAnimalProfile);
        }
        return "redirect:/animalprofile/myanimals";
    }

    @PostMapping("/delete/{animalProfileId}")
    public String processDeleteAnimalProfile(@PathVariable int animalProfileId) {
        Optional<AnimalProfile> result = animalProfileRepository.findById(animalProfileId);
        if (result.isPresent()) {
            AnimalProfile animalProfile = result.get();
            animalProfileRepository.delete(animalProfile);
        }
        return "redirect:/animalprofile/myanimals";
    }




}


