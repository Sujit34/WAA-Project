package edu.miu.WAA_Project.service;

import edu.miu.WAA_Project.entity.Student;
import edu.miu.WAA_Project.entity.dto.request.StudentResponseDto;
import edu.miu.WAA_Project.entity.dto.response.StudentRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    List<StudentResponseDto> findAllStudents();
    StudentResponseDto findById(int id);
    StudentResponseDto findByEmail(String email);
    void updateStudent(StudentRequestDto studentRequestDto);
}
