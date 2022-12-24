package edu.miu.WAA_Project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import edu.miu.WAA_Project.entity.User;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Logs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Boolean isDeleted;
    private LocalDateTime createdDate;

    private String userName;
    @Column(columnDefinition="TEXT")
    private String logMessage;
}
