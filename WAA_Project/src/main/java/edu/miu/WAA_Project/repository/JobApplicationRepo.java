package edu.miu.WAA_Project.repository;

import edu.miu.WAA_Project.entity.JobApplication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobApplicationRepo extends CrudRepository<JobApplication,Integer>{
    List<JobApplication> findAllByJob_Id(int id);
}
