package com.gora.contractmanagerapi.contract.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.gora.contractmanagerapi.infra.UUIDWrapper;
import com.gora.contractmanagerapi.infra.UUIDWrapperDescriptor;
import com.gora.contractmanagerapi.infra.UUIDWrapperH2Type;

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

    public static class ContractIdType extends UUIDWrapperH2Type<ContractId> {
        private static final long serialVersionUID = 1L;

        private static final UUIDWrapperDescriptor<ContractId> TYPE_DESCRIPTOR =
                new UUIDWrapperDescriptor<>(ContractId.class, ContractId::new);

        public ContractIdType() {
            super(TYPE_DESCRIPTOR);
        }
    }
}
