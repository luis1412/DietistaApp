//package com.example.dietistaspring.security;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//
//@Configuration
//public class SpringSecurityConfig{
//
//    @Autowired
//    private AuthenticationConfiguration authenticationConfiguration;
//    @Bean
//    AuthenticationManager authenticationManager() throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//    @Bean
//    PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//
//}
