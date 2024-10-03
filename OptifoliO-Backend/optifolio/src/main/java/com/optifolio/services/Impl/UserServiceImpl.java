package com.optifolio.services.Impl;

import com.optifolio.dto.UserCreateDTO;
import com.optifolio.dto.UserDTO;
import com.optifolio.dto.UserUpdateDTO;
import com.optifolio.exceptions.UserAlreadyExistException;
import com.optifolio.exceptions.UserNotFoundException;
import com.optifolio.mapper.UserMapper;
import com.optifolio.models.Enum;
import com.optifolio.models.User;
import com.optifolio.repositories.UserRepository;
import com.optifolio.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

   @Autowired
   private  UserRepository userRepository;

   @Autowired
   private  UserMapper userMapper;

    //    FUNCTION TO GET ALL USER
    @Override
    public List<UserDTO> getAllusers(){
        List<User> users= userRepository.findAll();
        if(users.isEmpty())
        {
            return List.of();
        }
        return userMapper.toUserDTOS(users);
    }

    //    FUNCTION TO GET USER BY EMAILID
    @Override
    public UserDTO getUserById(String userId) throws UserNotFoundException {
        User user=userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return userMapper.toUserDTO(user);
    }

//    FUNCTION TO ADD USER
    @Override
    public UserDTO addUser(UserCreateDTO userCreateDTO) throws UserAlreadyExistException {
        User existingUser=userRepository.findByEmailId(userCreateDTO.getEmailId());
        if(existingUser!=null)
        {
            throw new UserAlreadyExistException();
        }
        User user =userMapper.toUserEntity(userCreateDTO);
        userMapper.setTimestamps(user);
        User savedUser=userRepository.save(user);
        return userMapper.toUserDTO(savedUser);
    }

//    FUNCTION TO UPDATE EXISTING USER
    @Override
    public UserDTO updateUser(UserUpdateDTO userUpdateDTO) throws UserNotFoundException {
        User existingUser=userRepository.findById(userUpdateDTO.getUserId()).orElseThrow(UserNotFoundException::new);
        userMapper.updateUserEntityFromDTO(userUpdateDTO,existingUser);
        userMapper.setTimestamps(existingUser);
        User updatedUser=userRepository.save(existingUser);
        return  userMapper.toUserDTO(updatedUser);
    }

    //    FUNCTION TO DELETE EXISTING USER
    @Override
    public UserDTO deleteUser(String userId) throws UserNotFoundException {
        User user=userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
        return userMapper.toUserDTO(user);
    }

}
