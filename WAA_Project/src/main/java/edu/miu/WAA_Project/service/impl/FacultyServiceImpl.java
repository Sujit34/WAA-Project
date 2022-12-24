package edu.miu.WAA_Project.service.impl;

import edu.miu.WAA_Project.entity.dto.request.FacultyResponseDto;
import edu.miu.WAA_Project.entity.dto.response.FacultyRequestDto;
import edu.miu.WAA_Project.repository.FacultyRepositiry;
import edu.miu.WAA_Project.service.FacultyService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FacultyServiceImpl implements FacultyService{
    private final FacultyRepositiry repo;
    private final ModelMapper modelMapper;

    @Override
    public FacultyResponseDto findByEmail(String email){
        var faculty  = repo.findByEmail(email);
        return modelMapper.map(faculty, FacultyResponseDto.class);
    }
    public void update(FacultyRequestDto facultyRequestDto){
        var faculty  = repo.findByEmail(facultyRequestDto.getEmail());

        faculty.setFacultyId(facultyRequestDto.getFacultyId());
        faculty.setDepartment(facultyRequestDto.getDepartment());

        repo.save(faculty);
    }
}
