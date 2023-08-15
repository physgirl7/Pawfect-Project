package org.launchcode.Pawfect.Harmony.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FooterController {
    @GetMapping("/termsofservice")
    public String termsofservice(){ return "/footerPages/termsofservice"; }

    @GetMapping("/aboutus")
    public String aboutus(){ return "/footerPages/aboutus"; }

    @GetMapping("/contactadmin")
    public String contactadmin(){ return "/footerPages/contactadmin"; }

    @GetMapping("/privacypolicy")
    public String privacypolicy(){ return "/footerPages/privacypolicy"; }

}

