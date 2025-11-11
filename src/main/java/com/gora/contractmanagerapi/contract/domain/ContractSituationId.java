package com.gora.contractmanagerapi.contract.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.gora.contractmanagerapi.infra.UUIDWrapper;

import java.util.UUID;

public class ContractSituationId extends UUIDWrapper {

    protected ContractSituationId(UUID value) {
        super(value);
    }

    public static ContractSituationId generate() {
        return new ContractSituationId(UUID.randomUUID());
    }

    @JsonCreator
    public static ContractSituationId from(String value) {
        return value != null ? new ContractSituationId(UUID.fromString(value)) : null;
    }
}
