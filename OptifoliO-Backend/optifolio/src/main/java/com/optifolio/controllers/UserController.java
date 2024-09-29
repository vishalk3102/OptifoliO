package com.optifolio.controllers;

import com.optifolio.dto.UserCreateDTO;
import com.optifolio.dto.UserDTO;
import com.optifolio.repositories.UserRepository;
import com.optifolio.services.UserService;
import jdk.jshell.spi.ExecutionControl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(path = UserController.PATH,produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    public  static final String PATH = "/api/v1/users";

    private final UserService userService;


    @PostMapping("/add-user")
    public ResponseEntity<UserDTO> addUser(@RequestBody UserCreateDTO userCreateDTO)
    {
        UserDTO savedUser=userService.addUser(userCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
}
