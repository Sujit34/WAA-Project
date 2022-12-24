package edu.miu.WAA_Project.service;

import edu.miu.WAA_Project.entity.JobApplication;
import edu.miu.WAA_Project.entity.dto.request.JobApplicationRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JobApplicationService {
    List<JobApplication> getAll();
    List<JobApplication> getAllByJobId(int id);
    void save(JobApplicationRequestDto jobRequestDto);
}
