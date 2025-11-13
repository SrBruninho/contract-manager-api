package com.gora.contractmanagerapi.infra.authorization.service;

import com.gora.contractmanagerapi.infra.authorization.component.CustomUserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InMemoryUserDetailsService implements UserDetailsService {

    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // mock users for now
        if ("test".equals(username))
            return new CustomUserDetails(2L, "test", encoder.encode("test"), List.of(() -> "ROLE_USER"));

        return new CustomUserDetails(1L, "admin", encoder.encode("admin"), List.of(() -> "ROLE_USER"));
    }
}
