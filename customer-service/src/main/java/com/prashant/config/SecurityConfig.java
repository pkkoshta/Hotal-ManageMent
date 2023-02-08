package com.prashant.config;

import com.prashant.jwtFilter.JWTFilter;
import com.prashant.service.UserDetailsServiceImpl;
import org.hibernate.StatelessSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserDetailsServiceImpl userService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private JWTFilter jwtFilter;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.
                csrf(AbstractHttpConfigurer::disable).
                authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/user", "/user/**").permitAll();
                    auth.requestMatchers("/customer/api/**").permitAll();
                    auth.requestMatchers("/user/").hasAuthority("MANAGER");
                    auth.requestMatchers("/user/getAll").hasAuthority("ADMIN");
                })
                .
        logout(logout -> logout
                .logoutUrl("/logout"))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)

                .exceptionHandling().and().sessionManagement()

                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()

                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(encoder);
        daoAuthenticationProvider.setUserDetailsService(userService);
        return daoAuthenticationProvider;
    }


}
