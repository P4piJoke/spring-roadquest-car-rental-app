package com.p4pijk.roadquest.service.impl;

import com.p4pijk.roadquest.entity.car.Car;
import com.p4pijk.roadquest.repository.CarRepository;
import com.p4pijk.roadquest.service.CarRQService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarRQServiceImpl implements CarRQService {

    private final CarRepository repository;

    @Override
    public List<Car> findAll() {
        return null;
    }

    @Override
    public Car findById(int id) {
        return null;
    }

    @Override
    public Car save(Car newObj) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }
}
