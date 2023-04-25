package com.p4pijk.roadquest.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(){
        return "user/login";
    }

    @GetMapping("/logout")
    public String logout(){
        return "redirect:/index.html";
    }
}
