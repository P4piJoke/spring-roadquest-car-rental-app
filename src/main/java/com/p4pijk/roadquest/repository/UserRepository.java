package com.p4pijk.roadquest.repository;

import com.p4pijk.roadquest.entity.user.Role;
import com.p4pijk.roadquest.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByLogin(String login);

    Optional<User> findByLoginAndStatus(String login, boolean status);

    List<User> findByRole(Role role);
}
