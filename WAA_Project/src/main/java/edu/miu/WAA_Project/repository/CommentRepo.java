package edu.miu.WAA_Project.repository;


import edu.miu.WAA_Project.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment,Integer> {
    List<Comment> findAllByStudent_Id(int id);
}
