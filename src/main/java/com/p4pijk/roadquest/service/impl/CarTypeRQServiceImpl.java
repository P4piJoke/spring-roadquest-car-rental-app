package com.p4pijk.roadquest.service.impl;

import com.p4pijk.roadquest.entity.car.CarType;
import com.p4pijk.roadquest.repository.CarTypeRepository;
import com.p4pijk.roadquest.service.CarTypeRQService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarTypeRQServiceImpl implements CarTypeRQService {

    private final CarTypeRepository repository;

    @Override
    public List<CarType> findAll() {
        return repository.findAll();
    }

    @Override
    public CarType findById(int id) {
        Optional<CarType> typeById = repository.findById(id);
        CarType type;
        if (typeById.isPresent()) {
            type = typeById.get();
        } else {
            throw new RuntimeException("Can't find car type by id - " + id);
        }
        return type;
    }

    @Override
    public CarType save(CarType newObj) {
        return repository.save(newObj);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
