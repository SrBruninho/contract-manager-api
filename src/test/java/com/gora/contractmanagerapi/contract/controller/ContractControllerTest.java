package com.gora.contractmanagerapi.contract.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.gora.contractmanagerapi.contract.domain.ContractId;
import com.gora.contractmanagerapi.contract.domain.enums.ContractStatus;
import com.gora.contractmanagerapi.contract.dto.UpdateContractDTO;
import com.gora.contractmanagerapi.contract.hateoas.ContractModel;
import com.gora.contractmanagerapi.contract.util.ContractTestFactory;
import com.gora.contractmanagerapi.utils.AbstractIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static com.gora.contractmanagerapi.utils.TestUtils.getIdFromJson;
import static com.gora.contractmanagerapi.utils.TestUtils.getObjectMapper;
import static com.gora.contractmanagerapi.utils.TestUtils.objectToJson;
import static com.gora.contractmanagerapi.utils.TestUtils.removeEmbedded;
import static org.junit.jupiter.api.Assertions.*;

class ContractControllerTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Should include a draft contract")
    void shouldIncludeAContract() throws Exception {
        var contractDraft = ContractTestFactory.oneDraftContract();

        var contractSent = mockMvc.perform(MockMvcRequestBuilders.post(ContractController.PATH)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectToJson(contractDraft)))
                        .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        var contractPersisted = super.contractRepository.findByName(contractDraft.getName());

        assertEquals(contractPersisted.getContractId(), getIdFromJson(contractSent, ContractId.class));
        assertEquals(contractPersisted.getContractStatus(), ContractStatus.DRAFT);
    }

    @Test
    @DisplayName("Should retrieve a contract by id")
    void shouldRetrieveAContractById() throws Exception {
        var contract = super.contractRepository.save(ContractTestFactory.oneDraftContract());

        var contractSent = getIdFromJson(mockMvc.perform(MockMvcRequestBuilders
                        .get(ContractController.PATH + "/{contractId}", contract.getContractId().asString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn(), ContractModel.class);

        assertNotNull(contractSent);
        assertEquals(contract.getContractId().asString(), contractSent.getContractId());
        assertEquals(contract.getContractStatus().name(), contractSent.getContractStatus());
        assertEquals(contract.getName(), contractSent.getName());
    }

    @Test
    @DisplayName("Should retrieve all contracts")
    void shouldRetrieveAllContracts() throws Exception {
        var contractActivated = super.contractRepository.save(ContractTestFactory.oneActiveContract());
        var contractBlocked = super.contractRepository.save(ContractTestFactory.oneBlockedContract());
        var contractsPersisted = new ArrayList<>(List.of(contractBlocked, contractActivated));

        var result = mockMvc.perform(MockMvcRequestBuilders
                        .get(ContractController.PATH)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();

        List<ContractModel> contracts =
                getObjectMapper()
                        .convertValue(removeEmbedded(json,"contractModelList"), new TypeReference<>() {});

        List<String> expectedIds = contracts
                .stream()
                .map(ContractModel::getContractId)
                .toList();

        List<String> persistedIds = contractsPersisted
                .stream()
                .map(contract -> contract.getContractId().asString())
                .toList();

        assertFalse(contracts.isEmpty());
        assertEquals(2, contracts.size());

        assertTrue(persistedIds.containsAll(expectedIds));
    }

    @Test
    @DisplayName("Should delete a contract by Id")
    void shouldDeleteAContractById() throws Exception {
        var contractPersisted = super.contractRepository.save(ContractTestFactory.oneActiveContract());

        var result = mockMvc.perform(MockMvcRequestBuilders
                        .delete(ContractController.PATH + "/{contractId}", contractPersisted.getContractId().asString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andReturn();

        assertEquals(result.getResponse().getStatus(), HttpStatus.NO_CONTENT.value());
    }

    @Test
    @DisplayName("Should update a contract")
    void shouldUpdateAContractById() throws Exception {
        var contractPersisted = super.contractRepository.save(ContractTestFactory.oneActiveContract());
        String namePersisted = contractPersisted.getName();
        var updateContractDTO = new UpdateContractDTO(contractPersisted.getContractId(), "Novo Nome");

        mockMvc.perform(MockMvcRequestBuilders.put(ContractController.PATH + "/update")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectToJson(updateContractDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        var contractChanged = super.contractRepository.findByName(updateContractDTO.name());

        assertEquals(contractPersisted.getContractId(), contractChanged.getContractId());
        assertNotEquals(namePersisted, contractChanged.getName());
    }
}