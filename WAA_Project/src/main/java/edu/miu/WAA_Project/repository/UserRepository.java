package edu.miu.WAA_Project.repository;

import edu.miu.WAA_Project.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    List<User> getUsersByUserType(String userType, Pageable pageable);
    List<User> findAllByOrderByIdDesc();
    List<User> findAllByUserTypeIsNotOrderByIdDesc(String userType);
//    @Query(value = "SELECT u FROM users u WHERE u.isDeleted = false ")
    List<User> findAllByIsDeletedIsFalseAndUserTypeIsNotOrderByIdDesc(String userType);
}
