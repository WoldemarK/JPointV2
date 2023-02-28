package com.example.JPointV2.service;

import com.example.JPointV2.dto.UserDto;
import com.example.JPointV2.exception.AllException;
import com.example.JPointV2.mapper.UserMapper;
import com.example.JPointV2.model.Department;
import com.example.JPointV2.model.Post;
import com.example.JPointV2.model.Task;
import com.example.JPointV2.model.User;
import com.example.JPointV2.repository.DepartmentRepository;
import com.example.JPointV2.repository.PostRepository;
import com.example.JPointV2.repository.TaskRepository;
import com.example.JPointV2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final DepartmentRepository departmentRepository;
    private final PostRepository postRepository;
    private final TaskRepository taskRepository;

    public Optional<UserDto> getUsersById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::convertPersonToDto);

    }

    @Transactional
    public UserDto createNewUser(@Validated UserDto userDto) {
        User user = userMapper.convertDtoToPerson(userDto);
        return userMapper.convertPersonToDto(userRepository.save(user));
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::convertPersonToDto)
                .collect(Collectors.toList());
    }


    @Transactional
    public UserDto update(@Validated UserDto userDto, Long id) {
        User _users = userRepository.findById(id).get();

        _users.setLogin(userDto.getLogin());
        _users.setPassword(userDto.getPassword());
        _users.setFirstName(userDto.getFirstName());
        _users.setLastName(userDto.getLastName());
        _users.setBirth(userDto.getBirth());
        _users.setPhoneNumber(userDto.getPhoneNumber());
        _users.setEmail(userDto.getEmail());

        userRepository.save(_users);

        userMapper.convertPersonToDto(_users);

        return userMapper.convertPersonToDto(_users);
    }

    @Transactional
    public User createUserAndDepartmentAndPost(@Validated User _user, Long departmentId, Long postId) {
        _user.addDepartment(departmentRepository.findById(departmentId).get());
        _user.addPost(postRepository.findById(postId).get());
        return userRepository.save(_user);
    }


    @Transactional
    public void deleteId(@Validated Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
        }
        throw new AllException("Пользователя с " + id + " не существует");
    }

    public List<UserDto> getNames(String _nameF, String _nameL) {

        return userRepository
                .findByFirstNameOrLastName(_nameF, _nameL)
                .stream()
                .map(userMapper::convertPersonToDto)
                .collect(Collectors.toList());
    }

    public List<UserDto> startWithNames(String _name) {
        return userRepository
                .findByFirstNameIsStartingWith(_name)
                .stream()
                .map(userMapper::convertPersonToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public Optional<User> applyPost(Long userId, Long postId) {
        User user = userRepository.findById(userId).get();
        Post post = postRepository.findById(postId).get();
        user.addPost(post);
        postRepository.save(post);
        return Optional.of(userRepository.save(user));
    }

    @Transactional
    public Optional<User> applyDepartments(Long userId, Long depId) {
        User user = userRepository.findById(userId).get();
        Department department = departmentRepository.findById(depId).get();
        user.addDepartment(department);
        departmentRepository.save(department);
        return Optional.of(userRepository.save(user));
    }

    @Transactional
    public Optional<User> applyDepartmentsAddPast(Long userId, Long depId, Long postId) {
        User user = userRepository.findById(userId).get();
        Department department = departmentRepository.findById(depId).get();
        Post post = postRepository.findById(postId).get();
        user.addDepartment(department);
        departmentRepository.save(department);
        user.addPost(post);
        postRepository.save(post);
        return Optional.of(userRepository.save(user));
    }

    @Transactional
    public Optional<User> applyTask(Long userId, Long taskId) {
        User user = userRepository.findById(userId).get();
        Task task = taskRepository.findById(taskId).get();

        user.addTask(task);
        task.addUser(user);
        taskRepository.save(task);

        return Optional.of(userRepository.save(user));
    }
}
