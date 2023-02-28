package com.example.JPointV2.service;

import com.example.JPointV2.dto.TaskDto;
import com.example.JPointV2.exception.AllException;
import com.example.JPointV2.mapper.TaskMapper;
import com.example.JPointV2.model.Task;
import com.example.JPointV2.model.User;
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
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;

    public List<TaskDto> getAllTask() {
        return taskRepository.findAll()
                .stream()
                .map(taskMapper::convertTaskToDto)
                .collect(Collectors.toList());
    }
    @Transactional
    public TaskDto createNewTask(@Validated TaskDto taskDto) {
        Task task = taskMapper.convertDtoToTask(taskDto);
        return taskMapper.convertTaskToDto(taskRepository.save(task));
    }

    @Transactional
    public void deleteById(Long taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isPresent()) {
            taskRepository.deleteById(taskId);
        }
        taskMapper.convertTaskToDto(task.get());
        throw new AllException("Задача с " + taskId + " не найдена");
    }
    @Transactional
    public Task applyUsers(Long taskId, Long userId) {
        Task task = taskRepository.findById(taskId).get();
        User user = userRepository.findById(userId).get();
        task.addUser(user);
        userRepository.save(user);
        return taskRepository.save(task);
    }
    public TaskDto getTaskId(Long taskId) {
        return taskRepository.findById(taskId)
                .map(taskMapper::convertTaskToDto).orElse(null);
    }


}
