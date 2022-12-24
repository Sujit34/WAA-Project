package edu.miu.WAA_Project.entity.dto.entityDto.jobExperienceDto;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class JobExperienceBasicDto {

//    private Boolean isDeleted;
    private LocalDateTime createdDate;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String company;
    private String description;
}
