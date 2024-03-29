package com.p4pijk.roadquest.service.impl;

import com.p4pijk.roadquest.entity.car.Car;
import com.p4pijk.roadquest.entity.car.CarType;
import com.p4pijk.roadquest.repository.CarRepository;
import com.p4pijk.roadquest.service.CarRQService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarRQServiceImpl implements CarRQService {

    private final CarRepository repository;

    @Override
    public List<Car> findAll() {
        return repository.findAll();
    }

    @Override
    public Car findById(int id) {
        Optional<Car> byId = repository.findById(id);
        Car car;
        if (byId.isPresent()) {
            car = byId.get();
        } else {
            throw new RuntimeException("Can't find car by id - " + id);
        }
        return car;
    }

    @Override
    public Car save(Car newObj) {
        return repository.save(newObj);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    @Override
    public List<Car> findAllActiveCars() {
        return repository.findAllByStatus(true);
    }

    @Override
    public Page<Car> findAllActiveCars(Pageable pageable) {
        return repository.findAllByStatus(true, pageable);
    }

    @Override
    public List<Car> findAllByCarTypeIn(List<CarType> filters) {
        return repository.findAllByCarTypeIn(filters);
    }

    @Override
    public Page<Car> findAllByCarTypeIn(List<CarType> filters, Pageable pageable) {
        return repository.findAllByCarTypeIn(filters, pageable);
    }
}
