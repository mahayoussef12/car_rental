package com.project.car_rentel.repository;

import com.project.car_rentel.entity.Car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CarRepository extends JpaRepository<Car,Long> {

}
