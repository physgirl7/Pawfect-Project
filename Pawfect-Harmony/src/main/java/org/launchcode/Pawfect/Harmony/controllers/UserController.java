package org.launchcode.Pawfect.Harmony.controllers;

import org.launchcode.Pawfect.Harmony.data.AnimalProfileRepository;
import org.launchcode.Pawfect.Harmony.data.UserMeetPetRepository;
import org.launchcode.Pawfect.Harmony.data.UserRepository;
import org.launchcode.Pawfect.Harmony.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("user")
public class UserController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AnimalProfileRepository animalProfileRepository;

    @Autowired
    private UserMeetPetRepository userMeetPetRepository;

    @Autowired
    private AuthenticationController authenticationController;

    static HashMap<String, String> columnChoices = new HashMap<>();
    static HashMap<String, String> searchByPetUserChoices = new HashMap<>();

    public UserController(){
        columnChoices.put("all", "All");
        columnChoices.put("name", "Name");
        columnChoices.put("location", "Location");
        searchByPetUserChoices.put("users", "Users");
        searchByPetUserChoices.put("pets", "Pets");

    }




    @GetMapping("useraccount/{userId}")
    public String displayUserAccountPage(Model model, @PathVariable int userId) {
        Optional optUser = userRepository.findById(userId);
        if (optUser.isPresent()) {
            User user = (User) optUser.get();
            model.addAttribute("user", user);
            List<AnimalProfile> myAnimals = animalProfileRepository.findAllByUser(user);
            model.addAttribute("myAnimals", myAnimals);
            List<UserMeetPet> userMeetAnimals = userMeetPetRepository.findAllByUser(user);
            List<AnimalProfile> meetAnimals = new ArrayList<>();
            for (UserMeetPet userMeetPet : userMeetAnimals) {
                AnimalProfile onePet = userMeetPet.getAnimalProfile();
                meetAnimals.add(onePet);
            }
            model.addAttribute("meetAnimals", meetAnimals);
            return "user/useraccount";
        } else {
            return "redirect:../";
        }
    }

    @GetMapping("edit/{userId}")
    public String displayEditUserAccount(Model model, @PathVariable int userId) {
        Optional optUser = userRepository.findById(userId);
        if (optUser.isPresent()) {
            User user = (User) optUser.get();
            EditedUser editeduser = new EditedUser();
            editeduser.setFirstName(user.getFirstName());
            editeduser.setLastName(user.getLastName());
            editeduser.setLocation(user.getLocation());
            editeduser.setEmail(user.getEmail());
            editeduser.setPhone(user.getPhone());
            model.addAttribute("user", user);
            model.addAttribute("editeduser", editeduser);
            return "user/edit";
        } else {
            return "redirect:../";
        }
    }

    @PostMapping("edit/{userId}")
    public String processEditUserAccount(@ModelAttribute @Valid EditedUser editedUser, Errors errors, Model model, @PathVariable int userId) {
        if (errors.hasErrors()) {
            return "redirect:" + userId;
        }
        Optional optUser = userRepository.findById(userId);
        if (optUser.isPresent()) {
            User user = (User) optUser.get();
            user.setFirstName(editedUser.getFirstName());
            user.setLastName(editedUser.getLastName());
            user.setLocation(editedUser.getLocation());
            user.setEmail(editedUser.getEmail());
            user.setPhone(editedUser.getPhone());
            model.addAttribute("user", user);
            userRepository.save(user);
            return "redirect:../useraccount/" + user.getId();

        }
        return "redirect:../";
    }

    @GetMapping("useraccount/button")
    public String callUserAccountPage(Model model, HttpServletRequest request) {
        HttpSession userSession = request.getSession();
        User user = authenticationController.getUserFromSession(userSession);
        if (user != null) {
            model.addAttribute("user", user);

            return "redirect:../useraccount/" + user.getId();
        }
        return "redirect:../../login";
    }

    @RequestMapping("admin")
    public String displayAdminAccountPage(Model model, HttpServletRequest request){
        HttpSession userSession = request.getSession();
        User user = authenticationController.getUserFromSession(userSession);
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("columns", columnChoices);
            model.addAttribute("searchTypes", searchByPetUserChoices);
            if(user.getIsAdmin().equals(true)){
                return "user/admin";
            }

            return "redirect:useraccount/" + user.getId();
        }
        return "redirect:../login";
    }

    @PostMapping("admin/results")
    public String displayAdminSearchResults(Model model, @RequestParam String searchType, @RequestParam String searchCategory, @RequestParam String searchTerm, HttpServletRequest request){
        HttpSession userSession = request.getSession();
        User user = authenticationController.getUserFromSession(userSession);
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("columns", columnChoices);
            model.addAttribute("searchTypes", searchByPetUserChoices);
        }
        Iterable<AnimalProfile> animals;
        Iterable<User> users;
        if(searchType.equals("pets")) {
            if (searchTerm.toLowerCase().equals("all") || searchTerm.equals("")) {
                animals = animalProfileRepository.findAll();
            } else {
                animals = AnimalProfileData.findByColumnAndValue(searchCategory, searchTerm, animalProfileRepository.findAll());
            }
            model.addAttribute("animals", animals);
        }
        if(searchType.equals("users")){
            if (searchTerm.toLowerCase().equals("all") || searchTerm.equals("")) {
                users = userRepository.findAll();
            } else {
                users = UserData.findByColumnAndValue(searchCategory, searchTerm, userRepository.findAll());
            }
            model.addAttribute("users", users);
        }
        model.addAttribute("columns", columnChoices);
        model.addAttribute("title", "Pets by " + columnChoices.get(searchType) + ": " + searchTerm);


        return "user/admin";
    }

    @GetMapping("adminedit/useraccount/{userId}")
    public String displayAdminUserAccountPage(Model model, @PathVariable int userId) {
        Optional optUser = userRepository.findById(userId);
        if (optUser.isPresent()) {
            User user = (User) optUser.get();
            model.addAttribute("user", user);
            List<AnimalProfile> myAnimals = animalProfileRepository.findAllByUser(user);
            model.addAttribute("myAnimals", myAnimals);
            List<UserMeetPet> userMeetAnimals = userMeetPetRepository.findAllByUser(user);
            List<AnimalProfile> meetAnimals = new ArrayList<>();
            for (UserMeetPet userMeetPet : userMeetAnimals) {
                AnimalProfile onePet = userMeetPet.getAnimalProfile();
                meetAnimals.add(onePet);
            }
            model.addAttribute("meetAnimals", meetAnimals);
            return "user/adminuseraccount";
        } else {
            return "redirect:../";
        }
    }
    @GetMapping("adminedit/useraccountedit/{userId}")
    public String displayAdminEditUserAccount(Model model, @PathVariable int userId) {
        Optional optUser = userRepository.findById(userId);
        if (optUser.isPresent()) {
            User user = (User) optUser.get();
            EditedUser editeduser = new EditedUser();
            editeduser.setFirstName(user.getFirstName());
            editeduser.setLastName(user.getLastName());
            editeduser.setLocation(user.getLocation());
            editeduser.setEmail(user.getEmail());
            editeduser.setPhone(user.getPhone());
            editeduser.setIsAdmin(user.getIsAdmin());
            model.addAttribute("user", user);
            model.addAttribute("editeduser", editeduser);
            return "user/adminedit";
        } else {
            return "redirect:../";
        }
    }

    @PostMapping("adminedit/useraccountedit/{userId}")
    public String processAdminEditUserAccount(@ModelAttribute @Valid EditedUser editedUser, Errors errors, Model model, @PathVariable int userId) {
        if (errors.hasErrors()) {
            return "redirect:" + userId;
        }
        Optional optUser = userRepository.findById(userId);
        if (optUser.isPresent()) {
            User user = (User) optUser.get();
            user.setFirstName(editedUser.getFirstName());
            user.setLastName(editedUser.getLastName());
            user.setLocation(editedUser.getLocation());
            user.setEmail(editedUser.getEmail());
            user.setPhone(editedUser.getPhone());
            user.setIsAdmin(editedUser.getIsAdmin());
            model.addAttribute("user", user);
            userRepository.save(user);
            return "redirect:../useraccount/" + user.getId();

        }
        return "redirect:../";
    }


}

