package com.gora.contractmanagerapi.infra.authorization.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class InMemoryUserDetailsService implements UserDetailsService {

    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var usernameSample = "admin";
        var passwordSample = "admin";

        if (!usernameSample.equals(username))
            throw new UsernameNotFoundException("User Not Found!");

        return User.builder()
                .username(usernameSample)
                .password(encoder.encode(passwordSample))
                .roles("USER")
                .build();
    }
}
