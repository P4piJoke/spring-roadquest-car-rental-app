package com.p4pijk.roadquest.service;

import com.p4pijk.roadquest.entity.car.Car;
import com.p4pijk.roadquest.entity.car.CarType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CarRQService extends RoadQuestService<Car> {

    List<Car> findAllActiveCars();
    Page<Car> findAllActiveCars(Pageable pageable);
    List<Car> findAllByCarTypeIn(List<CarType> filters);
    Page<Car> findAllByCarTypeIn(List<CarType> filters, Pageable pageable);
}
