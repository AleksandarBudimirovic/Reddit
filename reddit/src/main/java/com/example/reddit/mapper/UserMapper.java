package com.example.reddit.mapper;

import com.example.reddit.dto.UserDTO;
import com.example.reddit.model.User;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO modelToDto(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setAvatar(user.getAvatar());
        userDTO.setDescription(user.getDescription());
        userDTO.setDisplayName(user.getDisplayName());
        userDTO.setPassword(user.getPassword());
        userDTO.setRegistrationDate(user.getRegistrationDate());
        userDTO.setRole(user.getRole());
        userDTO.setUsername(user.getUsername());



        return userDTO;
    }

    public User dtoToModel(UserDTO userDTO) {
        User user = new User();

        user.setId(userDTO.getId());
        user.setAvatar(userDTO.getAvatar());
        user.setDescription(userDTO.getDescription());
        user.setDisplayName(userDTO.getDisplayName());
        user.setPassword(userDTO.getPassword());
        user.setRegistrationDate(userDTO.getRegistrationDate());
        user.setRole(userDTO.getRole());
        user.setUsername(userDTO.getUsername());



        return user;
    }
}
