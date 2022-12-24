package edu.miu.WAA_Project.entity.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class JobApplicationRequestDto {
    private LocalDateTime appliedTime = LocalDateTime.now();
    private String studentEmail;
    private int jobId;
}
