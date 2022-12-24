package edu.miu.WAA_Project.service;

import edu.miu.WAA_Project.entity.dto.entityDto.commentDto.CommentBasicDto;
import edu.miu.WAA_Project.entity.dto.entityDto.commentDto.CommentRequestDto;

import java.util.List;

public interface CommentService {
    CommentBasicDto getById(int id);
    List<CommentBasicDto> getAll();
    List<CommentBasicDto> getAllByStudentId(int id);
    void save(CommentRequestDto commentRequestDto);
    void update(int id, CommentBasicDto commentDto);
    void delete(int id);

}
