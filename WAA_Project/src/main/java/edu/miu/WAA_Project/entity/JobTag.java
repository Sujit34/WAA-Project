package edu.miu.WAA_Project.entity;

import lombok.Data;
import org.springframework.boot.autoconfigure.batch.BatchProperties;

import javax.persistence.*;

@Data
@Entity
public class JobTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String tag;

}
