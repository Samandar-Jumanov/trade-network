package com.trade.auth;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class RegisterRequest

{

    @NotNull(message = "Firstname is required")
    @NotBlank(message = "Firstname is blank")
    private String firstname;

    @NotNull(message = "Lastname  is required")
    @NotBlank(message = "Lastname is blank")
    private String lastname;


    @NotNull(message = "Birthday is required")
    private LocalDate birthday;


    @NotNull(message = "Password is required")
    @NotBlank(message = "Password is blank")
    @Size(min = 8 , max = 16 , message =  "Password length should be 8 - 16")
    private String password;


    @NotNull(message = "Email is required")
    @NotBlank(message = "Email is blank")
    @Email(message = "Email is not valid")
    private String email;



}
