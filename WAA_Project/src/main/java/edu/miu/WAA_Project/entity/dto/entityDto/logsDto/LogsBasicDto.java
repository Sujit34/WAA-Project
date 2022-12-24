package edu.miu.WAA_Project.entity.dto.entityDto.logsDto;

import edu.miu.WAA_Project.entity.User;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Data
public class LogsBasicDto {



    private Boolean isDeleted;
    private Timestamp createdDate;


    private String user;
    private String logMessage;
}
