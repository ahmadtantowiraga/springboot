package com.enigma.enigma_shop.services;

import com.enigma.enigma_shop.dto.response.JwtClaims;

public interface JwtSevice {
    String generateToken();
    boolean verifyJwtToken(String token);
    JwtClaims getClaimsByToken(String token);
}
