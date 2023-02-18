package com.example.JPointV2.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserDto {
    private Long id;

    private String login;
    private String password;

    private String firstName;

    private String lastName;

    private String birth;

    private String phoneNumber;

    private String email;
    private String sex;
    private boolean isActive;
    private LocalDateTime creation;

    private LocalDateTime update;
}
