package edu.miu.WAA_Project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class JobAdvertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Boolean isDeleted = false;
    private LocalDateTime createdDate = LocalDateTime.now();
    private String title;
    private String company;
    private String jobDescription;
    private String contact;
    private String applicationEndDate;
    private String filePath;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Address address;
    @ManyToOne
    @JsonBackReference
    private Student owner;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "job_id")
    private List<JobTag> tag;


}
