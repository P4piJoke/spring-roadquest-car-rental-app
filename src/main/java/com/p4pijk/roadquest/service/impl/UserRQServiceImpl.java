package com.p4pijk.roadquest.service.impl;

import com.p4pijk.roadquest.entity.User;
import com.p4pijk.roadquest.repository.UserRepository;
import com.p4pijk.roadquest.service.UserRQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRQServiceImpl implements UserRQService {

    private UserRepository repository;

    @Autowired
    public UserRQServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User findById(int id) {

        Optional<User> userById = repository.findById(id);
        User user = null;

        if (userById.isPresent()){
            user = userById.get();
        }
        else {
            throw new RuntimeException("Can't find user by id - " + id);
        }
        return user;
    }

    @Override
    public User save(User newObj) {
        return repository.save(newObj);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
