package com.trade.auth;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")

public class AuthController {

    private final AuthService authservice;


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> register(
             @RequestBody @Valid RegisterRequest request
    ) throws MessagingException {
        authservice.register(request);
        return  ResponseEntity.accepted().build();
    };



    @PostMapping("/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<AuthLoginResponse> login(
              @RequestBody @Valid LoginRequest request
    )
    {
            return  ResponseEntity.ok(authservice.login(request));
    }


    @GetMapping("/activate-account")
    public ResponseEntity<?> activateAccount(
         @RequestParam   String token
    ) throws MessagingException {
          authservice.activateAccount(token);
          return  ResponseEntity.ok().build();
    }

}
