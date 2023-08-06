package org.launchcode.Pawfect.Harmony.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactAdminController {

    @GetMapping("/contactadmin")
    public String contactadmin(){ return "/contactadmin"; }
}
