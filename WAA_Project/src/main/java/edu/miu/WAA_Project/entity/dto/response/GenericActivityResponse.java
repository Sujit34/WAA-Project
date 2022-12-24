package edu.miu.WAA_Project.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericActivityResponse {
    public boolean success;
    public String message;
}
