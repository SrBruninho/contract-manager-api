package com.gora.contractmanagerapi.contract.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.gora.contractmanagerapi.infra.UUIDWrapper;

import java.util.UUID;

public class ContractId extends UUIDWrapper {

    protected ContractId(UUID value) {
        super(value);
    }

    public static ContractId generate() {
        return new ContractId(UUID.randomUUID());
    }

    @JsonCreator
    public static ContractId from(String value) {
        return value != null ? new ContractId(UUID.fromString(value)) : null;
    }
}
