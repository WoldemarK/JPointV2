package com.example.JPointV2.service;
import com.example.JPointV2.exception.AllException;
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


@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    @Transactional
    public Task createNewTask(@Validated Task _task) {
        return taskRepository.save(_task);
    }

    @Transactional
    public void deleteById(Long taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isPresent()) {
            taskRepository.deleteById(taskId);
        }
        throw new AllException("Задача с " + taskId + " не найдена");
    }

   @Transactional
    public Task applyUsers(Long taskId, Long userId) {
        Task task = taskRepository.findById(taskId).get();
        User user = userRepository.findById(userId).get();

        return taskRepository.save(task);
    }

    public Optional<Task> getTaskId(Long taskId) {
        return taskRepository.findById(taskId);
    }


}
