package com.project.car_rentel.service.auth;

import com.project.car_rentel.dto.SignupRequest;
import com.project.car_rentel.dto.UserDto;
import com.project.car_rentel.entity.User;
import com.project.car_rentel.enums.UserRole;
import com.project.car_rentel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @Override
    public UserDto createCustomer(SignupRequest signupRequest) {
     User user = new User();
     user.setName(signupRequest.getName());
     user.setEmail(signupRequest.getEmail());
     user.setPassword(signupRequest.getPassword());
     user.setUserRole(UserRole.CUSTOMER);
     User createdUser = userRepository.save(user);
     UserDto userDto = new UserDto();
     userDto.setId(createdUser.getId());
     return  userDto;
    }

    @Override
    public boolean hasCustomerWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }
}
