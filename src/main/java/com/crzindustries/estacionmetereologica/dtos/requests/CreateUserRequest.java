package com.crzindustries.estacionmetereologica.dtos.requests;

import lombok.Getter;

@Getter
public class CreateUserRequest {
    private String fullName;
    private String email;
    private String password;
}
