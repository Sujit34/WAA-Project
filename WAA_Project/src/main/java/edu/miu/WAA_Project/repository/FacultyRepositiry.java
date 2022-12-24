package edu.miu.WAA_Project.repository;

import edu.miu.WAA_Project.entity.Faculty;
import edu.miu.WAA_Project.entity.Student;
import org.springframework.data.repository.CrudRepository;

public interface FacultyRepositiry  extends CrudRepository<Faculty, Integer> {
    Faculty findByEmail(String email);
}
