package com.crzindustries.estacionmetereologica.services;

import com.crzindustries.estacionmetereologica.dtos.requests.BaseResponse;
import com.crzindustries.estacionmetereologica.dtos.requests.TokenRequest;
import com.crzindustries.estacionmetereologica.entities.User;
import com.crzindustries.estacionmetereologica.repositories.IUserRepository;
import com.crzindustries.estacionmetereologica.services.interfaces.IAccessService;
import com.crzindustries.estacionmetereologica.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AccessServiceImpl implements IAccessService {

    @Autowired
    private IUserRepository userRepository;

    private final String secretKey = "X1axj9JU8E4gC10YRl3a2oGHC7ngQMC5"; // cambien esto por una secret key real

    @Override
    public BaseResponse token(TokenRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!isValidatePassword(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        Map<String, Object> claims = Map.of("email", user.getEmail());

        String token = JWTUtils.generateToken(user.getEmail(), claims, secretKey, 30);

        Map<String, Object> response = Map.of("token", token);

        return BaseResponse.builder()
                .status(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .success(Boolean.TRUE)
                .message("Token generated successfully")
                .data(response)
                .build();
    }

    private boolean isValidatePassword(String password, String passwordHash) {
        return BCrypt.checkpw(password, passwordHash);
    }
}
