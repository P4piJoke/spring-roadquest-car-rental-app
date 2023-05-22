package com.p4pijk.roadquest.service.impl;

import com.p4pijk.roadquest.entity.user.Role;
import com.p4pijk.roadquest.entity.user.User;
import com.p4pijk.roadquest.repository.UserRepository;
import com.p4pijk.roadquest.service.UserRQService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRQServiceImpl implements UserRQService {

    private final UserRepository repository;

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

    @Override
    public List<User> findByRole(Role role) {
        return repository.findByRole(role);
    }
}
