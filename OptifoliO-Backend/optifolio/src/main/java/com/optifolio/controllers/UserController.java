package com.optifolio.controllers;

import com.optifolio.dto.UserCreateDTO;
import com.optifolio.dto.UserDTO;
import com.optifolio.dto.UserUpdateDTO;
import com.optifolio.exceptions.UserAlreadyExistException;
import com.optifolio.exceptions.UserNotFoundException;
import com.optifolio.repositories.UserRepository;
import com.optifolio.services.UserService;
import jdk.jshell.spi.ExecutionControl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(path = UserController.PATH,produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    public  static final String PATH = "/api/v1/users";

    @Autowired
    private  UserService userService;


    @GetMapping("/get-all-users")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> users=userService.getAllusers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }
    @GetMapping("/get-user/{userId}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String userId) throws UserNotFoundException {
        UserDTO user=userService.getUserById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping("/add-user")
    public ResponseEntity<UserDTO> addUser(@RequestBody UserCreateDTO userCreateDTO) throws UserAlreadyExistException {
        UserDTO savedUser=userService.addUser(userCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PutMapping("/update-user/{userId}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserUpdateDTO userUpdateDTO) throws UserNotFoundException {
        UserDTO savedUser=userService.updateUser(userUpdateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @DeleteMapping("/delete-user/{userId}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable String userId) throws UserNotFoundException {
        UserDTO deleteUser=userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(deleteUser);
    }
}
