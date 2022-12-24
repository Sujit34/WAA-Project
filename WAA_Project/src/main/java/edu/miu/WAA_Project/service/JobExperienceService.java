package edu.miu.WAA_Project.service;

import edu.miu.WAA_Project.entity.dto.entityDto.jobExperienceDto.JobExperienceBasicDto;
import edu.miu.WAA_Project.entity.dto.request.JobExperienceRequestDto;

import java.util.List;

public interface JobExperienceService {
    JobExperienceBasicDto getById(int id);
    List<JobExperienceBasicDto> getAll();
    void save(JobExperienceRequestDto jobExperienceRequestDto);
    void update(int id, JobExperienceRequestDto jobExperienceRequestDto);
    void delete(int id);
}
