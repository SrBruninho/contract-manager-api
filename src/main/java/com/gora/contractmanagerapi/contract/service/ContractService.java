package com.gora.contractmanagerapi.contract.service;

import com.gora.contractmanagerapi.contract.domain.Contract;
import com.gora.contractmanagerapi.contract.domain.ContractId;
import com.gora.contractmanagerapi.contract.domain.ContractSituation;
import com.gora.contractmanagerapi.contract.domain.enums.ContractStatus;
import com.gora.contractmanagerapi.contract.dto.ContractDTO;
import com.gora.contractmanagerapi.contract.dto.CreateContractDTO;
import com.gora.contractmanagerapi.contract.dto.UpdateContractDTO;
import com.gora.contractmanagerapi.contract.exception.CMAContractNotFoundException;
import com.gora.contractmanagerapi.contract.repository.ContractRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ContractService {

    private final ContractRepository contractRepository;

    public ContractId included(@Valid CreateContractDTO createContractDTO) {
        var contract = Contract
                .builder()
                .name(createContractDTO.getName())
                .build();

        contractRepository.save(contract);

        return contract.getContractId();
    }

    public ContractDTO getContractById(String contractId) {
        var contractIdConv = ContractId.from(contractId);
        var contract = contractRepository
                .findById(contractIdConv)
                .orElseThrow(()-> new CMAContractNotFoundException(contractIdConv));
        return ContractDTO.of(contract.getContractId(), contract.getName(), contract.getContractStatus());
    }

    public List<ContractDTO> getContracts() {
        var contracts = contractRepository.findAll();
        return contracts
                .stream()
                .map(contract -> ContractDTO.of(contract.getContractId(), contract.getName(), contract.getContractStatus()))
                .toList();
    }

    public void deleteContract(String contractId) {
        contractRepository.deleteById(ContractId.from(contractId));
    }

    public void updateContract(UpdateContractDTO updateContractDTO) {
        var contractIdConv = ContractId.from(updateContractDTO.contractId().asString());

        contractRepository
                .findById(contractIdConv)
                .ifPresentOrElse(contract ->{
                    contract.updateName(updateContractDTO.name());
                    contractRepository.save(contract);
                }, () -> {
                    throw new RuntimeException();
                });
    }
}
