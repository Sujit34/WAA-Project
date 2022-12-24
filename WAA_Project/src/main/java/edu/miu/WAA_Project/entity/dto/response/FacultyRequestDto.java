package edu.miu.WAA_Project.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacultyRequestDto {
    private String email;
    private String facultyId;
    private String department;
}
