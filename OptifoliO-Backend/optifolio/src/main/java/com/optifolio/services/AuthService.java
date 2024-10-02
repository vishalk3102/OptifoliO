package com.optifolio.services;

import com.optifolio.dto.JwtRequest;
import com.optifolio.dto.JwtResponse;
import com.optifolio.exceptions.BadCredentialsException;

public interface AuthService {
    Boolean blacklistToken(String token);

    JwtResponse generateAndAuthenticateToken(JwtRequest jwtRequest) throws BadCredentialsException;
}
