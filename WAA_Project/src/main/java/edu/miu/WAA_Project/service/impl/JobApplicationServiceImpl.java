package edu.miu.WAA_Project.service.impl;

import edu.miu.WAA_Project.entity.JobAdvertisement;
import edu.miu.WAA_Project.entity.JobApplication;
import edu.miu.WAA_Project.entity.dto.request.JobApplicationRequestDto;
import edu.miu.WAA_Project.repository.JobAdvertisementRepo;
import edu.miu.WAA_Project.repository.JobApplicationRepo;
import edu.miu.WAA_Project.repository.StudentRepository;
import edu.miu.WAA_Project.service.EmailService;
import edu.miu.WAA_Project.service.JobApplicationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobApplicationServiceImpl implements JobApplicationService {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    private final JobApplicationRepo jobApplicationRepo;

    private final JobAdvertisementRepo jobAdvertisementRepo;

    private final EmailService emailService;

    @Override
    public List<JobApplication> getAll() {
        List<JobApplication> lists= new ArrayList<>();
        jobApplicationRepo.findAll().forEach(lists::add);
        return lists;
    }

    public void save(JobApplicationRequestDto jobApplicationRequestDto){
        var student  = studentRepository.findByEmail(jobApplicationRequestDto.getStudentEmail());
        var jobApplication = modelMapper.map(jobApplicationRequestDto, JobApplication.class);
        jobApplication.setApplicant(student);
        jobApplicationRepo.save(jobApplication);
        Optional<JobAdvertisement> job = jobAdvertisementRepo.findById(jobApplicationRequestDto.getJobId());
        if(job.isPresent()){
            var  userEmail = job.get().getOwner().getEmail();
            var html = student.getFirstName() + " " + student.getLastName() + " applied for your job post";
            emailService.sendWithHTMLBody(userEmail, "Applicant Applied For Job", html);
        }

    }

    public List<JobApplication> getAllByJobId(int id){
        return jobApplicationRepo.findAllByJob_Id(id);
    }


}
