package com.optifolio.mapper;


import com.optifolio.dto.UserCreateDTO;
import com.optifolio.dto.UserDTO;
import com.optifolio.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    //Mapping from  User entity to UserDTO
    UserDTO toUserDTO(User user);

    //Mapping from  UserDTO  to User entity
    User toUserEntity(UserDTO userDTO);

    //Mapping from UserCreateDTO to User entity
    User toUserEntity(UserCreateDTO userCreateDTO);

}
