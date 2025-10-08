package com.gora.contractmanagerapi.utils;

import com.gora.contractmanagerapi.contract.repository.ContractRepository;
import com.gora.contractmanagerapi.contract.service.ContractService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;

@Getter
@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "it")
public abstract class AbstractIntegrationTest {

    @Autowired
    protected ContractService contractService;

    @Autowired
    protected ContractRepository contractRepository;

}
