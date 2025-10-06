package com.gora.contractmanagerapi.contract.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class CreateContractDTO {

    @Size(min = 1, max = 50, message = "{CreateContractDTO.name.Size}")
    private String name;
}
