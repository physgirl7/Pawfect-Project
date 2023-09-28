package org.launchcode.Pawfect.Harmony.controllers;

import org.apache.commons.io.IOUtils;
import org.launchcode.Pawfect.Harmony.data.UserMeetPetRepository;
import org.launchcode.Pawfect.Harmony.data.UserRepository;
import org.launchcode.Pawfect.Harmony.models.AnimalProfile;
import org.launchcode.Pawfect.Harmony.models.User;
import org.launchcode.Pawfect.Harmony.models.UserMeetPet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.launchcode.Pawfect.Harmony.data.AnimalProfileRepository;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("animalprofile")
public class AnimalProfileController {

    @Autowired
    private AnimalProfileRepository animalProfileRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMeetPetRepository userMeetPetRepository;

    @Autowired
    private AuthenticationController authenticationController;


    @GetMapping("myanimals/{userId}")
    public String displayAnimalProfile(Model model, @PathVariable int userId) {
        List<AnimalProfile> animalProfiles = (List<AnimalProfile>) animalProfileRepository.findAll();
        model.addAttribute("animalProfiles", animalProfiles);
        Optional optUser = userRepository.findById(userId);
        if (optUser.isPresent()) {
            User user = (User) optUser.get();
            model.addAttribute("user", user);
        }
        return "animalprofile/myanimals";
    }


    @GetMapping("/form/{userId}")
    public String createAnimalForm(Model model, @PathVariable int userId) {
        Optional optUser = userRepository.findById(userId);
        if (optUser.isPresent()) {
            User user = (User) optUser.get();
            model.addAttribute("user", user);
        }
        AnimalProfile animalProfile = new AnimalProfile();
        model.addAttribute("animalProfile", animalProfile);
        return "animalprofile/form";
    }

    @PostMapping("/form/{userId}")
    public String processAnimalForm(@ModelAttribute @Valid AnimalProfile animalProfile, Errors errors, Model model, @PathVariable int userId, @RequestParam("file")MultipartFile photo) throws IOException {
        if (errors.hasErrors()) {
            return "redirect:./" + userId;
        }
        Optional<User> result = userRepository.findById(userId);
        if (result.isPresent()) {
            User user = result.get();
            animalProfile.setUser(user);
        }
        byte[] animalPhoto = photo.getBytes();
        animalProfile.setPhoto(animalPhoto);
        animalProfileRepository.save(animalProfile);
        return "redirect:/user/useraccount/" + userId;
    }

    @GetMapping("/edit/{animalProfileId}/{userId}")
    public String editAnimalProfile(Model model, @PathVariable int animalProfileId, @PathVariable int userId) {
        Optional<AnimalProfile> result = animalProfileRepository.findById(animalProfileId);
        if (result.isPresent()) {
            AnimalProfile animalProfile = result.get();
            model.addAttribute("animalProfile", animalProfile);

            Optional optUser = userRepository.findById(userId);
            if (optUser.isPresent()) {
                User user = (User) optUser.get();
                model.addAttribute("user", user);
            }
            return "animalprofile/edit";
        }
        return "user/useraccount";
    }

    @PostMapping("/edit/{animalProfileId}/{userId}")
    public String processEditAnimalProfile(@PathVariable int animalProfileId, @PathVariable int userId, @ModelAttribute AnimalProfile animalProfile, Model model,@RequestParam("file")MultipartFile photo) throws IOException {
        Optional<AnimalProfile> result = animalProfileRepository.findById(animalProfileId);
        if (result.isPresent()) {
            AnimalProfile existingAnimalProfile = result.get();
            byte[] animalPhoto = existingAnimalProfile.getPhoto();
            if(!photo.isEmpty()) {
                animalPhoto = photo.getBytes();
            }

            existingAnimalProfile.setName(animalProfile.getName());
            existingAnimalProfile.setPhoto(animalPhoto);
            existingAnimalProfile.setLocation(animalProfile.getLocation());
            existingAnimalProfile.setSpecies(animalProfile.getSpecies());
            existingAnimalProfile.setBreed(animalProfile.getBreed());
            existingAnimalProfile.setGender(animalProfile.getGender());
            existingAnimalProfile.setAge(animalProfile.getAge());
            existingAnimalProfile.setComments(animalProfile.getComments());
            animalProfileRepository.save(existingAnimalProfile);
        }
        Optional optUser = userRepository.findById(userId);
        if (optUser.isPresent()) {
            User user = (User) optUser.get();
            model.addAttribute("user", user);
        }
        return "redirect:../../../user/useraccount/" + userId;
    }

    @PostMapping("/delete/{animalProfileId}/{userId}")
    public String processDeleteAnimalProfile(@PathVariable int animalProfileId, @PathVariable int userId, Model model) {
        Optional<AnimalProfile> result = animalProfileRepository.findById(animalProfileId);
        if (result.isPresent()) {
            AnimalProfile animalProfile = result.get();
            animalProfileRepository.delete(animalProfile);
        }
        Optional optUser = userRepository.findById(userId);
        if (optUser.isPresent()) {
            User user = (User) optUser.get();
            model.addAttribute("user", user);
        }
        return "redirect:../../../user/useraccount/" + userId;
    }

