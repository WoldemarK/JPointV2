package com.example.JPointV2.servic;

import com.example.JPointV2.dto.UserDto;
import com.example.JPointV2.mapper.UserMapper;
import com.example.JPointV2.model.User;
import com.example.JPointV2.repository.UserRepository;
import com.example.JPointV2.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Spy
    private UserMapper userMapper;
    @InjectMocks
    private UserService userService;

    @Test
    public void shouldReturnUserById() {
        long id = 2;
        String login = "login";
        String password = "123";
        String firstName = " Tom";
        String lastName = "Jeri";
        String birth = "30";
        String phoneNumber = "232323";
        String email = "email@email.com";
        boolean isActive = true;
        String sex = "M";

        User mockUser = User.builder()
                .id(id)
                .login(login)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .birth(birth)
                .phoneNumber(phoneNumber)
                .email(email)
                .isActive(isActive)
                .sex(sex)
                .creation(LocalDate.now())
                .update(LocalDate.now())
                .build();

        Mockito.when(userRepository.findById(id))
                .thenReturn(Optional.of(mockUser));

        Optional<UserDto> user = userService.getUsersById(id);

        Assertions.assertEquals(id, user.get().getId());
        Assertions.assertEquals(login, user.get().getLogin());
        Assertions.assertEquals(password, user.get().getPassword());
        Assertions.assertEquals(firstName, user.get().getFirstName());
        Assertions.assertEquals(lastName, user.get().getLastName());
        Assertions.assertEquals(birth, user.get().getBirth());
        Assertions.assertEquals(phoneNumber, user.get().getPhoneNumber());
        Assertions.assertEquals(email, user.get().getEmail());
        Assertions.assertEquals(isActive, user.get().isActive());
        Assertions.assertEquals(sex, user.get().getSex());

    }

    @Test
    public void shouldUpdateUser() {
        long id = 2;
        String login = "login";
        String password = "123";
        String firstName = " Tom";
        String lastName = "Jeri";
        String birth = "30";
        String phoneNumber = "232323";
        String email = "email@email.com";
        boolean isActive = true;
        String sex = "M";

        User oldUser = User.builder()
                .id(id)
                .login(login)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .birth(birth)
                .phoneNumber(phoneNumber)
                .email(email)
                .isActive(isActive)
                .sex(sex)
                .creation(LocalDate.now())
                .update(LocalDate.now())
                .build();

        UserDto newUserDto = UserDto.builder()
                .id(id)
                .login(login)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .birth(birth)
                .phoneNumber(phoneNumber)
                .email(email)
                .isActive(isActive)
                .sex(sex)
                .creation(LocalDate.now().atStartOfDay())
                .update(LocalDate.now().atStartOfDay())
                .build();

        Mockito.when(userRepository.findById(id))
                .thenReturn(Optional.of(oldUser));

        UserDto userDto = userService.update(newUserDto, id);

        Assertions.assertEquals(id, userDto.getId());
        Assertions.assertEquals(login, userDto.getLogin());
        Assertions.assertEquals(password, userDto.getPassword());
        Assertions.assertEquals(firstName, userDto.getFirstName());
        Assertions.assertEquals(lastName, userDto.getLastName());
        Assertions.assertEquals(birth, userDto.getBirth());
        Assertions.assertEquals(phoneNumber, userDto.getPhoneNumber());
        Assertions.assertEquals(email, userDto.getEmail());
        Assertions.assertEquals(isActive, userDto.isActive());
        Assertions.assertEquals(sex, userDto.getSex());
    }
}
