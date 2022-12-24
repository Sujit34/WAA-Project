package edu.miu.WAA_Project.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationErrorResponse {
    public ValidationErrorListDto error;

    public ValidationErrorResponse(List<String> errorMessages) {
        error = new ValidationErrorListDto(errorMessages);
    }
}
