package com.gora.contractmanagerapi.contract.controller;

import com.gora.contractmanagerapi.contract.controller.openapi.ContractControllerOpenApi;
import com.gora.contractmanagerapi.contract.domain.ContractId;
import com.gora.contractmanagerapi.contract.dto.CreateContractDTO;
import com.gora.contractmanagerapi.contract.service.ContractService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.util.UriComponentsBuilder.fromPath;

@RequiredArgsConstructor
@RequestMapping(path = ContractController.PATH, produces = APPLICATION_JSON_VALUE)
@RestController
public class ContractController implements ContractControllerOpenApi {

    public static final String PATH = "/api/v1/contract";

    private final ContractService contractService;

    @Override
    @PostMapping
    public ResponseEntity<ContractId> include(@Valid @RequestBody CreateContractDTO createContractDTO) {
        var contractId = contractService.included(createContractDTO);
        return ResponseEntity.created(
                fromPath(ContractController.PATH + "/{id}")
                        .build(contractId.asString())
        ).body(contractId);
    }
}
