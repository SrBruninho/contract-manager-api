package com.gora.contractmanagerapi.contract.repository;

import com.gora.contractmanagerapi.contract.domain.Contract;
import com.gora.contractmanagerapi.contract.domain.ContractId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContractRepository extends JpaRepository<Contract, ContractId> {

    Contract save(Contract contract);

    Contract findByName(String name);

    Optional<Contract> findById(ContractId contractId);

    List<Contract> findAll();
}
