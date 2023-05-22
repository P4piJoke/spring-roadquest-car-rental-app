package com.p4pijk.roadquest.controller.user;

import jakarta.servlet.ServletException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Collection;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private static final String REDIRECT_ADMIN = "redirect:/admin";
    private static final String REDIRECT_MANAGER = "redirect:/manager";

    // UserService getCurrentUser
    // ApplicationService getApplications

    @GetMapping
    public String profile(Authentication auth){
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        for (final GrantedAuthority grantedAuthority : authorities) {
            String authorityName = grantedAuthority.getAuthority();
            if (authorityName.equals("ADMIN")) {
                return REDIRECT_ADMIN;
            } else if (authorityName.equals("MANAGER")) {
                return REDIRECT_MANAGER;
            }
        }

        // TODO upload user model into page

        return "user/profile";
    }
}
