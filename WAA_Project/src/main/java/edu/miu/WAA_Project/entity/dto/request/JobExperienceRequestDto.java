package edu.miu.WAA_Project.entity.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class JobExperienceRequestDto {
    private String email;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String company;
    private String description;
}
