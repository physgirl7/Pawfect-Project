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
                                        Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "user/create";
        }
        User saved = userRepository.save(newUser);

        return "redirect:useraccount/" + saved.getId();
    }

    @GetMapping("edit/{userId}")
    public String displayEditUserAccount(Model model, @PathVariable int userId) {
        Optional optUser = userRepository.findById(userId);
        if (optUser.isPresent()) {
            User user = (User) optUser.get();

            model.addAttribute("user", user);
            return "user/edit";
        } else {
            return "redirect:../";
        }
    }

    @PostMapping("edit/{userId}")
    public String processEditUserAccount(User editedUser, Model model, @PathVariable int userId, Errors errors) {
        if (errors.hasErrors()) {
            return "user/edit/{userId}";
        }
        Optional optUser = userRepository.findById(userId);
        if (optUser.isPresent()) {
            User user = (User) optUser.get();
            user.setFirstName(editedUser.getFirstName());
            user.setLastName(editedUser.getLastName());
            user.setEmail(editedUser.getEmail());
            user.setPhone(editedUser.getPhone());
            model.addAttribute("user", user);
            userRepository.save(user);
            return "redirect:../useraccount/" + user.getId();

        }
        return "redirect:../";
    }
}

//    Optional optEmployer = employerRepository.findById(employerId);
//       if (optEmployer.isPresent()) {
//               Employer employer = (Employer) optEmployer.get();
//               model.addAttribute("employer", employer);
//               return "employers/view";
//               } else {
//               return "redirect:../";
