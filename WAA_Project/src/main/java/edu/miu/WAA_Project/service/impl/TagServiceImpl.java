package edu.miu.WAA_Project.service.impl;

import edu.miu.WAA_Project.entity.Tag;
import edu.miu.WAA_Project.entity.dto.entityDto.tagDto.TagBasicDto;
import edu.miu.WAA_Project.repository.TagRepo;
import edu.miu.WAA_Project.service.TagService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    private final TagRepo tagRepo;
    private final ModelMapper modelMapper;

    public TagServiceImpl(TagRepo tagRepo, ModelMapper modelMapper) {
        this.tagRepo = tagRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public TagBasicDto getById(int id) {
        Tag tag= tagRepo.findById(id).get();
        return modelMapper.map(tag,TagBasicDto.class);
    }

    @Override
    public List<TagBasicDto> getAll() {
        List<Tag> tagList = (List<Tag>) tagRepo.findAll();
        return tagList
                .stream()
                .map(
                        tag->modelMapper
                                .map(tag,TagBasicDto.class))
                .toList();
    }

    @Override
    public void save(TagBasicDto tagDto) {
        Tag tag = modelMapper.map(tagDto,Tag.class);
        tagRepo.save(tag);
    }

    @Override
    public void update(int id, TagBasicDto tagDto) {

        Tag tag = modelMapper.map(tagDto,Tag.class);

        Tag oldTag = tagRepo.findById(id).get();
        tagRepo.delete(oldTag);
        tag.setId(id);
        tagRepo.save(tag);
    }

    @Override
    public void delete(int id) {
        tagRepo.deleteById(id);
    }
}
