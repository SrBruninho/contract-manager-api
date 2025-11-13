package com.gora.contractmanagerapi.infra.auditing;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static java.util.Objects.isNull;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    private static final String SYSTEM_USER_NAME = "SYSTEM";

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (isNull(authentication) || !authentication.isAuthenticated())
            return Optional.of(SYSTEM_USER_NAME);

        return Optional.ofNullable(authentication.getName());
    }
}
