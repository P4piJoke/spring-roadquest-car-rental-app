package com.p4pijk.roadquest.repository;

import com.p4pijk.roadquest.entity.car.Car;
import com.p4pijk.roadquest.entity.car.CarType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    List<Car> findAllByStatus(boolean status);

    Page<Car> findAllByStatus(boolean status, Pageable pageable);

    List<Car> findAllByCarTypeIn(List<CarType> filters);

    Page<Car> findAllByCarTypeIn(List<CarType> filters, Pageable pageable);
}
