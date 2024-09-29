package com.optifolio.services;

import com.optifolio.dto.UserCreateDTO;
import com.optifolio.dto.UserDTO;

public interface UserService {
    UserDTO addUser(UserCreateDTO userCreateDTO);
}
