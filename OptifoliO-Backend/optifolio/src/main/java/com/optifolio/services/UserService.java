package com.optifolio.services;

import com.optifolio.dto.UserCreateDTO;
import com.optifolio.dto.UserDTO;
import com.optifolio.dto.UserUpdateDTO;
import com.optifolio.exceptions.UserAlreadyExistException;
import com.optifolio.exceptions.UserNotFoundException;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllusers();
    UserDTO getUserById(String userId) throws UserNotFoundException;

    UserDTO addUser(UserCreateDTO userCreateDTO) throws UserAlreadyExistException;
    UserDTO updateUser(UserUpdateDTO userUpdateDTO) throws UserNotFoundException;
    UserDTO deleteUser(String emailId) throws UserNotFoundException;
}
