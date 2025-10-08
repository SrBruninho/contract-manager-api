package com.gora.contractmanagerapi.contract.repository;

import com.gora.contractmanagerapi.contract.domain.Contract;
import com.gora.contractmanagerapi.contract.domain.ContractId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends JpaRepository<Contract, ContractId> {

    Contract save(Contract contract);

    Contract findByName(String name);
}
