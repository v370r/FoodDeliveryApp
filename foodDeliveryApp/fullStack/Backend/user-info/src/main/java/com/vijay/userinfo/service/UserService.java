package com.vijay.userinfo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vijay.userinfo.dto.UserDTO;
import com.vijay.userinfo.entity.User;
import com.vijay.userinfo.mapper.UserMapper;
import com.vijay.userinfo.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO addUser(UserDTO userDTO) {

        User savedUser = userRepository.save(UserMapper.INSTANCE.mapUserDTOToUser(userDTO));
        return UserMapper.INSTANCE.mapUserTOUserDTO(savedUser);

    }

    public ResponseEntity<UserDTO> fetchUserDetailsById(Integer userId) {

        Optional<User> fetchedUser = userRepository.findById(userId);
        if (fetchedUser.isPresent()) {
            return new ResponseEntity<>(UserMapper.INSTANCE.mapUserTOUserDTO(fetchedUser.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

}
