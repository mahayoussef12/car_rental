package com.project.car_rentel.service.jwt;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


public interface UserService {

    UserDetailsService userDetailsService();
}
