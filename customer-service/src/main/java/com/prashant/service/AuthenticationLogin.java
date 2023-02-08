package com.prashant.service;

import com.prashant.login.LoginJWTResponse;
import com.prashant.login.LoginRequest;
import com.prashant.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/sign")
public class AuthenticationLogin {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private AuthenticationProvider authenticationManager;




}
