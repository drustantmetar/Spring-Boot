package com.journalapp.springsecurity.springsecurity.configuration;

import com.journalapp.springsecurity.springsecurity.Services.UserDetailsServiceImpls;
import com.journalapp.springsecurity.springsecurity.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableWebSecurity

    //@Profile("dev")   -> We can set too the below mentioned config file runs from which env dev / UAT or Production
    public class SecurityConfig {

    @Autowired
    public JwtFilter jwtFilter;

    @Autowired
    private final UserDetailsServiceImpls userDetailsServiceImpls;

    public SecurityConfig(UserDetailsServiceImpls userDetailsServiceImpls) {
        this.userDetailsServiceImpls = userDetailsServiceImpls;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/update","/deleteByUsername","/journal/**").authenticated() // allow registration
                        .anyRequest().permitAll());

//                .httpBasic(Customizer.withDefaults());  -> Only for the basic authetication
                http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        // ✅ In Security 5, you must pass the UserDetailsService into the constructor
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsServiceImpls);
//        provider.setPasswordEncoder(passwordEncoder());
//        return provider;
//    }

    // It was created before JWT but it also use in it
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}