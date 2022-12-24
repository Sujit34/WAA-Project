package edu.miu.WAA_Project.service;

import edu.miu.WAA_Project.entity.JobAdvertisement;
import edu.miu.WAA_Project.entity.dto.entityDto.jobAdvertisementDto.JobAdvertisementBasicDto;

import java.util.List;

public interface JobAdvertisementService {
    JobAdvertisementBasicDto getById(int id);
    List<JobAdvertisementBasicDto> findAllByOwner_Email(String email);
    List<JobAdvertisementBasicDto> getAll();
    List<JobAdvertisementBasicDto> getLastTenJObs();
    void save(JobAdvertisementBasicDto jobAdvertisementDto);
    void update(int id, JobAdvertisementBasicDto jobAdvertisementDto);
    void delete(int id);



}
