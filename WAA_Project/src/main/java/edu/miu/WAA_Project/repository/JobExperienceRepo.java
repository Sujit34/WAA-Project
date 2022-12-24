package edu.miu.WAA_Project.repository;


import edu.miu.WAA_Project.entity.JobExperience;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobExperienceRepo extends CrudRepository<JobExperience,Integer> {
}
