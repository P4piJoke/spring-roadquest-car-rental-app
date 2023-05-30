package com.p4pijk.roadquest.service;

import com.p4pijk.roadquest.entity.car.Car;

import java.util.List;

public interface CarRQService extends RoadQuestService<Car> {

    List<Car> findAllActiveCars();
}
