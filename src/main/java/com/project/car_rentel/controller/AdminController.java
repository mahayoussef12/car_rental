package com.project.car_rentel.controller;

import com.project.car_rentel.dto.CarDto;
import com.project.car_rentel.dto.SignupRequest;
import com.project.car_rentel.dto.UserDto;
import com.project.car_rentel.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private AdminService adminService;
    @PostMapping("/car")
    public ResponseEntity<?> postCar(@ModelAttribute CarDto carDto){
        boolean success= adminService.postCar(carDto);

        if (success){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }

    }
    @GetMapping("/cars")
    public ResponseEntity<?> getAllCars(){
        return ResponseEntity.ok(adminService.getAllCars());
    }

    @DeleteMapping("/car/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable Long id){
        adminService.deletCar(id);
        return ResponseEntity.ok(null);

    }
    @GetMapping("/car/{id}")
    public  ResponseEntity<CarDto> getCarById (@PathVariable Long id){
        CarDto carDto = adminService.getCarById(id);
        return ResponseEntity.ok(carDto);
    }
}
