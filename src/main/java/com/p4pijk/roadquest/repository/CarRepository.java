package com.p4pijk.roadquest.repository;

import com.p4pijk.roadquest.entity.car.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Integer> {
}