    @GetMapping("/adminedit/{animalProfileId}/{userId}")
    public String adminEditAnimalProfile(Model model, @PathVariable int animalProfileId, @PathVariable int userId) {
        Optional optUser = userRepository.findById(userId);
        if (optUser.isPresent()) {
            User user = (User) optUser.get();
            model.addAttribute("user", user);

            if (user.getIsAdmin() == true) {

                Optional<AnimalProfile> result = animalProfileRepository.findById(animalProfileId);
                if (result.isPresent()) {
                    AnimalProfile animalProfile = result.get();
                    model.addAttribute("animalProfile", animalProfile);
                }
            }   return "animalprofile/adminedit";
        }
        return "user/useraccount";
    }

    @PostMapping("/adminedit/{animalProfileId}/{userId}")
    public String processAdminEditAnimalProfile(@PathVariable int animalProfileId, @PathVariable int userId, @ModelAttribute AnimalProfile animalProfile, Model model,@RequestParam("file")MultipartFile photo) throws IOException {
        Optional<AnimalProfile> result = animalProfileRepository.findById(animalProfileId);
        if (result.isPresent()) {
            AnimalProfile existingAnimalProfile = result.get();
            byte[] animalPhoto = existingAnimalProfile.getPhoto();
            if(!photo.isEmpty()) {
                animalPhoto = photo.getBytes();
            }

            existingAnimalProfile.setName(animalProfile.getName());
            existingAnimalProfile.setPhoto(animalPhoto);
            existingAnimalProfile.setLocation(animalProfile.getLocation());
            existingAnimalProfile.setSpecies(animalProfile.getSpecies());
            existingAnimalProfile.setBreed(animalProfile.getBreed());
            existingAnimalProfile.setGender(animalProfile.getGender());
            existingAnimalProfile.setAge(animalProfile.getAge());
            existingAnimalProfile.setComments(animalProfile.getComments());
            animalProfileRepository.save(existingAnimalProfile);
            model.addAttribute("existingAnimalProfile", existingAnimalProfile);
        }
        Optional optUser = userRepository.findById(userId);
        if (optUser.isPresent()) {
            User user = (User) optUser.get();
            model.addAttribute("user", user);
        }
        return "animalprofile/adminanimalfile";
    }

    @PostMapping("/admindelete/{animalProfileId}/{userId}")
    public String processAdminDeleteAnimalProfile(@PathVariable int animalProfileId, @PathVariable int userId, Model model) {
        Optional<AnimalProfile> result = animalProfileRepository.findById(animalProfileId);
        if (result.isPresent()) {
            AnimalProfile animalProfile = result.get();
            animalProfileRepository.delete(animalProfile);
        }
        Optional optUser = userRepository.findById(userId);
        if (optUser.isPresent()) {
            User user = (User) optUser.get();
            model.addAttribute("user", user);
        }
        return "animalprofile/deleteconfirmation";
    }

    @GetMapping("animalfile/{animalProfileId}")
    public String displayAnimalFilePage(Model model, @PathVariable int animalProfileId, HttpServletRequest request) {
        Optional optAnimal = animalProfileRepository.findById(animalProfileId);
        if (optAnimal.isPresent()) {
            AnimalProfile animalProfile = (AnimalProfile) optAnimal.get();
            model.addAttribute("animalProfile", animalProfile);
            HttpSession userSession = request.getSession();
            User user = authenticationController.getUserFromSession(userSession);
            model.addAttribute("user", user);

            return "animalprofile/animalfile";
        } else {
            return "redirect:../";
        }
    }

    @GetMapping("requestsent/{animalProfileId}/{userId}")
    public String displayRequestSentPage(@ModelAttribute @Valid UserMeetPet userMeetPet, Model model, @PathVariable int animalProfileId, @PathVariable int userId) {
        Optional optAnimal = animalProfileRepository.findById(animalProfileId);
        Optional optUser = userRepository.findById(userId);
        if (optAnimal.isPresent() && optUser.isPresent()) {
            AnimalProfile animalProfile = (AnimalProfile) optAnimal.get();
            User user = (User) optUser.get();
            userMeetPet.setAnimalProfile(animalProfile);
            userMeetPet.setUser(user);

            List<UserMeetPet> userMeetExistingPets = userMeetPetRepository.findAllByUser(user);
                for(UserMeetPet oneUserMeetExistingPet : userMeetExistingPets){
                    if(oneUserMeetExistingPet.getAnimalProfile().getId() == userMeetPet.getAnimalProfile().getId()){
                        return "animalprofile/alreadyrequested";
                    }
                }

            userMeetPetRepository.save(userMeetPet);
            model.addAttribute("animalProfile", animalProfile);
            model.addAttribute("user", user);
            model.addAttribute("userMeetPet", userMeetPet);
            return "animalprofile/requestsent";
        } else {
            return "redirect:../";
        }
    }

    @GetMapping("postpet/button")
    public String callAnimalProfileFormPage(Model model, HttpServletRequest request) {
        HttpSession userSession = request.getSession();
        User user = authenticationController.getUserFromSession(userSession);
        if (user != null) {
            model.addAttribute("user", user);

            return "redirect:../form/" + user.getId();
        }
        return "redirect:../../login";
    }


    @GetMapping(path = "{animalProfileId}/photo",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getAnimalPhoto(@PathVariable int animalProfileId) throws IOException{
        Optional optAnimal = animalProfileRepository.findById(animalProfileId);
        if(optAnimal.isPresent()){
            AnimalProfile animalProfile = (AnimalProfile) optAnimal.get();
            return animalProfile.getPhoto();
        }
        InputStream in = getClass()
                .getResourceAsStream("static/PawfectProjectPhotos/pawfectLogo.jpg");
        return IOUtils.toByteArray(in);
    }
}

