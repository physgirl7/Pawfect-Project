package org.launchcode.Pawfect.Harmony.controllers;

import org.launchcode.Pawfect.Harmony.data.UserRepository;
import org.launchcode.Pawfect.Harmony.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("useraccount/{userId}")
    public String displayUserAccountPage() {
//        model.addAttribute("user", new User());
        return "user/useraccount";
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
}
