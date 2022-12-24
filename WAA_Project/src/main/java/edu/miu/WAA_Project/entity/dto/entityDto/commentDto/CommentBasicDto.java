package edu.miu.WAA_Project.entity.dto.entityDto.commentDto;

import edu.miu.WAA_Project.entity.Faculty;
import edu.miu.WAA_Project.entity.Student;
import lombok.Data;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class CommentBasicDto {

    private String comment;
    private LocalDateTime createdDate;
    private Faculty faculty;
    private Student student;
}
