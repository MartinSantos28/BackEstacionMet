package com.crzindustries.estacionmetereologica.dtos.requests;

import lombok.Getter;

@Getter
public class TokenRequest {
    private String email;
    private String password;
}
