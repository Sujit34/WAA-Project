package edu.miu.WAA_Project.service;

import edu.miu.WAA_Project.entity.dto.request.FacultyResponseDto;
import edu.miu.WAA_Project.entity.dto.response.FacultyRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface FacultyService {
    FacultyResponseDto findByEmail(String email);
    void update(FacultyRequestDto facultyRequestDto);
}
