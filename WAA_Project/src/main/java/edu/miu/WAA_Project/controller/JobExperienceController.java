package edu.miu.WAA_Project.controller;


import edu.miu.WAA_Project.entity.dto.entityDto.jobExperienceDto.JobExperienceBasicDto;
import edu.miu.WAA_Project.entity.dto.request.JobExperienceRequestDto;
import edu.miu.WAA_Project.service.impl.JobExperienceServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/jobExperiences")
@RestController
@CrossOrigin
public class JobExperienceController {
    private final JobExperienceServiceImpl jobExperienceService;

    public JobExperienceController(JobExperienceServiceImpl jobExperienceService) {

        this.jobExperienceService = jobExperienceService;
    }

    @GetMapping
    public List<JobExperienceBasicDto> getAll(){

        return jobExperienceService.getAll();
    }
    @GetMapping("/{id}")
    public JobExperienceBasicDto getById(@PathVariable int id){
        return jobExperienceService.getById(id);
    }

    @PostMapping
    public void create(@RequestBody JobExperienceRequestDto request){
        jobExperienceService.save(request);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody JobExperienceRequestDto jobExperienceDto){
        jobExperienceService.update(id,jobExperienceDto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id){
        jobExperienceService.delete(id);
        return "deleted";
    }



}
