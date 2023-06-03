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
        User user;

        if (userById.isPresent()) {
            user = userById.get();
        } else {
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
    public User findActiveByLogin(String login) {
        User user;
        Optional<User> byLoginAndStatus = repository.findByLoginAndStatus(login, true);
        if (byLoginAndStatus.isPresent()) {
            user = byLoginAndStatus.get();
        } else {
            throw new RuntimeException("Can't find active user by login - " + login);
        }
        return user;
    }

    @Override
    public List<User> findByRole(Role role) {
        return repository.findByRole(role);
    }

    @Override
    public User findByLogin(String login) {
        User user;
        Optional<User> byLogin = repository.findByLogin(login);
        if (byLogin.isPresent()) {
            user = byLogin.get();
        } else {
            throw new RuntimeException("Can't find user by login - " + login);
        }
        return user;
    }
}
