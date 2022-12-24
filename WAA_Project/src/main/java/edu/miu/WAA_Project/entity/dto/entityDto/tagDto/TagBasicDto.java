package edu.miu.WAA_Project.entity.dto.entityDto.tagDto;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
public class TagBasicDto {


    private Boolean isDeleted;
    private String tagName;
    private Timestamp createdDate;
}
