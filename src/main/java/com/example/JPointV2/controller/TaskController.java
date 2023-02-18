package com.example.JPointV2.controller;

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
    public ResponseEntity<List<Task>> getAllTask() {
        return new ResponseEntity<>(taskService.getAllTask(), HttpStatus.OK);
    }

    @PostMapping("/create/new/task")
    public ResponseEntity<Task> createNewTask(@RequestBody Task _task) {
        return new ResponseEntity<>(taskService.createNewTask(_task), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{taskId}")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable("taskId") Long _taskId) {
        taskService.deleteById(_taskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping ("/apply/user/{taskId}/{userId}")
    public ResponseEntity<Task> applyUsers(@PathVariable("taskId") Long taskId,
                                           @PathVariable("userId") Long userId) {
        return new ResponseEntity<>(taskService.applyUsers(taskId, userId), HttpStatus.OK);
    }

    @GetMapping("/get/{taskId}")
    public ResponseEntity<Task> getById(@PathVariable("taskId") Long _taskId) {
        return new ResponseEntity<>(taskService.getTaskId(_taskId).get(), HttpStatus.OK);
    }
}
