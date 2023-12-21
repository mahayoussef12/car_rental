package com.project.car_rentel.dto;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private  String email;
    private  String password;
}
