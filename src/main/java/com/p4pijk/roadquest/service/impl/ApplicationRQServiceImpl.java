package com.p4pijk.roadquest.service.impl;

import com.p4pijk.roadquest.entity.order.Application;
import com.p4pijk.roadquest.entity.order.RentStatus;
import com.p4pijk.roadquest.entity.user.User;
import com.p4pijk.roadquest.repository.ApplicationRepository;
import com.p4pijk.roadquest.service.ApplicationRQService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public Application save(Application newObj) {
        return repository.save(newObj);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    @Override
    public List<Application> findAllByRentStatus(RentStatus status) {
        return repository.findAllByRentStatus(status);
    }

    @Override
    public Application findByCustomer(User user) {
        Application application;
        Optional<Application> byCustomer = repository.findByCustomer(user);
        if (byCustomer.isPresent()){
            application = byCustomer.get();
        }
        else {
            throw new RuntimeException("Can't find application by user - " + user.getLogin());
        }
        return application;
    }
}
