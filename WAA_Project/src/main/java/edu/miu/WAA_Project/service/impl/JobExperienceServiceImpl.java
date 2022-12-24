package edu.miu.WAA_Project.service.impl;

import edu.miu.WAA_Project.entity.JobExperience;
import edu.miu.WAA_Project.entity.Student;
import edu.miu.WAA_Project.entity.dto.entityDto.jobExperienceDto.JobExperienceBasicDto;
import edu.miu.WAA_Project.entity.dto.request.JobExperienceRequestDto;
import edu.miu.WAA_Project.repository.JobExperienceRepo;
import edu.miu.WAA_Project.repository.StudentRepository;
import edu.miu.WAA_Project.service.JobExperienceService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JobExperienceServiceImpl implements JobExperienceService {
    private final JobExperienceRepo jobExperienceRepo;
    private final ModelMapper modelMapper;
    private final StudentRepository studentRepository;

    public JobExperienceServiceImpl(JobExperienceRepo jobExperienceRepo, ModelMapper modelMapper,
                                    StudentRepository studentRepository) {
        this.jobExperienceRepo = jobExperienceRepo;
        this.modelMapper = modelMapper;
        this.studentRepository = studentRepository;
    }

    @Override
    public JobExperienceBasicDto getById(int id) {
        JobExperience jobExperience= jobExperienceRepo.findById(id).get();
        return modelMapper.map(jobExperience,JobExperienceBasicDto.class);
    }

    @Override
    public List<JobExperienceBasicDto> getAll() {
        List<JobExperience> jobExperienceList = (List<JobExperience>) jobExperienceRepo.findAll();
        return jobExperienceList
                .stream()
                .map(
                        jobExperience->modelMapper
                                .map(jobExperience,JobExperienceBasicDto.class))
                .toList();
    }

    @Override
    public void save(JobExperienceRequestDto jobExperienceRequestDto) {
        Student student = studentRepository.findByEmail(jobExperienceRequestDto.getEmail());
        List<JobExperience> jobExperiences = student.getJobExperience();

        JobExperience newJobExp = new JobExperience();
        newJobExp.setTitle(jobExperienceRequestDto.getTitle());
        newJobExp.setCompany(jobExperienceRequestDto.getCompany());
        newJobExp.setDescription(jobExperienceRequestDto.getDescription());
        newJobExp.setEndDate(jobExperienceRequestDto.getEndDate());
        newJobExp.setStartDate(jobExperienceRequestDto.getStartDate());
        newJobExp.setCreatedDate(LocalDateTime.now());
        JobExperience jobExperience = modelMapper.map(jobExperienceRequestDto,JobExperience.class);

        jobExperiences.add(newJobExp);
        student.setJobExperience(jobExperiences);

        studentRepository.save(student);
    }

    @Override
    public void update(int id, JobExperienceRequestDto jobExperienceRequestDto) {

        JobExperience jobExperience = modelMapper.map(jobExperienceRequestDto,JobExperience.class);

        JobExperience oldJobExperience = jobExperienceRepo.findById(id).get();
        jobExperienceRepo.delete(oldJobExperience);
        jobExperience.setId(id);
        jobExperienceRepo.save(jobExperience);
    }

    @Override
    public void delete(int id) {
        jobExperienceRepo.deleteById(id);
    }
}
