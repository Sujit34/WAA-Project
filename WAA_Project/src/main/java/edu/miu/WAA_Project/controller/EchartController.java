package edu.miu.WAA_Project.controller;

import edu.miu.WAA_Project.service.EchartService;
import edu.miu.WAA_Project.service.JobAdvertisementService;
import edu.miu.WAA_Project.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/echarts")
@CrossOrigin
public class EchartController {
    private final EchartService echartService;

    @GetMapping("/student-by-state")
    public List<String> studentByState(){
        return echartService.getStudentByState();
    }
    @GetMapping("/student-by-city")
    public List<String> studentByCity(){
        return echartService.getStudentByCity();
    }
    @GetMapping("/job-by-city")
    public List<String> jobByCity(){
       return echartService.getJobByCity();
    }
    @GetMapping("/job-by-state")
    public List<String> jobByState(){
        //List<String> test = List.of("a");
        return echartService.getJobByState();
    }
    @GetMapping("/job-by-tag")
    public List<String> jobByTag(){
        return echartService.getJobByTags();
    }

}
