package com.gora.contractmanagerapi.infra.authorization.service;

import com.gora.contractmanagerapi.infra.authorization.component.JWTTokenProvider;
import com.gora.contractmanagerapi.infra.authorization.dto.TokenAuthorizedDTO;
import com.gora.contractmanagerapi.infra.authorization.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JWTTokenProvider jwtTokenProvider;

    public TokenAuthorizedDTO login(UserDTO userDTO){
        Authentication auth = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userDTO.username(), userDTO.password()));

        UserDetails user = (UserDetails) auth.getPrincipal();
        var tokenAuthorized = TokenAuthorizedDTO.from(jwtTokenProvider.generateToken(user), 3600);
        log.info("User: {} logged in", userDTO.username());
        return tokenAuthorized;
    }
}
