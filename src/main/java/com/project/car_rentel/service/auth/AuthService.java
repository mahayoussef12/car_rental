package com.project.car_rentel.service.auth;

import com.project.car_rentel.dto.SignupRequest;
import com.project.car_rentel.dto.UserDto;

public interface AuthService {

    UserDto createCustomer(SignupRequest signupRequest );

    boolean hasCustomerWithEmail(String email);
}
