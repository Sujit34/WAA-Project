package edu.miu.WAA_Project.entity.dto.request;

import edu.miu.WAA_Project.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponseDto {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
    private String studentId;
    private String program;
    private String major;
    private String entryYear;
    private String completionYear;
    private List<JobExperience> jobExperience;
    private List<StudentTag> tag;
    private List<JobApplication> jobApplications;
}
