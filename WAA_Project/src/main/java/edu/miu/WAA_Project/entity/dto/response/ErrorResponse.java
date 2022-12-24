package edu.miu.WAA_Project.entity.dto.response;

import lombok.Data;

@Data
public class ErrorResponse {
    public ErrorDto error;

    public ErrorResponse(String message) {
        this.error = new ErrorDto(message);
    }
}

