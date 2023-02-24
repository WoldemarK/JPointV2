package com.example.JPointV2.dto;

import com.example.JPointV2.model.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Schema(description = "Сущность пользователя")
public class UserDto {
    @Schema(description = "Уникальный идентификатор пользователя")
    private Long id;
    @Schema(description = "Логин")
    private String login;
    @Schema(description = "Пароль")
    private String password;
    @Schema(description = "Имя")
    private String firstName;
    @Schema(description = "Фамилия")
    private String lastName;
    @Schema(description = "Дата рождения")
    private String birth;
    @Schema(description = "Номер телефона")
    private String phoneNumber;
    @Schema(description = "Почта")
    private String email;
    @Schema(description = "Пол")
    private String sex;
    @Schema(description = "Активный/ Не активный")
    private boolean isActive;
    @Schema(description = "Дата заведения")
    private LocalDateTime creation;
    @Schema(description = "Дата обновления данных")
    private LocalDateTime update;
    @Schema(description = "class Department по ID")
    private Long departmentId;
//    @Schema(description = "class Post по ID")
//    private List<Long> postsId;
private List<Post> postsId;
    @Schema(description = "class Task по ID")
    private Long taskId;
    @Schema(description = "class Company по ID")
    private Long companyId;
}
