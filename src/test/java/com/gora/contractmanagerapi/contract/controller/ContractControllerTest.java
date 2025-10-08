package com.gora.contractmanagerapi.contract.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gora.contractmanagerapi.contract.domain.ContractId;
import com.gora.contractmanagerapi.contract.util.ContractTestFactory;
import com.gora.contractmanagerapi.utils.AbstractIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.gora.contractmanagerapi.utils.TestUtils.getIdFromJson;
import static com.gora.contractmanagerapi.utils.TestUtils.objectToJson;
import static org.junit.jupiter.api.Assertions.*;

class ContractControllerTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private static final ObjectMapper objectMapper = new ObjectMapper();


    @Test
    @DisplayName("Should include a activated contract")
    void shouldIncludeAContract() throws Exception {
        var contractActivated = ContractTestFactory.oneActiveContract();

        var contractSent = mockMvc.perform(MockMvcRequestBuilders.post(ContractController.PATH)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectToJson(contractActivated)))
                        .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
        var contractPersisted = super.contractRepository.findByName(contractActivated.getName());

        assertEquals(contractPersisted.getContractId(), getIdFromJson(contractSent, ContractId.class));
    }
}