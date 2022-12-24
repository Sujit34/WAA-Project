package edu.miu.WAA_Project.entity.dto.response;

import edu.miu.WAA_Project.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailDto {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
    //private String phoneNumber;
    private boolean deleted;
    private String userType;
    private boolean isActivated;
}