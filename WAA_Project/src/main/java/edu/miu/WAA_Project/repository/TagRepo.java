package edu.miu.WAA_Project.repository;


import edu.miu.WAA_Project.entity.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepo extends CrudRepository<Tag,Integer> {
}
