package edu.miu.WAA_Project.controller;

import edu.miu.WAA_Project.entity.dto.request.FacultyResponseDto;
import edu.miu.WAA_Project.entity.dto.response.FacultyRequestDto;
import edu.miu.WAA_Project.service.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/faculty")
@CrossOrigin
public class FacultyController {
    private final FacultyService service;

    @PutMapping()
    public void updateStudent(@RequestBody FacultyRequestDto facultyRequestDto) {
        service.update(facultyRequestDto);
    }

    @GetMapping("/filterByEmail")
    public FacultyResponseDto getByEmail(@RequestParam String email) {
        return service.findByEmail(email);
    }

}
