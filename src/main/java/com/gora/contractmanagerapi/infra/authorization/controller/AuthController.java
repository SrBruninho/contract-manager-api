package com.gora.contractmanagerapi.infra.authorization.controller;

import com.gora.contractmanagerapi.infra.authorization.dto.TokenAuthorizedDTO;
import com.gora.contractmanagerapi.infra.authorization.dto.UserDTO;
import com.gora.contractmanagerapi.infra.authorization.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = AuthController.PATH, produces = APPLICATION_JSON_VALUE)
@Tag(name = "Authentication", description = "Provides Token for access API")
public class AuthController {

    public static final String PATH = "/api/v1/auth";

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenAuthorizedDTO> login(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(authService.login(userDTO));
    }

}
