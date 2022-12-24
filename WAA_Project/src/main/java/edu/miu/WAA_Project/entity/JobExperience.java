package edu.miu.WAA_Project.entity;

import lombok.Data;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Data
public class JobExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Boolean isDeleted = false;
    private LocalDateTime createdDate;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String company;
    private String description;




}
