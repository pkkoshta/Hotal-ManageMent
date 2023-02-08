package com.prashant.service;

import com.prashant.model.User;
import com.prashant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User optionalUser = userRepository.findByUsername(username);

        org.springframework.security.core.userdetails.User userDetails = null;
        if (optionalUser == null){
            System.out.println(" = user is null" );
        }
        User user = optionalUser;
        user.getRoles().stream().forEach(System.out::println);
        userDetails =
                new org.springframework.security.core.userdetails.User(user.getUsername(),
                        user.getPassword(), user.getRoles().stream().
                        map(role->new SimpleGrantedAuthority(role)).collect(Collectors.toList()));
        return userDetails;
    }

    public User createUser(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }


}
