package com.gora.contractmanagerapi.contract.util;

import com.gora.contractmanagerapi.contract.domain.Contract;
import com.gora.contractmanagerapi.contract.domain.ContractId;
import com.gora.contractmanagerapi.contract.domain.ContractSituation;
import com.gora.contractmanagerapi.contract.domain.enums.ContractStatus;
import com.gora.contractmanagerapi.contract.repository.ContractRepository;

public class ContractTestFactory {

    private static final String CONTRACT_NAME_ACTIVE = "Contract ACTIVE Test - 001";
    private static final String CONTRACT_NAME_BLOCKED = "Contract BLOCKED Test - 001";
    private static final String CONTRACT_NAME_DRAFT = "Contract DRAFT Test - 001";

    public static Contract oneDraftContract() {
        var contractId = ContractId.generate();

        return Contract
                .builder()
                .contractId(contractId)
                .name(CONTRACT_NAME_DRAFT)
                .contractStatus(ContractSituation.ofDraft(contractId))
                .build();
    }

    public static Contract oneActiveContract() {
        var contractId = ContractId.generate();

        var contract = Contract
                .builder()
                .contractId(contractId)
                .name(CONTRACT_NAME_ACTIVE)
                .contractStatus(ContractSituation.ofDraft(contractId))
                .build();

        contract.activate();

        return contract;

    }

    public static Contract oneBlockedContract() {
        var contractId = ContractId.generate();

        var contract = Contract
                .builder()
                .contractId(contractId)
                .name(CONTRACT_NAME_BLOCKED)
                .contractStatus(ContractSituation.ofDraft(contractId))
                .build();

        contract.block();

        return contract;
    }

    public static Contract persistOnDatabase(ContractRepository repository, Contract contract) {
        repository.save(contract);
        return contract;
    }
}
