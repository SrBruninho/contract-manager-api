package com.gora.contractmanagerapi.utils;

import com.gora.contractmanagerapi.contract.repository.ContractRepository;
import com.gora.contractmanagerapi.contract.service.ContractService;
import com.gora.contractmanagerapi.infra.authorization.component.CustomUserDetails;
import lombok.Getter;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Getter
@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "it")
public abstract class AbstractIntegrationTest {

    @Autowired
    protected ContractService contractService;

    @Autowired
    protected ContractRepository contractRepository;

    private CustomUserDetails testUser;
    private UsernamePasswordAuthenticationToken testAuthentication;

    @BeforeEach
    void setupSecurityContext() {
        testUser = new CustomUserDetails(1L, "mockUser", "mockPassword", List.of());

        testAuthentication = new UsernamePasswordAuthenticationToken(
                testUser,
                null,
                testUser.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(testAuthentication);
    }

}
