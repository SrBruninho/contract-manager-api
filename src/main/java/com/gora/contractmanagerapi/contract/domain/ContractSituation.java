package com.gora.contractmanagerapi.contract.domain;

import com.gora.contractmanagerapi.contract.domain.enums.ContractStatus;
import com.gora.contractmanagerapi.contract.domain.enums.SituationReason;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
@Table
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ContractSituation {

    @Id
    private ContractSituationId id;

    @Enumerated(EnumType.STRING)
    @Column(name = "contract_status")
    private ContractStatus contractStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "situation_reason")
    private SituationReason situationReason;

    @NotNull
    private ContractId contractId;

    public static ContractSituation of(ContractStatus contractStatus, SituationReason situationReason, ContractId contractId){
        return new ContractSituation(ContractSituationId.generate(), contractStatus, situationReason, contractId);
    }

    public ContractSituation(ContractSituationId id, ContractStatus contractStatus, SituationReason situationReason, ContractId contractId) {
        this.id = id;
        this.contractStatus = contractStatus;
        this.situationReason = situationReason;
        this.contractId = contractId;
    }

    public static ContractSituation ofDraft(ContractId contractId){
        return new ContractSituation(ContractSituationId.generate(), ContractStatus.DRAFT, null, contractId);
    }

    public static ContractSituation ofActivated(ContractId contractId){
        return new ContractSituation(ContractSituationId.generate(), ContractStatus.ACTIVE, null, contractId);
    }

    public static ContractSituation ofBlocked(ContractId contractId){
        return new ContractSituation(ContractSituationId.generate(), ContractStatus.BLOCKED, null, contractId);
    }

}
