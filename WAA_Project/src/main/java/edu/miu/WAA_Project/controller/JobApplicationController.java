package edu.miu.WAA_Project.controller;

import edu.miu.WAA_Project.entity.JobApplication;
import edu.miu.WAA_Project.entity.dto.request.JobApplicationRequestDto;
import edu.miu.WAA_Project.repository.JobApplicationRepo;
import edu.miu.WAA_Project.service.JobApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/jobApplications")
@RestController
@CrossOrigin
@RequiredArgsConstructor
public class JobApplicationController {
    private final JobApplicationService jobApplicationService;

    @GetMapping
    public List<JobApplication> getAll(){
        return jobApplicationService.getAll();
    }


    @PostMapping
    public void save(@RequestBody JobApplicationRequestDto jobApplicationRequestDto){
        jobApplicationService.save(jobApplicationRequestDto);
    }

    @GetMapping("/filterByJobId")
    public List<JobApplication> getAllByJobId(@RequestParam int id){
        return jobApplicationService.getAllByJobId(id);
    }


}
