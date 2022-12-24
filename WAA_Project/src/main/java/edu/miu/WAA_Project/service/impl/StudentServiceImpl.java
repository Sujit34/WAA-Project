package edu.miu.WAA_Project.service.impl;

import edu.miu.WAA_Project.entity.dto.request.StudentResponseDto;
import edu.miu.WAA_Project.entity.dto.response.StudentRequestDto;
import edu.miu.WAA_Project.repository.StudentRepository;
import edu.miu.WAA_Project.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements  StudentService{
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<StudentResponseDto> findAllStudents() {
        return studentRepository.findAll().
                stream()
                .map(student -> modelMapper.map(student, StudentResponseDto.class))
                .toList();
    }

    @Override
    public StudentResponseDto findById(int id) {
        var student  = studentRepository.findById(id);
        return modelMapper.map(student, StudentResponseDto.class);
    }

    @Override
    public StudentResponseDto findByEmail(String email){
        var student  = studentRepository.findByEmail(email);
        return modelMapper.map(student, StudentResponseDto.class);
    }
    public void updateStudent(StudentRequestDto studentRequestDto){
        var student  = studentRepository.findByEmail(studentRequestDto.getEmail());

        student.setStudentId(studentRequestDto.getStudentId());
        student.setProgram(studentRequestDto.getProgram());
        student.setMajor(studentRequestDto.getMajor());
        student.setEntryYear(studentRequestDto.getEntryYear());
        student.setCompletionYear(studentRequestDto.getCompletionYear());
        student.setTag(studentRequestDto.getTag());


        studentRepository.save(student);
    }
}
