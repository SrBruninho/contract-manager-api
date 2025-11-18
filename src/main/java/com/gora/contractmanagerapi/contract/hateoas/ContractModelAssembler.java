package com.gora.contractmanagerapi.contract.hateoas;

import com.gora.contractmanagerapi.contract.controller.ContractController;
import com.gora.contractmanagerapi.contract.dto.ContractDTO;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ContractModelAssembler implements RepresentationModelAssembler<ContractDTO, ContractModel> {

    @Override
    public ContractModel toModel(ContractDTO contractDTO) {
        var contractId = contractDTO.getContractId().asString();
        var contractModel = ContractModel.from(contractId, contractDTO.getName(), contractDTO.getContractStatus().name());

        contractModel.add(
                linkTo(methodOn(ContractController.class)
                .getContractById(contractId))
                .withSelfRel()

                // TODO implement affordance in future . . .
        );

        contractModel.add(
                linkTo(methodOn(ContractController.class)
                        .getAllContracts())
                        .withRel("all-contracts"));

        contractModel.add(linkTo(
                methodOn(ContractController.class).updateContract(null))
                .withRel("update"));

        contractModel.add(linkTo(
                methodOn(ContractController.class).deleteContract(contractId))
                .withRel("delete"));

        return contractModel;
    }

    @Override
    public CollectionModel<ContractModel> toCollectionModel(Iterable<? extends ContractDTO> contractDTOS) {
        var contractModels = new ArrayList<ContractModel>();

        contractDTOS
                .forEach(contractDTO -> contractModels
                        .add(toModel(contractDTO)));

        return CollectionModel.of(
                contractModels,
                linkTo(methodOn(ContractController.class).getAllContracts()).withSelfRel()
        );
    }

}
