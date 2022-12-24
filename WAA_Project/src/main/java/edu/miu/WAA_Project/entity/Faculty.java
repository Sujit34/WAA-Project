package edu.miu.WAA_Project.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Entity
@DiscriminatorValue("FACULTY")
public class Faculty extends User{
    private String facultyId;
    private String department;

//    @OneToMany
//    @JoinColumn(name = "faculty_id")
//    private List<Comment> comments;
}
