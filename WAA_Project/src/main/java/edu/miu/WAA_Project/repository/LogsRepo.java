package edu.miu.WAA_Project.repository;

import edu.miu.WAA_Project.entity.Logs;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogsRepo extends CrudRepository<Logs,Integer> {
}
