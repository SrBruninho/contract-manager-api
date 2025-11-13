package com.gora.contractmanagerapi.contract.repository;

import com.gora.contractmanagerapi.contract.domain.Contract;
import com.gora.contractmanagerapi.contract.domain.ContractId;
import com.gora.contractmanagerapi.infra.persistence.UserScopedRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends UserScopedRepository<Contract, ContractId> {

    Contract findByName(String name);

}
