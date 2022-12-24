package edu.miu.WAA_Project.repository;


import edu.miu.WAA_Project.entity.JobAdvertisement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobAdvertisementRepo extends CrudRepository<JobAdvertisement,Integer> {
    List<JobAdvertisement> findAllByOwner_Email(String email);
    List<JobAdvertisement> findTop10ByOrderByCreatedDateDesc();
}
