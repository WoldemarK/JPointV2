package com.example.JPointV2.controller;

import com.example.JPointV2.dto.TaskDto;
import com.example.JPointV2.model.Task;
import com.example.JPointV2.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/task")
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/get/all")
    public ResponseEntity<List<TaskDto>> getAllTask() {
        return new ResponseEntity<>(taskService.getAllTask(), HttpStatus.OK);
    }

    @PostMapping("/create/new/task")
    public ResponseEntity<TaskDto> createNewTask(@RequestBody TaskDto taskDto) {
        return new ResponseEntity<>(taskService.createNewTask(taskDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{taskId}")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable("taskId") Long _taskId) {
        taskService.deleteById(_taskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/apply/user/{taskId}/{userId}")
    public ResponseEntity<Task> applyUsers(@PathVariable("taskId") Long taskId,
                                           @PathVariable("userId") Long userId) {
        return new ResponseEntity<>(taskService.applyUsers(taskId, userId), HttpStatus.OK);
    }

    @GetMapping("/get/{taskId}")
    public ResponseEntity<TaskDto> getById(@PathVariable("taskId") Long _taskId) {
        return new ResponseEntity<>(taskService.getTaskId(_taskId), HttpStatus.OK);
    }
}
