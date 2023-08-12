package org.launchcode.Pawfect.Harmony.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FooterController {
    @GetMapping("/termsofservice")
    public String termsofservice(){ return "/termsofservice"; }

    @GetMapping("/aboutus")
    public String aboutus(){ return "/aboutus"; }

    @GetMapping("/contactadmin")
    public String contactadmin(){ return "/contactadmin"; }

    @GetMapping("/privacypolicy")
    public String privacypolicy(){ return "/privacypolicy"; }

}

