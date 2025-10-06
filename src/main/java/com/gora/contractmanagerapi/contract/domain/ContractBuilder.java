package com.gora.contractmanagerapi.contract.domain;

import com.gora.contractmanagerapi.contract.domain.enums.ContractStatus;

public class ContractBuilder {

    protected ContractId contractId;
    protected String name;
    protected ContractStatus contractStatus;

    public ContractBuilder contractId(ContractId contractId) {
        this.contractId = contractId;
        return this;
    }

    public ContractBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ContractBuilder contractStatus(ContractStatus contractStatus) {
        this.contractStatus = contractStatus;
        return this;
    }

    public Contract build(){
        this.contractId = ContractId.generate();

        return new Contract(this);
    }
}
