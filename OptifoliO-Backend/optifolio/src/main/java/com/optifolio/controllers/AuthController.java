package com.optifolio.controllers;

import com.optifolio.dto.JwtRequest;
import com.optifolio.dto.JwtResponse;
import com.optifolio.exceptions.BadCredentialsException;
import com.optifolio.services.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(path = AuthController.PATH,produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AuthController {

    public static final String PATH ="/api/auth" ;

    @Autowired
    private AuthService authService;

    //LOGIN AUTHENTICATION FUNCTIONALITY
    @PostMapping("/login")
    ResponseEntity<JwtResponse> login(@RequestBody JwtRequest jwtRequest) throws BadCredentialsException {
        JwtResponse response=authService.generateAndAuthenticateToken(jwtRequest);
        return  ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //LOGOUT FUNCTIONALITY
    @PostMapping("/logout")
    ResponseEntity<?> logout(@RequestHeader("Authorization") String token){
        Boolean response=authService.blacklistToken(token);
        Map<String, String> responseBody = new HashMap<>();
        if (response)
        {
            responseBody.put("message", "Logout successful");
            return  ResponseEntity.status(HttpStatus.OK).body(responseBody);
        }
        else
        {
            responseBody.put("message", "Invalid token");
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
        }
    }
}
