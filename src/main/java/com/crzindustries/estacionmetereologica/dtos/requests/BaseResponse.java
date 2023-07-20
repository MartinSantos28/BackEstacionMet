package com.crzindustries.estacionmetereologica.dtos.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Builder @Getter @Setter
public class BaseResponse {

    private HttpStatus status;

    private Integer statusCode;

    private String message;

    private Boolean success;

    private Object data;
}
