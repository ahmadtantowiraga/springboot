package com.enigma.enigma_shop.services.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.enigma.enigma_shop.dto.response.JwtClaims;
import com.enigma.enigma_shop.services.JwtSevice;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;

@Service
public class JwtServiceImpl implements JwtSevice {
    private final String JWT_SECRET;
    private final String ISSUER;

    public JwtServiceImpl(@Value("${enigma_shop.jwt.secret_key}") String JWT_SECRET,
                          @Value("enigma_shop.jwt.issuer") String ISSUER) {
        this.JWT_SECRET = JWT_SECRET;
        this.ISSUER = ISSUER;
    }

    @Override
    public String generateToken() {
        try{
            Algorithm algorithm=Algorithm.HMAC512(JWT_SECRET);
            return JWT.create()
                    .withIssuedAt(Instant.now())
                    .withExpiresAt(Instant.now().plusSeconds(60*15))
                    .withIssuer(ISSUER)
                    .sign(algorithm);
        }catch (JWTCreationException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "error while creating jwt token");
        }
    }

    @Override
    public boolean verifyJwtToken(String token) {
        return false;
    }

    @Override
    public JwtClaims getClaimsByToken(String token) {
        return null;
    }
}
