package org.launchcode.Pawfect.Harmony.controllers;


import org.launchcode.Pawfect.Harmony.data.UserRepository;
import org.launchcode.Pawfect.Harmony.models.SearchBar;
import org.launchcode.Pawfect.Harmony.models.User;
import org.launchcode.Pawfect.Harmony.models.dto.CreateUserFormDTO;
import org.launchcode.Pawfect.Harmony.models.dto.LoginFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class AuthenticationController {

    @Autowired
    UserRepository userRepository;


    private static final String userSessionKey = "user";

    public User getUserFromSession(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        if (userId == null) {
            return null;
        }
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            return null;
        }
        return user.get();
    }

    private static void setUserInSession(HttpSession session, User user) {
        session.setAttribute(userSessionKey, user.getId());
    }
    @GetMapping("user/create")
    public String displayCreateUserForm(Model model) {
        model.addAttribute("createUserFormDTO", new CreateUserFormDTO());
        return "user/create";
    }
    @PostMapping("user/create")
    public String processRegistrationForm(@ModelAttribute @Valid CreateUserFormDTO createUserFormDTO,
                                          Errors errors, HttpServletRequest request,
                                          Model model) {
        System.out.println(createUserFormDTO.getFirstName());
        System.out.println(createUserFormDTO.getLocation());
        if (errors.hasErrors()) {
            return "user/create";
        }

        User existingUser = userRepository.findByUsername(createUserFormDTO.getUsername());

        if (existingUser != null) {
            errors.rejectValue("username", "username.alreadyexists", "A user with that username already exists");
            return "user/create";
        }

        String password = createUserFormDTO.getPassword();
        String verifyPassword = createUserFormDTO.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
            return "user/create";
        }


        User newUser = new User(createUserFormDTO.getUsername(),
                createUserFormDTO.getFirstName(),
                createUserFormDTO.getLastName(),
                createUserFormDTO.getLocation(),
                createUserFormDTO.getEmail(),
                createUserFormDTO.getPhone(),
                createUserFormDTO.getPassword(),
                createUserFormDTO.getIsAdmin());
        userRepository.save(newUser);
        setUserInSession(request.getSession(), newUser);
        model.addAttribute("searchBar", new SearchBar());

        return "search";
    }
    @GetMapping("/login")
    public String displayLoginForm(Model model) {
        model.addAttribute(new LoginFormDTO());
        return "login";
    }

    @PostMapping("/login")
    public String processLoginForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO,
                                   Errors errors, HttpServletRequest request,
                                   Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Log In");
            return "login";
        }

        User theUser = userRepository.findByUsername(loginFormDTO.getUsername());

        if (theUser == null) {
            errors.rejectValue("username", "user.invalid", "The given username does not exist");
            model.addAttribute("title", "Log In");
            return "login";
        }

        String password = loginFormDTO.getPassword();

        if (!theUser.isMatchingPassword(password)) {
            errors.rejectValue("password", "password.invalid", "Invalid password");
            model.addAttribute("title", "Log In");
            return "login";
        }

        setUserInSession(request.getSession(), theUser);
        model.addAttribute("searchBar", new SearchBar());

        return "redirect:/search";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/login";
    }

}