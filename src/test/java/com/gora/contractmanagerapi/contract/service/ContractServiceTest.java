package com.gora.contractmanagerapi.contract.service;

import com.gora.contractmanagerapi.contract.domain.ContractId;
import com.gora.contractmanagerapi.contract.domain.enums.ContractStatus;
import com.gora.contractmanagerapi.contract.dto.CreateContractDTO;
import com.gora.contractmanagerapi.contract.dto.UpdateContractDTO;
import com.gora.contractmanagerapi.contract.exception.CMAContractNameInvalidSizeException;
import com.gora.contractmanagerapi.contract.exception.CMAContractNotFoundException;
import com.gora.contractmanagerapi.contract.util.ContractTestFactory;
import com.gora.contractmanagerapi.utils.AbstractIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContractServiceTest extends AbstractIntegrationTest {

    @Test
    @DisplayName("Should include a contract")
    void shouldIncludeAContract() {
        var createContractDTO = new CreateContractDTO();
        createContractDTO.setName("New Contract");

        contractService.included(createContractDTO);

        var contractPersisted = super.contractRepository.findByName(createContractDTO.getName());

        assertNotNull(contractPersisted);
        assertEquals(contractPersisted.getContractStatus(), ContractStatus.DRAFT);
    }

    @Test
    @DisplayName("Should throws Invalid Name Size when try to include a contract more than 100 chars")
    void shouldThrowsInvalidNameSizeWhenTryToIncludeAContractMoreThan100Chars() {
        var createContractDTO = new CreateContractDTO();
        createContractDTO.setName("EndToEndSimulationOfHighThroughputMessageProcessingEnvironmentTest" +
                "EndToEndSimulationOfHighThroughputMessageProcessingEnvironmentTest" +
                "EndToEndSimulationOfHighThroughputMessageProcessingEnvironmentTest" +
                "EndToEndSimulationOfHighThroughputMessageProcessingEnvironmentTest");

        assertThrows(CMAContractNameInvalidSizeException.class,
                () -> contractService.included(createContractDTO));
    }

    @Test
    @DisplayName("Should throws Invalid Name Size when try to include a contract without name")
    void shouldThrowsInvalidNameSizeWhenTryToIncludeAContractWithoutName() {
        var createContractDTO = new CreateContractDTO();
        createContractDTO.setName("");

        assertThrows(CMAContractNameInvalidSizeException.class,
                () -> contractService.included(createContractDTO));
    }

    @Test
    @DisplayName("Should delete a contract By Id")
    void shouldUpdateAContractById() {
        var contractDraft = ContractTestFactory.oneDraftContract();
        ContractTestFactory.persistOnDatabase(super.getContractRepository(), contractDraft);

        var updateContractDTO = new UpdateContractDTO(contractDraft.getContractId(), "Contract Updated");

        contractService.updateContract(updateContractDTO);

        assertTrue(super.contractRepository.findById(contractDraft.getContractId()).isPresent());
        assertNotEquals(contractDraft.getName(), updateContractDTO.name());
        assertEquals(contractDraft.getContractId(), updateContractDTO.contractId());
    }

    @Test
    @DisplayName("Should throws Not Found when try to update a contract")
    void shouldThrowsNotFoundWhenTryUpdateAContractById() {
        var updateContractDTO = new UpdateContractDTO(ContractId.generate(), "Contract - Not Found");

        assertThrows(CMAContractNotFoundException.class,
                () -> contractService.updateContract(updateContractDTO));
    }

    @Test
    @DisplayName("Should delete a contract By Id")
    void shouldDeleteAContractById() {
        var contractDraft = ContractTestFactory.oneDraftContract();
        ContractTestFactory.persistOnDatabase(super.getContractRepository(), contractDraft);

        contractService.deleteContract(contractDraft.getContractId().asString());

        assertTrue(super.contractRepository.findById(contractDraft.getContractId()).isEmpty());
    }

    @Test
    @DisplayName("Should throws Not Found when try to delete a contract")
    void shouldThrowsNotFoundWhenTryDeleteAContractById() {
        assertThrows(CMAContractNotFoundException.class,
                () -> contractService.deleteContract(ContractId.generate().asString()));
    }

    @Test
    @DisplayName("Should find a contract By Id")
    void shouldFindAContractById() {
        var contractDraft = ContractTestFactory.oneDraftContract();
        ContractTestFactory.persistOnDatabase(super.getContractRepository(), contractDraft);

        var contractPersisted = contractService.getContractById(contractDraft.getContractId().asString());

        assertNotNull(contractPersisted);
        assertEquals(contractPersisted.getContractId(), contractDraft.getContractId());
        assertEquals(contractPersisted.getContractStatus(), ContractStatus.DRAFT);
    }

    @Test
    @DisplayName("Should throws Not Found when try to find a contract")
    void shouldThrowsNotFoundWhenTryFindAContractById() {
        assertThrows(CMAContractNotFoundException.class,
                () -> contractService.getContractById(ContractId.generate().asString()));
    }

    @Test
    @DisplayName("Should get all contracts")
    void shouldFindAllContracts() {
        var contractDraft = ContractTestFactory.oneDraftContract();
        var contractActive = ContractTestFactory.oneActiveContract();
        var contractBlocked = ContractTestFactory.oneBlockedContract();

        ContractTestFactory.persistOnDatabase(super.getContractRepository(), contractDraft);
        ContractTestFactory.persistOnDatabase(super.getContractRepository(), contractActive);
        ContractTestFactory.persistOnDatabase(super.getContractRepository(), contractBlocked);

        var contracts = contractService.getContracts();

        assertNotNull(contracts);
        assertEquals(3, contracts.size());
    }

}