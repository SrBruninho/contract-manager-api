package com.gora.contractmanagerapi.contract.dto;

import com.gora.contractmanagerapi.contract.domain.ContractId;

public record UpdateContractDTO(ContractId contractId, String name){}
