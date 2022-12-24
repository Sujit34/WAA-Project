package edu.miu.WAA_Project.entity.dto.response;

import edu.miu.WAA_Project.entity.JobTag;
import edu.miu.WAA_Project.entity.StudentTag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequestDto {
    private String email;
    private String studentId;
    private String program;
    private String major;
    private String entryYear;
    private String completionYear;
    private List<StudentTag> tag;

}
