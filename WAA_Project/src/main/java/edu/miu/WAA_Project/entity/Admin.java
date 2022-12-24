package edu.miu.WAA_Project.entity;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User {

}
