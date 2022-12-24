package edu.miu.WAA_Project.repository;

import edu.miu.WAA_Project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo2 extends JpaRepository<User, Integer> {

    List<User> findAllByIsDeletedIsFalseAndUserTypeIsNotOrderByIdDesc(String userType);
}
