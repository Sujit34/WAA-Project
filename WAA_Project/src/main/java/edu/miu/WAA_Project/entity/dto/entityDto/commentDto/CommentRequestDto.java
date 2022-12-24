package edu.miu.WAA_Project.entity.dto.entityDto.commentDto;

import lombok.Data;

@Data
public class CommentRequestDto {
    private String comment;
    private String facultyEmail;
    private String studentEmail;
}
