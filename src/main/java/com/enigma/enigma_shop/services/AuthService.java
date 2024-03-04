package com.enigma.enigma_shop.services;

import com.enigma.enigma_shop.dto.request.AuthRequest;
import com.enigma.enigma_shop.dto.response.LoginRespone;
import com.enigma.enigma_shop.dto.response.RegisterResponse;

public interface AuthService {
    RegisterResponse register(AuthRequest request);
    RegisterResponse registerAdmin(AuthRequest request);
    LoginRespone login(AuthRequest request);

}
