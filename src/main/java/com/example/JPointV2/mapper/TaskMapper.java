package com.example.JPointV2.mapper;
import com.example.JPointV2.dto.TaskDto;
import com.example.JPointV2.model.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public TaskDto convertTaskToDto(Task task) {
        return TaskDto.builder()
                .id(task.getId())
                .name(task.getTitle())
                .descriptions(task.getDescriptions())
                .companyId(task.getId())
                .creation(task.getCreation())
                .userId(task.getId())
                .update(task.getUpdate())
                .build();

    }

    public Task convertDtoToTask(TaskDto taskDto) {

        return Task.builder()
                .id(taskDto.getId())
                .title(taskDto.getName())
                .descriptions(taskDto.getDescriptions())
                .creation(taskDto.getCreation())
                .update(taskDto.getUpdate())
                .build();

    }
}
