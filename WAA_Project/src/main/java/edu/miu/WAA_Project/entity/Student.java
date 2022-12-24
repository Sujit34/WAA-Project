package edu.miu.WAA_Project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@DiscriminatorValue("STUDENT")
public class Student extends User {

    private String studentId;
    private String program;
    private String major;
    private String entryYear;
    private String completionYear;

    @OneToMany(mappedBy = "owner")
    @JsonManagedReference
    private List<JobAdvertisement> jobAdvertisement;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private List<JobExperience> jobExperience;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private List<StudentTag> tag;

    @OneToMany
    @JsonIgnore
    @JoinColumn(name="applicant_id")
    private List<JobApplication> jobApplications;

}
