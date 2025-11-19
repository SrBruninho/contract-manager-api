package com.gora.contractmanagerapi.contract.exception;

import com.gora.contractmanagerapi.contract.domain.ContractId;

public class CMAContractNotFoundException extends RuntimeException {
    public CMAContractNotFoundException(ContractId contractId) {
        super("Contract {} not found !" + contractId);
    }
}
