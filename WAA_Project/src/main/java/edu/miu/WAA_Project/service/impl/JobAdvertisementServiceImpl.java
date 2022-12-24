package edu.miu.WAA_Project.service.impl;

import edu.miu.WAA_Project.entity.JobAdvertisement;
import edu.miu.WAA_Project.entity.dto.entityDto.jobAdvertisementDto.JobAdvertisementBasicDto;
import edu.miu.WAA_Project.exceptions.ActionPermissionDeniedException;
import edu.miu.WAA_Project.repository.JobAdvertisementRepo;
import edu.miu.WAA_Project.repository.StudentRepository;
import edu.miu.WAA_Project.service.JobAdvertisementService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobAdvertisementServiceImpl implements JobAdvertisementService {
    private final JobAdvertisementRepo jobAdvertisementRepo;
    private final ModelMapper modelMapper;
    private final StudentRepository studentRepository;

    public JobAdvertisementServiceImpl(JobAdvertisementRepo jobAdvertisementRepo, ModelMapper modelMapper,
                                       StudentRepository studentRepository) {
        this.jobAdvertisementRepo = jobAdvertisementRepo;
        this.modelMapper = modelMapper;
        this.studentRepository = studentRepository;
    }

    @Override
    public JobAdvertisementBasicDto getById(int id) {
        JobAdvertisement jobAdvertisement= jobAdvertisementRepo.findById(id).get();
        return modelMapper.map(jobAdvertisement,JobAdvertisementBasicDto.class);
    }

    @Override
    public List<JobAdvertisementBasicDto> getAll() {
        List<JobAdvertisement> jobAdvertisementList = (List<JobAdvertisement>) jobAdvertisementRepo.findAll();
        return jobAdvertisementList
                .stream()
                .map(
                        jobAdvertisement->modelMapper
                                .map(jobAdvertisement,JobAdvertisementBasicDto.class))
                .toList();
    }
    @Override
    public List<JobAdvertisementBasicDto> getLastTenJObs(){
        List<JobAdvertisement> jobAdvertisementList = (List<JobAdvertisement>) jobAdvertisementRepo.findTop10ByOrderByCreatedDateDesc();
        return jobAdvertisementList
                .stream()
                .map(
                        jobAdvertisement->modelMapper
                                .map(jobAdvertisement,JobAdvertisementBasicDto.class))
                .toList();
    }

    @Override
    public List<JobAdvertisementBasicDto> findAllByOwner_Email(String email){
        return jobAdvertisementRepo.findAllByOwner_Email(email)
                .stream()
                .map(item -> modelMapper.map(item, JobAdvertisementBasicDto.class)).toList();
    }

    @Override
    public void save(JobAdvertisementBasicDto jobAdvertisementDto) {
        var student = studentRepository.findByEmail(jobAdvertisementDto.getOwner().getEmail());
        JobAdvertisement jobAdvertisement = modelMapper.map(jobAdvertisementDto,JobAdvertisement.class);
        jobAdvertisement.getOwner().setId(student.getId());
        jobAdvertisementRepo.save(jobAdvertisement);
    }

    @Override
    public void update(int id, JobAdvertisementBasicDto jobAdvertisementDto) {
        JobAdvertisement jobAdvertisement = jobAdvertisementRepo.findById(id).get();
        if (!jobAdvertisement.getOwner().getEmail().equals(jobAdvertisementDto.getOwner().getEmail())){
            throw new ActionPermissionDeniedException("Update permission denied!");
        }
        jobAdvertisement.setId(id);
        jobAdvertisement.setTitle(jobAdvertisementDto.getTitle());
        jobAdvertisement.setCompany(jobAdvertisementDto.getCompany());
        jobAdvertisement.setJobDescription(jobAdvertisementDto.getJobDescription());
        jobAdvertisement.setContact(jobAdvertisementDto.getContact());
        jobAdvertisement.setAddress(jobAdvertisementDto.getAddress());
        jobAdvertisement.setApplicationEndDate(jobAdvertisementDto.getApplicationEndDate());
        jobAdvertisement.setTag(jobAdvertisementDto.getTag());

        jobAdvertisementRepo.save(jobAdvertisement);
    }

    @Override
    public void delete(int id) {
        jobAdvertisementRepo.deleteById(id);
    }
}
