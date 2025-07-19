package com.airtribe.taskMaster.controller;

import com.airtribe.taskMaster.dto.UserDTO;
import com.airtribe.taskMaster.entity.Users;
import com.airtribe.taskMaster.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public Users register(@RequestBody UserDTO user) {
        Users registeredUser = authenticationService.registerUser(user);
        String generatedToken = UUID.randomUUID().toString();
        String applicationUrl = "http://localhost:9001/verifyRegistration?token=" + generatedToken;
        //smtp.send_email_with_url
        System.out.println("Verification URL: " + applicationUrl);
        authenticationService.registerVerificationToken(registeredUser, generatedToken);
        return registeredUser;
    }

    @PostMapping("/verifyRegistration")
    public String verifyToken(@RequestParam String token){
        boolean isTokenValid = authenticationService.validateRegistrationToken(token);
        if (isTokenValid){
            authenticationService.enableUser(token);
            return "Token has been verified, please login to proceed";
        } else {
            return "Token validation failed";
        }
    }

    @PostMapping("/signin")
    public String login(@RequestParam String username, @RequestParam String password) {
        return authenticationService.loginUser(username,password);
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello from Auth";
    }
}
