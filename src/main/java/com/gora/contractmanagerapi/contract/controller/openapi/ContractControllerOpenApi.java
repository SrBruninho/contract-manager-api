package com.gora.contractmanagerapi.contract.controller.openapi;

import com.gora.contractmanagerapi.contract.domain.ContractId;
import com.gora.contractmanagerapi.contract.dto.CreateContractDTO;
import com.gora.contractmanagerapi.contract.dto.UpdateContractDTO;
import com.gora.contractmanagerapi.contract.hateoas.ContractModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Contract", description = "Provides APIs for the contract flow")
public interface ContractControllerOpenApi {

    @Operation(description = "Create a new contract using a valid body", method = "POST")
    ResponseEntity<ContractId> include(@Valid @RequestBody CreateContractDTO createContractDTO);

    @Operation(description = "Retrieve a contract by its Id", method = "GET")
    ResponseEntity<ContractModel> getContractById(@PathVariable("contractId") String contractId);

    @Operation(description = "Retrieve a list of all contracts", method = "GET")
    ResponseEntity<CollectionModel<ContractModel>> getAllContracts();

    @Operation(description = "Delete a contract by its Id", method = "DELETE")
    ResponseEntity<Void> deleteContract(@PathVariable("contractId") String contractId);

    @Operation(description = "Update an existing contract using a valid request body", method = "PUT")
    ResponseEntity<Void> updateContract(@Valid @RequestBody UpdateContractDTO updateContractDTO);

}
