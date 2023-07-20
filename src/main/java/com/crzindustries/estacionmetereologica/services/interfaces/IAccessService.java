package com.crzindustries.estacionmetereologica.services.interfaces;

import com.crzindustries.estacionmetereologica.dtos.requests.BaseResponse;
import com.crzindustries.estacionmetereologica.dtos.requests.TokenRequest;

public interface IAccessService {
    BaseResponse token(TokenRequest request);
}
