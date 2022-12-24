package edu.miu.WAA_Project.entity.dto.entityDto.jobAdvertisementDto;

import edu.miu.WAA_Project.entity.Address;
import edu.miu.WAA_Project.entity.JobTag;
import edu.miu.WAA_Project.entity.Student;
import edu.miu.WAA_Project.entity.Tag;
import lombok.Data;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class JobAdvertisementBasicDto {

    private int id;
    private LocalDateTime createdDate = LocalDateTime.now();
    private String title;
    private String company;
    private String jobDescription;
    private String contact;
    private String applicationEndDate;
    private String filePath;
    private Address address;
    private Student owner;
    private List<JobTag> tag;
}
