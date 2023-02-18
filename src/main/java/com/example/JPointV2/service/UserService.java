package com.example.JPointV2.service;
import com.example.JPointV2.exception.AllException;
import com.example.JPointV2.model.Department;
import com.example.JPointV2.model.Post;
import com.example.JPointV2.model.User;
import com.example.JPointV2.repository.DepartmentRepository;
import com.example.JPointV2.repository.PostRepository;
import com.example.JPointV2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final PostRepository postRepository;
    @Transactional
    public User createNewUser(@Validated User _user) {
        return userRepository.save(_user);
    }

    @Transactional
    public void update(@Validated User user, Long id) {
        User _users = getUsersById(id);
        _users.setLogin(user.getLogin());
        _users.setPassword(user.getPassword());
        _users.setFirstName(user.getFirstName());
        _users.setLastName(user.getLastName());
        _users.setBirth(user.getBirth());
        _users.setPhoneNumber(user.getPhoneNumber());
        _users.setEmail(user.getEmail());
        _users.setCreation(user.getCreation());
        _users.setUpdate(user.getUpdate());
        _users.setDepartments(user.getDepartments());
        _users.setPosts(user.getPosts());
        userRepository.save(_users);

    }

    @Transactional
    public User createUserAndDepartmentAndPost(@Validated User _user, Long departmentId, Long postId) {
        _user.addDepartment(departmentRepository.findById(departmentId).get());
        _user.addPost(postRepository.findById(postId).get());


        return userRepository.save(_user);
    }

    public User getUsersById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new AllException("Пользователя с " + id + " не существует"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public void deleteId(@Validated Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
        }
        throw new AllException("Пользователя с " + id + " не существует");
    }

    public List<User> getNames(String _nameF, String _nameL) {
        return userRepository.findByFirstNameOrLastName(_nameF, _nameL);
    }

    public List<User> startWithNames(String _name) {
        return userRepository.findByFirstNameIsStartingWith(_name);
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
}
