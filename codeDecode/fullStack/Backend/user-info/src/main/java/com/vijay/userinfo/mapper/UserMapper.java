package com.vijay.userinfo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.vijay.userinfo.dto.UserDTO;
import com.vijay.userinfo.entity.User;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User mapUserDTOToUser(UserDTO userDTO);
    UserDTO mapUserTOUserDTO(User user);

}
