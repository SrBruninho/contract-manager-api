package com.gora.contractmanagerapi.contract.util;

import com.gora.contractmanagerapi.contract.domain.Contract;
import com.gora.contractmanagerapi.contract.domain.ContractId;
import com.gora.contractmanagerapi.contract.domain.enums.ContractStatus;
import com.gora.contractmanagerapi.contract.repository.ContractRepository;

public class ContractTestFactory {

    private static final String NAME = "Contract Test - 001";

    public static Contract oneActiveContract() {
        return Contract
                .builder()
                .contractId(ContractId.generate())
                .name(NAME)
                .contractStatus(ContractStatus.ACTIVE)
                .build();
    }

    public static Contract persistOnDatabase(ContractRepository repository, Contract contract) {
        repository.save(contract);
        return contract;
    }
}
