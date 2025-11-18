package com.gora.contractmanagerapi.contract.controller;

import com.gora.contractmanagerapi.contract.controller.openapi.ContractControllerOpenApi;
import com.gora.contractmanagerapi.contract.domain.ContractId;
import com.gora.contractmanagerapi.contract.dto.CreateContractDTO;
import com.gora.contractmanagerapi.contract.dto.UpdateContractDTO;
import com.gora.contractmanagerapi.contract.hateoas.ContractModel;
import com.gora.contractmanagerapi.contract.hateoas.ContractModelAssembler;
import com.gora.contractmanagerapi.contract.service.ContractService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    private final ContractModelAssembler contractModelAssembler;

    @Override
    @GetMapping("/{contractId}")
    public ResponseEntity<ContractModel> getContractById(@PathVariable("contractId") String contractId) {
        return ResponseEntity.ok(contractModelAssembler.toModel(contractService.getContractById(contractId)));
    }

    @GetMapping
    public ResponseEntity<CollectionModel<ContractModel>> getAllContracts() {
        return ResponseEntity.ok(contractModelAssembler.toCollectionModel(contractService.getContracts()));
    }

    @Override
    @PostMapping
    public ResponseEntity<ContractId> include(@Valid @RequestBody CreateContractDTO createContractDTO) {
        var contractId = contractService.included(createContractDTO);
        return ResponseEntity.created(
                fromPath(ContractController.PATH + "/{id}")
                        .build(contractId.asString())
        ).body(contractId);
    }

    @Override
    @PutMapping("/update")
    public ResponseEntity<Void> updateContract(@Valid @RequestBody UpdateContractDTO updateContractDTO) {
        contractService.updateContract(updateContractDTO);
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping("/{contractId}")
    public ResponseEntity<Void> deleteContract(@PathVariable("contractId") String contractId) {
        contractService.deleteContract(contractId);
        return ResponseEntity.noContent().build();
    }
}
