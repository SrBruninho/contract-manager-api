package com.gora.contractmanagerapi.contract.dto;

import com.gora.contractmanagerapi.contract.domain.ContractId;
import com.gora.contractmanagerapi.contract.domain.enums.ContractStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor(staticName = "of")
public class ContractDTO {

    private ContractId contractId;
    private String name;
    private ContractStatus contractStatus;
}
