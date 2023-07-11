package org.launchcode.Pawfect.Harmony.controllers;

import org.launchcode.Pawfect.Harmony.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {
    @GetMapping("useraccount")
    public String displayUserAccountPage() {
//        model.addAttribute("user", new User());
        return "user/useraccount";
    }
}
