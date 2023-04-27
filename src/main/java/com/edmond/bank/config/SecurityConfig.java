////package com.edmond.bank.config;
////
////import com.edmond.bank.service.UserService;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.http.HttpMethod;
////import org.springframework.security.authentication.AuthenticationProvider;
////import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
////import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////import org.springframework.security.core.userdetails.User;
////import org.springframework.security.core.userdetails.UserDetails;
////import org.springframework.security.core.userdetails.UserDetailsService;
////import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
////import org.springframework.security.crypto.password.NoOpPasswordEncoder;
////import org.springframework.security.provisioning.InMemoryUserDetailsManager;
////import org.springframework.security.web.SecurityFilterChain;
////import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
////
////
////@EnableWebSecurity
////@Configuration
////public class SecurityConfig {
////    @Autowired
////    UserDetailsService userDetailsService;
////
////    @Bean
////    AuthenticationProvider authenticationProvider() {
////        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
////        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
////        daoAuthenticationProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
////        return daoAuthenticationProvider;
////    }
////
////    @Bean
////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////
////            http.authorizeHttpRequests()
////                    .requestMatchers("/","/create-user")
////                    .permitAll()
////                    .requestMatchers("/users/list", "/accounts/list", "/transactions/list")
////                    .hasAuthority("ADMIN")
////                    .anyRequest()
////                    .authenticated()
////                    .and()
////                    .formLogin()
////                    .defaultSuccessUrl("/dashboard", true)
////                    .permitAll()
////                    .and()
////                    .logout()
////                    .logoutSuccessUrl("/")
////                    .invalidateHttpSession(true)
////                    .and()
////                    .httpBasic();
////            return http.build();
////    }
////
////}
//
package com.edmond.bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/user/all").hasRole("ADMIN")
        );

        http.httpBasic();

        http.csrf().disable();

        return http.build();

    }
}