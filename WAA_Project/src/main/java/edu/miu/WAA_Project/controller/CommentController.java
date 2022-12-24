package edu.miu.WAA_Project.controller;
import edu.miu.WAA_Project.entity.dto.entityDto.commentDto.CommentBasicDto;
import edu.miu.WAA_Project.entity.dto.entityDto.commentDto.CommentRequestDto;
import edu.miu.WAA_Project.service.impl.CommentServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/comments")
@RestController
@CrossOrigin
public class CommentController {
    private final CommentServiceImpl commentService;

    public CommentController(CommentServiceImpl commentService) {

        this.commentService = commentService;
    }

    @GetMapping
    public List<CommentBasicDto> getAll(){
        return commentService.getAll();
    }
    @GetMapping("/{id}")
    public CommentBasicDto getById(@PathVariable int id){
        return commentService.getById(id);
    }

    @GetMapping("/filterByStudentId")
    public List<CommentBasicDto> getAllByStudentId(@RequestParam int id){
        return commentService.getAllByStudentId(id);
    }

    @PostMapping
    public void create(@RequestBody CommentRequestDto commentRequestDto){
        commentService.save(commentRequestDto);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody CommentBasicDto commentDto){
        commentService.update(id,commentDto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id){
        commentService.delete(id);
        return "deleted";
    }



}
