package edu.miu.WAA_Project.repository;

import edu.miu.WAA_Project.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
     Student findByEmail(String email);
}
