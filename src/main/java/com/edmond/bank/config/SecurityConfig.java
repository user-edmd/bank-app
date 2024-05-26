package com.edmond.bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
public class SecurityConfig {

    @EnableWebSecurity
    @EnableMethodSecurity
    @Configuration
    static class OktaOAuth2WebSecurityConfiguration {

        @Bean
        SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            return http.authorizeHttpRequests(
                            (req) -> req.requestMatchers("/**").authenticated()
                    )
                    .oauth2ResourceServer((srv) -> srv.jwt(Customizer.withDefaults()))
                    .cors(Customizer.withDefaults())
                    .build();
        }
    }
}
