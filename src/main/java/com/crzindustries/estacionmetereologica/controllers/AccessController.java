package com.crzindustries.estacionmetereologica.controllers;

import com.crzindustries.estacionmetereologica.dtos.requests.BaseResponse;
import com.crzindustries.estacionmetereologica.dtos.requests.TokenRequest;
import com.crzindustries.estacionmetereologica.services.interfaces.IAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("access")
public class AccessController {

    @Autowired
    private IAccessService service;

    @PostMapping("token")
    public ResponseEntity<BaseResponse> token(@RequestBody TokenRequest request) {
        BaseResponse baseResponse = service.token(request);

        return new ResponseEntity<>(baseResponse, baseResponse.getStatus());
    }
}
