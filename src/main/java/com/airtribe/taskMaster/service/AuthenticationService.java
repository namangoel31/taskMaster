package com.airtribe.taskMaster.service;

import com.airtribe.taskMaster.dto.UserDTO;
import com.airtribe.taskMaster.entity.Users;
import com.airtribe.taskMaster.entity.VerificationToken;
import com.airtribe.taskMaster.repository.UserRepository;
import com.airtribe.taskMaster.repository.VerificationTokenRepository;
import com.airtribe.taskMaster.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    public Users registerUser(UserDTO user) {
        Users dbUser = new Users();
        dbUser.setUsername(user.getUsername());
        dbUser.setPassword(passwordEncoder.encode(user.getPassword()));
        dbUser.setEnabled(false);
        dbUser.setRole("USER");
        return userRepository.save(dbUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new User(user.getUsername(),user.getPassword(), Collections.emptyList());
    }

    public void registerVerificationToken(Users registeredUser, String generatedToken) {
        VerificationToken token = new VerificationToken();
        token.setToken(generatedToken);
        token.setUser(registeredUser);
        long expiryDate = (new Date().getTime() + 1000 * 60 * 60 * 24);
        token.setExpiryDate(new Date(expiryDate));
        verificationTokenRepository.save(token);
    }

    public boolean validateRegistrationToken(String token) {
        VerificationToken registeredToken = verificationTokenRepository.findByToken(token);
        if (registeredToken == null) {
            return false;
        }

        long registeredExpiryDate = registeredToken.getExpiryDate().getTime();
        if (registeredExpiryDate < System.currentTimeMillis()) {
            return false;
        }
        return true;
    }

    public void enableUser(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        Users registeredUser = verificationToken.getUser();
        registeredUser.setEnabled(true);
        userRepository.save(registeredUser);
        verificationTokenRepository.delete(verificationToken);
    }

    public String loginUser(String username, String password) {
        Users registereduser = userRepository.findByUsername(username);
        if (registereduser == null || !registereduser.isEnabled()) {
            return ("User not registered or not enabled");
        }

        boolean arePasswordsMatch = passwordEncoder.matches(password, registereduser.getPassword());
        if (arePasswordsMatch) {
            return JwtUtil.generateToken(username);

        }

        return "User not authenticated";
    }
}
