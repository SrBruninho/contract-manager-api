package com.gora.contractmanagerapi.contract.domain;


public class ContractBuilder {

    protected ContractId contractId;
    protected String name;
    protected ContractSituation contractSituation;

    public ContractBuilder contractId(ContractId contractId) {
        this.contractId = contractId;
        return this;
    }

    public ContractBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ContractBuilder contractStatus(ContractSituation contractSituation) {
        this.contractSituation = contractSituation;
        return this;
    }

    public Contract build(){
        this.contractId = ContractId.generate();

        return new Contract(this);
    }
}
