package edu.miu.WAA_Project.controller;

import edu.miu.WAA_Project.aspect.annotation.Loggerme;
import edu.miu.WAA_Project.entity.dto.request.StudentResponseDto;
import edu.miu.WAA_Project.entity.dto.response.StudentRequestDto;
import edu.miu.WAA_Project.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
@CrossOrigin
public class StudentController {
    private final StudentService studentService;

    @GetMapping()
    public List<StudentResponseDto> getAllStudents() {
        return studentService.findAllStudents();
    }

    @GetMapping("/{id}")
    public StudentResponseDto getById(@PathVariable int id) {
        return studentService.findById(id);
    }

    @PutMapping()
    public void updateStudent(@RequestBody StudentRequestDto studentRequestDto) {
        studentService.updateStudent(studentRequestDto);
    }
    @Loggerme
    @GetMapping("/filterByEmail")
    public StudentResponseDto getByEmail(@RequestParam String email) {
        return studentService.findByEmail(email);
    }

}
