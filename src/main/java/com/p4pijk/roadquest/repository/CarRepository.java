package com.p4pijk.roadquest.repository;

import com.p4pijk.roadquest.entity.car.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
}