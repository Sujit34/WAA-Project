package edu.miu.WAA_Project.repository;

import edu.miu.WAA_Project.entity.PasswordResetToken;
import edu.miu.WAA_Project.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetRepository extends CrudRepository<PasswordResetToken, Integer> {

    PasswordResetToken findByToken(String token);

    PasswordResetToken findByUser(User user);
}
