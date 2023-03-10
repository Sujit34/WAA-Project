package edu.miu.WAA_Project.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String userType;
    //private String phoneNumber;
    private boolean isEmailVerified;
    private boolean deleted;
    private boolean isActivated;
}