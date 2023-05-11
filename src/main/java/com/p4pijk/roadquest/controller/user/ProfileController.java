package com.p4pijk.roadquest.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Collection;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    SimpleUrlAuthenticationSuccessHandler admin =
            new SimpleUrlAuthenticationSuccessHandler("/admin");

    SimpleUrlAuthenticationSuccessHandler manager =
            new SimpleUrlAuthenticationSuccessHandler("/manager");

    SimpleUrlAuthenticationSuccessHandler user =
            new SimpleUrlAuthenticationSuccessHandler("/profile");

    @GetMapping
    public void profile(HttpServletRequest req, HttpServletResponse resp, Authentication auth) throws ServletException, IOException {
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        for (final GrantedAuthority grantedAuthority : authorities) {
            String authorityName = grantedAuthority.getAuthority();
            switch (authorityName) {
                case "ADMIN" -> admin.onAuthenticationSuccess(req,resp,auth);
//                case "ADMIN" -> "admin/admin";
                case "MANAGER" -> manager.onAuthenticationSuccess(req,resp,auth);
//                case "MANAGER" -> "manager/manager";
                default -> user.onAuthenticationSuccess(req,resp,auth);
//                default -> "user/profile";
            }
        }
        // TODO upload user model into page
        // TODO redirect via role
//        return "/";
    }
}
