package com.example.JPointV2.mapper;
import com.example.JPointV2.dto.TaskDto;
import com.example.JPointV2.model.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public TaskDto convertTaskToDto(Task task) {
        return TaskDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .descriptions(task.getDescriptions())
                .creation(task.getCreation())
                .update(task.getUpdate())
                .build();
    }

    public Task convertDtoToTask(TaskDto taskDto) {

        return Task.builder()
                .id(taskDto.getId())
                .title(taskDto.getTitle())
                .descriptions(taskDto.getDescriptions())
                .creation(taskDto.getCreation())
                .update(taskDto.getUpdate())
                .build();

    }
}
