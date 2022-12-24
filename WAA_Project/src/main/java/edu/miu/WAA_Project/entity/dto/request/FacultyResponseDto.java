package edu.miu.WAA_Project.entity.dto.request;

import edu.miu.WAA_Project.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacultyResponseDto {
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
    private String facultyId;
    private String department;
}
