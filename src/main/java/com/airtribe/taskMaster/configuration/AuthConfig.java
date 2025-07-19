package com.airtribe.taskMaster.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AuthConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    public SecurityFilterChain securityFilterChainAuth(HttpSecurity httpSecurity) throws Exception {
        try{
            httpSecurity.csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests(
                            authorizeRequests -> authorizeRequests.requestMatchers("/register","/verifyRegistration","/signin","/hello")
                                    .permitAll()
                                    .anyRequest()
                                    .authenticated())
                    .formLogin(formLogin -> formLogin.defaultSuccessUrl("/hello",true).permitAll());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return httpSecurity.build();
    }
}
