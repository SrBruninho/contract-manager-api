package com.gora.contractmanagerapi.contract.hateoas;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@AllArgsConstructor(staticName = "from")
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContractModel extends RepresentationModel<ContractModel> {
    private String contractId;
    private String name;
    private String contractStatus;
}