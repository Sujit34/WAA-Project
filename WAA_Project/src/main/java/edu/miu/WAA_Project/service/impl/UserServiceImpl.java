package edu.miu.WAA_Project.service.impl;

import edu.miu.WAA_Project.entity.User;
import edu.miu.WAA_Project.entity.dto.request.UserRequestDto;
import edu.miu.WAA_Project.entity.dto.response.UserDetailDto;
import edu.miu.WAA_Project.entity.dto.response.UserDto;
import edu.miu.WAA_Project.repository.FacultyRepositiry;
import edu.miu.WAA_Project.repository.UserRepository;
import edu.miu.WAA_Project.service.UserService;
import edu.miu.WAA_Project.util.ListMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class UserServiceImpl implements UserService {

    private final ListMapper listMapper;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    private final FacultyRepositiry facultyRepositiry;

    //private final PropertyService propertyService;

    @Override
    public User getUserByEmailId(String id) {
        return userRepository.findByEmail(id);
    }

    @Override
    public ApplicationUserDetail getLoggedInUser() {
        try {
            return ((ApplicationUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<UserDto> getMostRecentUsersByTypeAndSize(String type) {
        Pageable lastTen = PageRequest.of(0, 10, Sort.by("id").descending());
        return listMapper.map(userRepository.getUsersByUserType("CUSTOMER", lastTen), UserDto.class);
    }

    @Override
    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAllByIsDeletedIsFalseAndUserTypeIsNotOrderByIdDesc("ADMIN");
        return listMapper.map(users, UserDto.class);
    }

    @Override
    public UserDetailDto getUserById(int id) {
        return modelMapper.map(userRepository.findById(id), UserDetailDto.class);
    }

    @Override
    public void updateUserById(UserRequestDto userRequestDto, int id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            if (userRequestDto.getDeleted() != null) {
                user.setDeleted(userRequestDto.getDeleted());

                if(userRequestDto.getDeleted()) {
                   // propertyService.convertOwnerPropertiesToUnpublishedWhereNotCompleted(id);
                }
                else {
                    //propertyService.convertOwnerPropertiesToAvailable(id);
                }
            }

            if (userRequestDto.getActivated() != null) {
                user.setActivated(userRequestDto.getActivated());

                if (userRequestDto.getActivated()) {
                    //propertyService.convertOwnerPropertiesToAvailable(id);
                }

            }

            userRepository.save(user);
        }
    }

}
