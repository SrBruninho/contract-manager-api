package com.gora.contractmanagerapi.contract.service;

import com.gora.contractmanagerapi.contract.domain.Contract;
import com.gora.contractmanagerapi.contract.domain.ContractId;
import com.gora.contractmanagerapi.contract.domain.enums.ContractStatus;
import com.gora.contractmanagerapi.contract.dto.CreateContractDTO;
import com.gora.contractmanagerapi.contract.repository.ContractRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Transactional
@Service
public class ContractService {

    private final ContractRepository contractRepository;

    public ContractId included(@Valid CreateContractDTO createContractDTO) {
        var contract = Contract
                .builder()
                .name(createContractDTO.getName())
                .contractStatus(ContractStatus.ACTIVE)
                .build();

        contractRepository.save(contract);

        return contract.getContractId();
    }
}
