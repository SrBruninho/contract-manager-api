package com.gora.contractmanagerapi.contract.domain;

import com.gora.contractmanagerapi.contract.domain.enums.ContractStatus;
import com.gora.contractmanagerapi.contract.exception.CMAContractNameInvalidSizeException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
@Table
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Contract {

    private static final int NAME_MIN_SIZE = 1;
    private static final int NAME_MAX_SIZE = 100;

    @Id
    @NonNull
    @EqualsAndHashCode.Include
    private ContractId contractId;

    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "current_situation")
    private ContractStatus contractStatus;

    public Contract(ContractBuilder contractBuilder){
        this.contractId = contractBuilder.contractId;
        this.name = contractBuilder.name;
        this.contractStatus = contractBuilder.contractStatus;

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

}
