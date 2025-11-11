package com.gora.contractmanagerapi.contract.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SituationReason {
    EXPIRED("Due date reached"),
    BLOCKED("Contract blocked due to pending approval"),
    INACTIVE("Contract inactivated due to internal policies");

    private final String description;
}
