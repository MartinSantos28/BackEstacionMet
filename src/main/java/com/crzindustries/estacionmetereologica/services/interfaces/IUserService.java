package com.crzindustries.estacionmetereologica.services.interfaces;

import com.crzindustries.estacionmetereologica.dtos.requests.BaseResponse;
import com.crzindustries.estacionmetereologica.dtos.requests.CreateUserRequest;

public interface IUserService {
    BaseResponse create(CreateUserRequest request);
}
