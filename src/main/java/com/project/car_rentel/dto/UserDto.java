package com.project.car_rentel.dto;

import com.project.car_rentel.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;

    private UserRole userRole;
}