package com.optifolio.services.Impl;

import com.optifolio.dto.UserCreateDTO;
import com.optifolio.dto.UserDTO;
import com.optifolio.mapper.UserMapper;
import com.optifolio.models.User;
import com.optifolio.repositories.UserRepository;
import com.optifolio.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

   private final UserRepository userRepository;
   private final UserMapper userMapper;


//    FUNCTION TO ADD USER
    @Override
    public UserDTO addUser(UserCreateDTO userCreateDTO) {
       User user =userMapper.toUserEntity(userCreateDTO);
       User savedUser=userRepository.save(user);
       return userMapper.toUserDTO(savedUser);
    }
}
