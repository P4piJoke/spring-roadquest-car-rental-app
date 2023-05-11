package com.p4pijk.roadquest.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public String init(){
        // TODO upload admin model panel into page
        return "admin/admin";
    }

}
