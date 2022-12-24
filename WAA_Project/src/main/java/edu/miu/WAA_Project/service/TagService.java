package edu.miu.WAA_Project.service;

import edu.miu.WAA_Project.entity.dto.entityDto.tagDto.TagBasicDto;

import java.util.List;

public interface TagService {
    TagBasicDto getById(int id);
    List<TagBasicDto> getAll();
    void save(TagBasicDto tagDto);
    void update(int id, TagBasicDto tagDto);
    void delete(int id);
}
