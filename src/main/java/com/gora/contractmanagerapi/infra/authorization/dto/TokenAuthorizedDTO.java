package com.gora.contractmanagerapi.infra.authorization.dto;


public record TokenAuthorizedDTO(String token, int expiresIn) {

    public static TokenAuthorizedDTO from(String token, int expiresIn){
        return new TokenAuthorizedDTO(token, expiresIn);
    }
}
