package com.example.JPointV2.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class TaskDto {
    private Long id;
    private String title;
    private String descriptions;
    private LocalDate creation;
    private LocalDate update;
}
