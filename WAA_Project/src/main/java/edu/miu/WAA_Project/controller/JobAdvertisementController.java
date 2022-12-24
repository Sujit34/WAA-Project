package edu.miu.WAA_Project.controller;


import edu.miu.WAA_Project.entity.dto.entityDto.jobAdvertisementDto.JobAdvertisementBasicDto;
import edu.miu.WAA_Project.service.JobAdvertisementService;
import edu.miu.WAA_Project.service.impl.JobAdvertisementServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/jobAdvertisements")
@RestController
@CrossOrigin
public class JobAdvertisementController {
    private final JobAdvertisementService jobAdvertisementService;

    public JobAdvertisementController(JobAdvertisementServiceImpl jobAdvertisementService) {

        this.jobAdvertisementService = jobAdvertisementService;
    }

    @GetMapping
    public List<JobAdvertisementBasicDto> getAll(){

        return jobAdvertisementService.getAll();
    }
    @GetMapping("/lastTenJobs")
    public List<JobAdvertisementBasicDto> getLastTenJObs(){

        return jobAdvertisementService.getLastTenJObs();
    }
    @GetMapping("/{id}")
    public JobAdvertisementBasicDto getById(@PathVariable int id){
        return jobAdvertisementService.getById(id);
    }

    @GetMapping("/filterByOwnerEmail")
    public List<JobAdvertisementBasicDto> findAllByOwner_Email(@RequestParam String email){
        return jobAdvertisementService.findAllByOwner_Email(email);
    }

    @PostMapping
    public void create(@RequestBody JobAdvertisementBasicDto jobAdvertisementDto){
        
        jobAdvertisementService.save(jobAdvertisementDto);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody JobAdvertisementBasicDto jobAdvertisementDto){
        jobAdvertisementService.update(id,jobAdvertisementDto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id){
        jobAdvertisementService.delete(id);
        return "deleted";
    }



}
