package com.p4pijk.roadquest.controller.user;

import com.p4pijk.roadquest.util.RQLiterals;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return RQLiterals.LOGIN_PAGE.value();
    }

    @GetMapping("/logout")
    public String logout() {
        return RQLiterals.REDIRECT_LOGOUT_PAGE.value();
    }
}
