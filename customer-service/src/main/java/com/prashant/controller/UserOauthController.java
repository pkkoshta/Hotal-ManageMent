package com.prashant.controller;

import com.prashant.login.LoginJWTResponse;
import com.prashant.login.LoginRequest;
import com.prashant.model.User;
import com.prashant.service.UserDetailsServiceImpl;
import com.prashant.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserOauthController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private AuthenticationProvider authenticationManager;

    @PostMapping
    public User createUser(@RequestBody User user){
        return userDetailsService.createUser(user);
    }

    @PostMapping(value = "/login", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<LoginJWTResponse> login(@RequestBody LoginRequest loginRequest) throws IllegalAccessException {
        String token = null;
        Set<String> roles = new HashSet<>();
        Map<String, Object> claims = new HashMap<>();

        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        for (GrantedAuthority r: userDetails.getAuthorities()){
            roles.add(r.getAuthority());
        }
        claims.put("Roles", roles);
        token =jwtUtils.generateToken(claims,loginRequest.getUsername());
        if (token != null)
            return new ResponseEntity<>(new LoginJWTResponse(token,  "token genrated successfully.."), HttpStatus.OK);
        else
            throw  new IllegalAccessException("Invalid username/password");
    }


    @GetMapping("/getAll")
    public List<User> getUsers(){
        return userDetailsService.getAll();
    }
}
