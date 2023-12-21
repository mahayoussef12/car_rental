package com.project.car_rentel.controller;

import com.project.car_rentel.dto.AuthenticationRequest;
import com.project.car_rentel.dto.AuthenticationResponse;
import com.project.car_rentel.dto.SignupRequest;
import com.project.car_rentel.dto.UserDto;
import com.project.car_rentel.entity.User;
import com.project.car_rentel.repository.UserRepository;
import com.project.car_rentel.service.auth.AuthService;
import com.project.car_rentel.service.jwt.UserService;
import com.project.car_rentel.utils.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private  final JWTUtil jwtUtil;

    private final UserRepository userRepository;
@PostMapping("/signup")
    public ResponseEntity<?> signupCustomer(@RequestBody SignupRequest signupRequest){
    if(authService.hasCustomerWithEmail(signupRequest.getEmail()))
        return  new ResponseEntity<>("Customer already exict with this email",HttpStatus.NOT_ACCEPTABLE);
        UserDto createdCustomerDto= authService.createCustomer(signupRequest);
        if (createdCustomerDto==null)return  new ResponseEntity<>
                ("Customer not created , come again later", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(createdCustomerDto,HttpStatus.CREATED);
    }
 @PostMapping ("/signing")
    public AuthenticationResponse createAuthentication(@RequestBody AuthenticationRequest authenticationRequest) throws
         BadCredentialsException,
         DisabledException,
         UsernameNotFoundException{
    try {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getEmail(),
                authenticationRequest.getPassword()));

    } catch (BadCredentialsException e){
        throw new BadCredentialsException("Incorrect username or password.");

    }
    final UserDetails userDetails= userService.userDetailsService().loadUserByUsername(authenticationRequest.getEmail());
     Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
     final String jwt= jwtUtil.generateToken(userDetails);
     AuthenticationResponse authenticationResponse= new AuthenticationResponse();
     if (optionalUser.isPresent()){
         authenticationResponse.setJwt(jwt);
         authenticationResponse.setUserID(optionalUser.get().getId());
         authenticationResponse.setUserRole(optionalUser.get().getUserRole());
     }
     return  authenticationResponse;
 }
}
