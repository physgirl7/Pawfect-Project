package org.launchcode.Pawfect.Harmony.controllers;

import org.launchcode.Pawfect.Harmony.data.UserRepository;
import org.launchcode.Pawfect.Harmony.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("useraccount/{userId}")
    public String displayUserAccountPage(Model model, @PathVariable int userId) {
        Optional optUser = userRepository.findById(userId);
        if (optUser.isPresent()) {
            User user = (User) optUser.get();
            model.addAttribute("user", user);
            return "user/useraccount";
        } else {
            return "redirect:../";
        }
    }

    @GetMapping("create")
    public String displayCreateUserForm(Model model) {
        model.addAttribute(new User());
        return "user/create";
    }

    @PostMapping("create")
    public String processCreateUserForm(@ModelAttribute @Valid User newUser,
                                         Errors errors) {

        if (errors.hasErrors()) {
            return "user/create";
        }
        User existingUser = userRepository.findByUsername(newUser.getUsername());

        if (existingUser != null) {
            errors.rejectValue("username", "username.alreadyexists", "A user with that username already exists");
            return "user/create";
        }


        userRepository.save(newUser);

        return "redirect:/search";
    }

    @GetMapping("edit/{userId}")
    public String displayEditUserAccount(Model model, @PathVariable int userId){
        Optional optUser = userRepository.findById(userId);
        if (optUser.isPresent()) {
            User user = (User) optUser.get();
            model.addAttribute("user", user);
            return "user/edit";
        } else {
            return "redirect:../";
        }
    }
}
