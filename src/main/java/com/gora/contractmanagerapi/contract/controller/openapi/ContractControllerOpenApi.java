package com.gora.contractmanagerapi.contract.controller.openapi;

import com.gora.contractmanagerapi.contract.domain.ContractId;
import com.gora.contractmanagerapi.contract.dto.CreateContractDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface ContractControllerOpenApi {

    ResponseEntity<ContractId> include(@Valid @RequestBody CreateContractDTO createContractDTO);

}
