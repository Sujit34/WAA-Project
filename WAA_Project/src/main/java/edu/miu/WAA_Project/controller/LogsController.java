package edu.miu.WAA_Project.controller;


import edu.miu.WAA_Project.entity.dto.entityDto.logsDto.LogsBasicDto;
import edu.miu.WAA_Project.service.impl.LogsServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/logss")
@RestController
@CrossOrigin
public class LogsController {
    private final LogsServiceImpl logsService;

    public LogsController(LogsServiceImpl logsService) {

        this.logsService = logsService;
    }

    @GetMapping
    public List<LogsBasicDto> getAll(){

        return logsService.getAll();
    }
    @GetMapping("/{id}")
    public LogsBasicDto getById(@PathVariable int id){
        return logsService.getById(id);
    }

    @PostMapping
    public void create(@RequestBody LogsBasicDto logsDto){
        //System.out.println("=========================="+logsDto.getLogs());
        logsService.save(logsDto);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody LogsBasicDto logsDto){
        logsService.update(id,logsDto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id){
        logsService.delete(id);
        return "deleted";
    }



}
