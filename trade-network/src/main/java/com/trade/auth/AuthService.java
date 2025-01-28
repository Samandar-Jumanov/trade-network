package com.trade.auth;

import com.trade.email.EmailService;
import com.trade.email.EmailTemplate;
import com.trade.role.RoleRepo;
import com.trade.security.JwtService;
import com.trade.user.Token;
import com.trade.user.TokenRepo;
import com.trade.user.User;
import com.trade.user.UserRepo;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AuthService {


    private final  RoleRepo  roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private  final TokenRepo tokenRepo;
    private final EmailService emailService;
    private final AuthenticationManager authenticationManager;
    private final JwtService  jwtService;

    @Value("${application.mailing.frontend.activation-url}")
    private String activationUrl;

    public void register(RegisterRequest data) throws MessagingException {

          var defaultRole  = roleRepo.findByTitle("USER")
                  // to-do better exception handling needs to be added
                  .orElseThrow(  () -> new IllegalStateException("ROLE USER not  found") );

          var user = User.builder()
                  .firstname(data.getFirstname())
                  .lastname(data.getLastname())
                  .email(data.getEmail())
                  .birthday(data.getBirthday())
                  .accountLocked(false)
                  .enabled(false)
                  .password(passwordEncoder.encode(data.getPassword())) // hash password
                  .roles(List.of()) // to do
                  .build();

          userRepo.save(user);
          sendValidationEmail(user);

    }

    private void sendValidationEmail(User user) throws MessagingException {
        var newToken = generateAndSaveToken(user);
        emailService.sendEmail(
                user.getEmail(),
                 EmailTemplate.ACTIVATATE_ACCOUNT,
                 user.getFullName(),
                 activationUrl ,
                newToken ,
                "Account cofirmation"
        );



    }

    private String  generateAndSaveToken(User user) {
        String generatedToken   = generateActivationCode(6);
        var token = Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.from(LocalDateTime.now().plusMinutes(15)))
                .user(user)
                .build();

        tokenRepo.save(token);
        return generatedToken;
    }

    private String generateActivationCode(int codeLength) {

        String chars = "0123456789";
        StringBuilder result = new StringBuilder();
        SecureRandom random = new SecureRandom();

        for ( int i = 0; i < codeLength; i++ ) {
            int randomChar = random.nextInt(chars.length()); // random value from chars by chars length
            result.append(chars.charAt(randomChar));
        }
        return result.toString();
    }

    public AuthLoginResponse login(LoginRequest request) {
         var auth = authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(
                           request.getEmail(),
                           request.getPassword()
                   )
         );

         var claims = new HashMap<String , Object>();
         var user = (User) auth.getPrincipal();
         claims.put("email", user.getFullName());
         claims.put("password", user.getPassword());

         var jwtToken = jwtService.generateToken(claims , user);
         return AuthLoginResponse.builder().token(jwtToken).build();


    }


    public void activateAccount(String token) throws MessagingException {

        Token dbToken = tokenRepo.findByToken(token).orElseThrow(() -> new RuntimeException("Token not found"));
        // to - do different exception

        Boolean isExpired = LocalDateTime.now().isAfter(dbToken.getExpiresAt());

        if(isExpired){
            sendValidationEmail(dbToken.getUser());
            throw new RuntimeException("Token is expired , new token has been sent ");
        }

        var user = userRepo.findByEmail(dbToken.getUser().getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setEnabled(true);
        userRepo.save(user);
        dbToken.setValidatedAt(LocalDateTime.now());
        tokenRepo.save(dbToken);

    }
}
