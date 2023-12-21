package com.project.car_rentel.repository;

import com.project.car_rentel.entity.User;
import com.project.car_rentel.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository< User,Long> {
    Optional<User> findFirstByEmail(String email);


    User findFirstByuserRole(UserRole userRole);
}
