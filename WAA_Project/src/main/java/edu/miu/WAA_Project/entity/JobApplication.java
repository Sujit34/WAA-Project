package edu.miu.WAA_Project.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private LocalDateTime appliedTime;
    @ManyToOne
    private Student applicant;
    @ManyToOne
    private JobAdvertisement job;
}
