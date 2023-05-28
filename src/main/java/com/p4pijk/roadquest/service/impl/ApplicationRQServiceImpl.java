package com.p4pijk.roadquest.service.impl;

import com.p4pijk.roadquest.entity.order.Application;
import com.p4pijk.roadquest.repository.ApplicationRepository;
import com.p4pijk.roadquest.service.ApplicationRQService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicationRQServiceImpl implements ApplicationRQService {

    private final ApplicationRepository repository;

    @Override
    public List<Application> findAll() {
        return repository.findAll();
    }

    @Override
    public Application findById(int id) {
        Application application;
        Optional<Application> byId = repository.findById(id);
        if (byId.isPresent()) {
            application = byId.get();
        } else {
            throw new RuntimeException("Can't find application by id - " + id);
        }
        return application;
    }

    @Override
    public Application save(Application newObj) {
        return repository.save(newObj);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
