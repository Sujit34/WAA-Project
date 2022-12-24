package edu.miu.WAA_Project.service;

import edu.miu.WAA_Project.entity.PasswordResetToken;
import org.springframework.stereotype.Service;

@Service
public interface PasswordResetService {

    String validatePasswordResetToken(String token);

    PasswordResetToken findByToken(String token);

    boolean isTokenFound(PasswordResetToken passToken);

    boolean isTokenExpired(PasswordResetToken passToken);
}
