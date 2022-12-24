package edu.miu.WAA_Project.controller;

import edu.miu.WAA_Project.aspect.annotation.Loggerme;
import edu.miu.WAA_Project.entity.User;
import edu.miu.WAA_Project.entity.dto.request.*;
import edu.miu.WAA_Project.entity.dto.response.GenericActivityResponse;
import edu.miu.WAA_Project.entity.dto.response.LoginResponse;
import edu.miu.WAA_Project.entity.dto.response.PasswordResetResponse;
import edu.miu.WAA_Project.exceptions.ErrorException;
import edu.miu.WAA_Project.service.AuthService;
import edu.miu.WAA_Project.service.PasswordResetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/authenticate")
@CrossOrigin
public class AuthController {
    private final AuthService authService;
    private final PasswordResetService passwordResetService;

    @Loggerme
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody RegisterRequest registerRequest) {
        if (registerRequest.getAccountType() == null) {
            throw new ErrorException("Account type value is required.");
        }

        if (registerRequest.getAccountType().equals("faculty")) {
            authService.registerFaculty(registerRequest);
        } else if (registerRequest.getAccountType().equals("student")) {
            authService.registerStudent(registerRequest);
        } else {
            throw new ErrorException("Account type not recognized. Only student / faculty field are accepted");
        }
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/reset-password")
    @ResponseStatus(HttpStatus.OK)
    public PasswordResetResponse resetPassword(@RequestBody PasswordResetRequest passwordResetRequest) {
        return authService.resetPassword(passwordResetRequest.getEmail());
    }

    @PostMapping("/change-password")
    @ResponseStatus(HttpStatus.OK)
    public void changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        String result = passwordResetService.validatePasswordResetToken(changePasswordRequest.getToken());

        if (result == null) {

            Optional user = authService.getUserByPasswordResetToken(changePasswordRequest.getToken());

            if (user.isPresent()) {
                User mUser = (User) user.get();
                authService.changeUserPassword(mUser, changePasswordRequest.getNewPassword());
            }

        }
    }

    @PostMapping("/verify-email")
    public GenericActivityResponse verifyEmail(@RequestBody EmailVerificationRequest emailVerificationRequest) {
        return authService.verifyEmail(emailVerificationRequest);
    }

    @GetMapping("/verify-email")
    public void verifyEmail(@RequestParam String email) {
        authService.resendVerificationToken(email);
    }
}
