package com.optifolio.mapper;


import com.optifolio.dto.UserCreateDTO;
import com.optifolio.dto.UserDTO;
import com.optifolio.dto.UserUpdateDTO;
import com.optifolio.models.Portfolio;
import com.optifolio.models.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    //Mapping from  User entity to UserDTO
    UserDTO toUserDTO(User user);

    //Mapping from  UserDTO  to User entity
    User toUserEntity(UserDTO userDTO);

    //Mapping from UserCreateDTO to User entity
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    User toUserEntity(UserCreateDTO userCreateDTO);

    //Update existing User entity  from UserUpdateDTO
    @Mapping(target = "userId",ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateUserEntityFromDTO(UserUpdateDTO userUpdateDTO, @MappingTarget User user);

    //Mapping from List of user entities to list of userDTO
    List<UserDTO> toUserDTOS(List<User> users);

    @AfterMapping
    default void setTimestamps(@MappingTarget User user) {
        LocalDateTime now = LocalDateTime.now();
        if (user.getCreatedAt() == null) {
            user.setCreatedAt(now);
        }
        user.setUpdatedAt(now);
    }
}
