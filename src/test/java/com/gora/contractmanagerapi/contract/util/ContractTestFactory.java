package com.gora.contractmanagerapi.contract.util;

import com.gora.contractmanagerapi.contract.domain.Contract;
import com.gora.contractmanagerapi.contract.domain.ContractId;
import com.gora.contractmanagerapi.contract.domain.enums.ContractStatus;
import com.gora.contractmanagerapi.contract.repository.ContractRepository;

public class ContractTestFactory {

    private static final String CONTRACT_NAME_ACTIVE = "Contract ACTIVE Test - 001";
    private static final String CONTRACT_NAME_BLOCKED = "Contract BLOCKED Test - 001";

    public static Contract oneActiveContract() {
        return Contract
                .builder()
                .contractId(ContractId.generate())
                .name(CONTRACT_NAME_ACTIVE)
                .contractStatus(ContractStatus.ACTIVE)
                .build();
    }

    public static Contract oneBlockedContract() {
        return Contract
                .builder()
                .contractId(ContractId.generate())
                .name(CONTRACT_NAME_BLOCKED)
                .contractStatus(ContractStatus.BLOCKED)
                .build();
    }

    public static Contract persistOnDatabase(ContractRepository repository, Contract contract) {
        repository.save(contract);
        return contract;
    }
}
