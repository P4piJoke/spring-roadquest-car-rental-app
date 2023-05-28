package com.p4pijk.roadquest.service;

import com.p4pijk.roadquest.entity.user.Role;
import com.p4pijk.roadquest.entity.user.User;

import java.util.List;

public interface UserRQService extends RoadQuestService<User> {

    User findActiveByLogin(String login);

    List<User> findByRole(Role role);
    User findByLogin(String login);
}
