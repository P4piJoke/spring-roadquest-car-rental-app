package com.p4pijk.roadquest.controller.user;

import com.p4pijk.roadquest.entity.user.Role;
import com.p4pijk.roadquest.entity.user.User;
import com.p4pijk.roadquest.model.UserSignUpModel;
import com.p4pijk.roadquest.service.impl.UserRQServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SignUpController {

    private final UserRQServiceImpl service;
    private final PasswordEncoder encoder;

    @GetMapping("/signup")
    public String createNewUser(UserSignUpModel userModel, Model model) {
        model.addAttribute("userModel", userModel);
        return "user/signup";
    }

    @PostMapping("/signup")
    public String createNewUser(@Valid @ModelAttribute("userModel") UserSignUpModel userModel,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/signup";
        }
        service.save(
                User.builder()
                        .login(userModel.getLogin())
                        .password(encoder.encode(userModel.getPassword()))
                        .email(userModel.getEmail())
                        .firstName(userModel.getFirstName())
                        .lastName(userModel.getLastName())
                        .phoneNumber(userModel.getPhoneNumber())
                        .role(new Role(3, "BASIC"))
                        .status(true)
                        .build()
        );
        return "redirect:/";
    }

}
