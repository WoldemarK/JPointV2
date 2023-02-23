package com.example.JPointV2.mapper;

import com.example.JPointV2.dto.UserDto;
import com.example.JPointV2.model.*;
import org.springframework.stereotype.Component;

@Component

public class UserMapper {
    public UserDto convertPersonToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .login(user.getLogin())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .birth(user.getBirth())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .sex(user.getSex())
                .isActive(user.isActive())
                .creation(user.getCreation().atStartOfDay())
                .update(user.getUpdate().atStartOfDay())
                .build();
    }

    public User convertDtoToPerson(UserDto dto) {
        return User.builder()
                .id(dto.getId())
                .login(dto.getLogin())
                .password(dto.getPassword())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .birth(dto.getBirth())
                .phoneNumber(dto.getPhoneNumber())
                .email(dto.getEmail())
                .sex(dto.getSex())
                .isActive(dto.isActive())
                .build();
    }

//    void updateFromDto(UserDto userDto, User user) {
//        userDto.setId(user.getId());
//    }
}
