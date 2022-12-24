package edu.miu.WAA_Project.entity;

import lombok.Data;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Data
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Boolean isDeleted = Boolean.FALSE;
    private LocalDateTime createdDate = LocalDateTime.now();

}
