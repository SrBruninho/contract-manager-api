package com.gora.contractmanagerapi.contract.controller.openapi;

import com.gora.contractmanagerapi.contract.domain.ContractId;
import com.gora.contractmanagerapi.contract.dto.ContractDTO;
import com.gora.contractmanagerapi.contract.dto.CreateContractDTO;
import com.gora.contractmanagerapi.contract.dto.UpdateContractDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ContractControllerOpenApi {

    ResponseEntity<ContractId> include(@Valid @RequestBody CreateContractDTO createContractDTO);

    ResponseEntity<ContractDTO> getContractById(@PathVariable("contractId") String contractId);

    ResponseEntity<List<ContractDTO>> getAllContracts();

    ResponseEntity<Void> deleteContract(@PathVariable("contractId") String contractId);

    ResponseEntity<Void> updateContract(@Valid @RequestBody UpdateContractDTO updateContractDTO);

}
