package com.p4pijk.roadquest.repository;

import com.p4pijk.roadquest.entity.car.CarType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarTypeRepository extends JpaRepository<CarType, Integer> {
}
