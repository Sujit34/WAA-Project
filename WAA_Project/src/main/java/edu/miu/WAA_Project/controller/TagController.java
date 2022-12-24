package edu.miu.WAA_Project.controller;


import edu.miu.WAA_Project.aspect.annotation.Loggerme;
import edu.miu.WAA_Project.entity.dto.entityDto.tagDto.TagBasicDto;
import edu.miu.WAA_Project.service.impl.TagServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/tags")
@RestController
@CrossOrigin
public class TagController {
    private final TagServiceImpl tagService;

    public TagController(TagServiceImpl tagService) {

        this.tagService = tagService;
    }

    @GetMapping
    public List<TagBasicDto> getAll(){

        return tagService.getAll();
    }
    @GetMapping("/{id}")
    public TagBasicDto getById(@PathVariable int id){
        return tagService.getById(id);
    }
    @Loggerme
    @PostMapping
    public void create(@RequestBody TagBasicDto tagDto){
        tagService.save(tagDto);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody TagBasicDto tagDto){
        tagService.update(id,tagDto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id){
        tagService.delete(id);
        return "deleted";
    }



}
