package com.p4pijk.roadquest.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserSignUpModel {

    @NotBlank(message = "Login is required")
    @Size(min = 6, max = 32)
    private String login;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 32)
    private String password;

    @Email
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "First name is required")
    @Size(min = 1, max = 45)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 1, max = 45)
    private String lastName;

    @NotBlank(message = "Phone number is required")
    @Size(min = 1, max = 10)
    private String phoneNumber;
}
