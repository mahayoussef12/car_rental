package com.project.car_rentel.controller;

import com.project.car_rentel.dto.SignupRequest;
import com.project.car_rentel.dto.UserDto;
import com.project.car_rentel.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
@PostMapping("/signup")
    public ResponseEntity<?> signupCustomer(@RequestBody SignupRequest signupRequest){
    if(authService.hasCustomerWithEmail(signupRequest.getEmail()))
        return  new ResponseEntity<>("Customer already exict with this email",HttpStatus.NOT_ACCEPTABLE);
        UserDto createdCustomerDto= authService.createCustomer(signupRequest);
        if (createdCustomerDto==null)return  new ResponseEntity<>
                ("Customer not created , come again later", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(createdCustomerDto,HttpStatus.CREATED);
    }
    @RequestMapping(value="")
    public String hello() {
        return "goodmorning";
    }
}
