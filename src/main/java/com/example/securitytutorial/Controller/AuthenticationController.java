package com.example.securitytutorial.Controller;


import com.example.securitytutorial.Config.UsernamePwdAuthProvider;
import com.example.securitytutorial.Service.UserService;
import com.example.securitytutorial.WebToken.JwtService;
import com.example.securitytutorial.WebToken.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UsernamePwdAuthProvider usernamePwdAuthProvider;
    @Autowired
    private UserService userService;
    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody LoginForm loginForm) {
        Authentication authentication = usernamePwdAuthProvider.authenticate(new UsernamePasswordAuthenticationToken(
                loginForm.username(), loginForm.password()
        ));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(userService.loadUserByUsername(loginForm.username()));
        } else {
            throw new UsernameNotFoundException("Invalid credentials");
        }
    }

}
