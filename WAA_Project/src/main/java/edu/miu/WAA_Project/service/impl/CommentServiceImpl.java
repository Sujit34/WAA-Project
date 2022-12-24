package edu.miu.WAA_Project.service.impl;

import edu.miu.WAA_Project.entity.Comment;
import edu.miu.WAA_Project.entity.dto.entityDto.commentDto.CommentBasicDto;
import edu.miu.WAA_Project.entity.dto.entityDto.commentDto.CommentRequestDto;
import edu.miu.WAA_Project.repository.CommentRepo;
import edu.miu.WAA_Project.repository.FacultyRepositiry;
import edu.miu.WAA_Project.repository.StudentRepository;
import edu.miu.WAA_Project.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepo commentRepo;
    private final ModelMapper modelMapper;
    private final StudentRepository studentRepository;
    private final FacultyRepositiry facultyRepositiry;

    public CommentServiceImpl(CommentRepo commentRepo, ModelMapper modelMapper,
                              StudentRepository studentRepository,
                              FacultyRepositiry facultyRepositiry) {
        this.commentRepo = commentRepo;
        this.modelMapper = modelMapper;
        this.studentRepository = studentRepository;
        this.facultyRepositiry = facultyRepositiry;
    }

    @Override
    public CommentBasicDto getById(int id) {
        Comment comment= commentRepo.findById(id).get();
        return modelMapper.map(comment,CommentBasicDto.class);
    }

    @Override
    public List<CommentBasicDto> getAll() {
        List<Comment> commentList = (List<Comment>) commentRepo.findAll();
        return commentList
                .stream()
                .map(
                        comment->modelMapper
                                .map(comment,CommentBasicDto.class))
                .toList();
    }

    @Override
    public List<CommentBasicDto> getAllByStudentId(int id) {
        List<Comment> commentList = commentRepo.findAllByStudent_Id(id);
        return commentList
                .stream()
                .map(
                        comment->modelMapper
                                .map(comment,CommentBasicDto.class))
                .toList();
    }

    @Override
    public void save(CommentRequestDto commentRequestDto) {
        Comment comment = modelMapper.map(commentRequestDto,Comment.class);
        var student  = studentRepository.findByEmail(commentRequestDto.getStudentEmail());
        var faculty = facultyRepositiry.findByEmail(commentRequestDto.getFacultyEmail());
        comment.getFaculty().setId(faculty.getId());
        comment.getStudent().setId(student.getId());
        commentRepo.save(comment);
    }

    @Override
    public void update(int id, CommentBasicDto commentDto) {

        Comment comment = modelMapper.map(commentDto,Comment.class);

        Comment oldComment = commentRepo.findById(id).get();
        commentRepo.delete(oldComment);
        comment.setId(id);
        commentRepo.save(comment);
    }

    @Override
    public void delete(int id) {
        commentRepo.deleteById(id);
    }
}
