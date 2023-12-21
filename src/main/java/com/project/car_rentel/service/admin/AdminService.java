package com.project.car_rentel.service.admin;

import com.project.car_rentel.dto.CarDto;

import java.util.List;


public interface AdminService {
    boolean postCar(CarDto carDto);
    List<CarDto> getAllCars();
    void deletCar(Long id);
    CarDto getCarById(Long id );
}
