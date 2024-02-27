package com.example.dietistaspring.security;


import com.example.dietistaspring.security.filter.JwtAuthenticationFilter;
import com.example.dietistaspring.security.filter.JwtValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SpringSecurityConfig {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;
    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/usuario/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/usuario/registerAdmin").hasAnyRole("DADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/usuario").hasRole("DADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/usuario").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/alimentos", "/api/alimentos/{id}").hasAnyRole("DADMIN", "DIETISTA", "CLIENTE")
                        .requestMatchers(HttpMethod.GET, "/api/alimentos/buscar/**").hasAnyRole("DADMIN", "DIETISTA", "CLIENTE")
                        .requestMatchers(HttpMethod.POST, "/api/alimentos").hasAnyRole("DADMIN","DIETISTA")
                        .requestMatchers(HttpMethod.PUT, "/api/alimentos/{id}").hasAnyRole("DADMIN", "DIETISTA")
                        .requestMatchers(HttpMethod.DELETE, "/api/alimentos/{id}").hasAnyRole("DADMIN", "DIETISTA")
                        .requestMatchers(HttpMethod.GET, "/api/comentarios", "/api/comentarios/{id}").hasAnyRole("DADMIN", "DIETISTA", "CLIENTE")
                        .requestMatchers(HttpMethod.POST, "/api/comentarios").hasAnyRole("DADMIN", "DIETISTA", "CLIENTE")
                        .requestMatchers(HttpMethod.PUT, "/api/comentarios/{id}").hasAnyRole("DADMIN", "DIETISTA", "CLIENTE")
                        .requestMatchers(HttpMethod.DELETE, "/api/comentarios/{id}").hasAnyRole("DADMIN", "DIETISTA", "CLIENTE")
                        .requestMatchers(HttpMethod.GET, "/api/dietas", "/api/comentarios/{id}").hasAnyRole("DADMIN", "DIETISTA", "CLIENTE")
                        .requestMatchers(HttpMethod.GET, "/api/dietas/filter/**", "/api/comentarios/{id}").hasAnyRole("DADMIN", "DIETISTA", "CLIENTE")
                        .requestMatchers(HttpMethod.POST, "/api/dietas").hasAnyRole("DADMIN", "DIETISTA")
                        .requestMatchers(HttpMethod.PUT, "/api/dietas/{id}").hasAnyRole("DADMIN", "DIETISTA")
                        .requestMatchers(HttpMethod.DELETE, "/api/dietas/{id}").hasAnyRole("DADMIN", "DIETISTA", "CLIENTE")
                        .anyRequest().authenticated())
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtValidationFilter(authenticationManager()))
                .csrf(config -> config.disable())
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();

    }

}
