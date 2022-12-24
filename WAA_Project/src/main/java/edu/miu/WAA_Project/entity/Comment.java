package edu.miu.WAA_Project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Boolean isDeleted = false;
    private LocalDateTime createdDate = LocalDateTime.now();
    private String comment;
    @ManyToOne
//    @JsonIgnore
    private Faculty faculty;
    @ManyToOne
//    @JsonIgnore
    private Student student;


}
