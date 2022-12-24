package edu.miu.WAA_Project.controller;

import edu.miu.WAA_Project.entity.User;
import edu.miu.WAA_Project.entity.dto.request.AdminChangePasswordRequest;
import edu.miu.WAA_Project.entity.dto.request.UserRequestDto;
import edu.miu.WAA_Project.entity.dto.response.UserDetailDto;
import edu.miu.WAA_Project.entity.dto.response.UserDto;
import edu.miu.WAA_Project.service.AuthService;
import edu.miu.WAA_Project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/admin-bad")
@RestController
@RequiredArgsConstructor
@CrossOrigin
public class AdminController {
    private final UserService service;
    private final AuthService authService;

    @GetMapping("/users")
    private List<UserDto> getAllUsers(){
        return service.getUsers();
    }

    @GetMapping("/users/{id}")
    public UserDetailDto getUserById(@PathVariable int id) {
        return service.getUserById(id);
    }

    @PutMapping("/users/{id}")
    public void updateUserById(@RequestBody UserRequestDto userDto, @PathVariable int id) {
        service.updateUserById(userDto, id);
    }

    @PutMapping("/users/change-password")
    public void updateUserPasswordById(@RequestBody AdminChangePasswordRequest changePasswordRequest) {
        authService.changeUserPasswordById(changePasswordRequest.getUserId(), changePasswordRequest.getNewPassword());
    }
}
