package edu.miu.WAA_Project.service.impl;

import edu.miu.WAA_Project.entity.User;
import edu.miu.WAA_Project.entity.dto.request.UserRequestDto;
import edu.miu.WAA_Project.entity.dto.response.UserDetailDto;
import edu.miu.WAA_Project.entity.dto.response.UserDto;
import edu.miu.WAA_Project.repository.UserRepo2;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService2 {
    private final UserRepo2 userRepo;
    private final ModelMapper modelMapper;

    public List<UserDto> getAllUsers(){
        return userRepo.findAllByIsDeletedIsFalseAndUserTypeIsNotOrderByIdDesc("ADMIN")
                .stream()
                .map(u -> modelMapper.map(u, UserDto.class)).toList();
    }

    public UserDetailDto getUserById(int id){
        return modelMapper.map(userRepo.findById(id).get(), UserDetailDto.class);
    }

    public void updateUserById(UserRequestDto userRequestDto, int id) {
        User user = userRepo.findById(id).orElse(null);
        if (user != null) {
            if (userRequestDto.getDeleted() != null) {
                user.setDeleted(userRequestDto.getDeleted());
            }

            if (userRequestDto.getActivated() != null) {
                user.setActivated(userRequestDto.getActivated());

            }

            userRepo.save(user);
        }
    }
}
