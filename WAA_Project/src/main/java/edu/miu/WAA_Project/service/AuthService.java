package edu.miu.WAA_Project.service;

import edu.miu.WAA_Project.entity.User;
import edu.miu.WAA_Project.entity.dto.request.EmailVerificationRequest;
import edu.miu.WAA_Project.entity.dto.request.LoginRequest;
import edu.miu.WAA_Project.entity.dto.request.RegisterRequest;
import edu.miu.WAA_Project.entity.dto.response.GenericActivityResponse;
import edu.miu.WAA_Project.entity.dto.response.LoginResponse;
import edu.miu.WAA_Project.entity.dto.response.PasswordResetResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface AuthService {
    void registerStudent(RegisterRequest registerRequest);
    void registerFaculty(RegisterRequest registerRequest);
    LoginResponse login(LoginRequest loginRequest);
    PasswordResetResponse resetPassword(String email);

    GenericActivityResponse verifyEmail(EmailVerificationRequest emailVerificationRequest);

    void createPasswordResetTokenForUser(User user, String token);

    Optional<User> getUserByPasswordResetToken(String token);

    void changeUserPassword(User user, String password);
    void changeUserPasswordById(int id, String password);
    void resendVerificationToken(String email);
}
