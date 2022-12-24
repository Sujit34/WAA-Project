package edu.miu.WAA_Project.service;

import edu.miu.WAA_Project.entity.User;
import edu.miu.WAA_Project.entity.dto.request.UserRequestDto;
import edu.miu.WAA_Project.entity.dto.response.UserDetailDto;
import edu.miu.WAA_Project.entity.dto.response.UserDto;
import edu.miu.WAA_Project.service.impl.ApplicationUserDetail;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserService {
    User getUserByEmailId(String id);

    ApplicationUserDetail getLoggedInUser();

    User findById(int id);

    List<UserDto> getMostRecentUsersByTypeAndSize(String type);

    List<UserDto> getUsers();

    UserDetailDto getUserById(int id);

    void updateUserById(UserRequestDto userRequestDto, int id);
}
