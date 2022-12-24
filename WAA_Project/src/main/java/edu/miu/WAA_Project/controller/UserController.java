package edu.miu.WAA_Project.controller;

import edu.miu.WAA_Project.entity.User;
import edu.miu.WAA_Project.entity.dto.request.AdminChangePasswordRequest;
import edu.miu.WAA_Project.entity.dto.request.UserRequestDto;
import edu.miu.WAA_Project.entity.dto.response.UserDetailDto;
import edu.miu.WAA_Project.entity.dto.response.UserDto;
import edu.miu.WAA_Project.service.AuthService;
import edu.miu.WAA_Project.service.impl.UserService2;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/admin/users")
@RestController
@RequiredArgsConstructor
@CrossOrigin
public class UserController {
    private final UserService2 userService;
    private final AuthService authService;

    @GetMapping
    public List<UserDto> getAll(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDetailDto getAll(@PathVariable int id){
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public void updateUserById(@RequestBody UserRequestDto userDto, @PathVariable int id) {
        userService.updateUserById(userDto, id);
    }

    @PutMapping("/change-password")
    public void updateUserPasswordById(@RequestBody AdminChangePasswordRequest changePasswordRequest) {
        authService.changeUserPasswordById(changePasswordRequest.getUserId(), changePasswordRequest.getNewPassword());
    }

}
