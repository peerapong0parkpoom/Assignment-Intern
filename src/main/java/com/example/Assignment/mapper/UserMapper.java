package com.example.Assignment.mapper;

import com.example.Assignment.models.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class UserMapper {
    public   UserDTO mapToDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setU_name(user.getU_name());
        userDTO.setDepartment(user.getDepartment());

        return userDTO;
    }

    public  User mapToEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setU_name(userDTO.getU_name());
        user.setDepartment(userDTO.getDepartment());
        return user;
    }
    public List<UserDTO> toDtoList(List<User> users) {
        return users.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public List<User> toEntityList(List<UserDTO> userDtos) {
        return userDtos.stream().map(this::mapToEntity).collect(Collectors.toList());
    }
}
