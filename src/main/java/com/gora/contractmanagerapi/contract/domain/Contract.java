package com.gora.contractmanagerapi.contract.domain;

import com.gora.contractmanagerapi.contract.domain.enums.ContractStatus;
import com.gora.contractmanagerapi.contract.domain.enums.SituationReason;
import com.gora.contractmanagerapi.contract.exception.CMAContractNameInvalidSizeException;
import com.gora.contractmanagerapi.infra.auditing.AuditableEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

import static com.gora.contractmanagerapi.contract.domain.enums.ContractStatus.ACTIVE;
import static com.gora.contractmanagerapi.contract.domain.enums.ContractStatus.BLOCKED;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
@Table
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Contract extends AuditableEntity {

    private static final int NAME_MIN_SIZE = 1;
    private static final int NAME_MAX_SIZE = 100;

    @Id
    @NonNull
    @EqualsAndHashCode.Include
    private ContractId contractId;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "contractId", referencedColumnName = "contractId", nullable = false, insertable = false)
    private List<ContractSituation> contractSituations;

    @Enumerated(EnumType.STRING)
    private ContractStatus contractStatus;

    public Contract(ContractBuilder contractBuilder){
        this.contractId = contractBuilder.contractId;
        this.name = contractBuilder.name;
        this.contractStatus = ContractStatus.DRAFT;
        this.contractSituations = new ArrayList<>();
        this.contractSituations.add(ContractSituation.ofDraft(this.contractId));

        validateContract();
    }

    public static ContractBuilder builder() {
        return new ContractBuilder();
    }

    private void validateContract() {
        if (this.name.isEmpty() || this.name.length() > NAME_MAX_SIZE)
            throw new CMAContractNameInvalidSizeException(NAME_MIN_SIZE, NAME_MAX_SIZE);
    }

    public void updateName(String name){
        this.name = name;
    }

    public void activate() {
        if (ACTIVE.equals(this.contractStatus))
            return;

        this.contractStatus = ACTIVE;
        this.contractSituations.add(ContractSituation.ofActivated(this.contractId));
    }

    public void block() {
        if (BLOCKED.equals(this.contractStatus))
            return;

        this.contractStatus = BLOCKED;
        this.contractSituations.add(ContractSituation.ofBlocked(this.contractId));
    }
}
