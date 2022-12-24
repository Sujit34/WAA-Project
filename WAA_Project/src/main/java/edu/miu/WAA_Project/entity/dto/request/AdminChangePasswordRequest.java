package edu.miu.WAA_Project.entity.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminChangePasswordRequest {
    private int userId;
    private String newPassword;
}
