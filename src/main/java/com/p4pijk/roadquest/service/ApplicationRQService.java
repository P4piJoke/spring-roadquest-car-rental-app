package com.p4pijk.roadquest.service;

import com.p4pijk.roadquest.entity.order.Application;
import com.p4pijk.roadquest.entity.order.RentStatus;
import com.p4pijk.roadquest.entity.user.User;

import java.util.List;

public interface ApplicationRQService extends RoadQuestService<Application> {
    List<Application> findAllByRentStatus(RentStatus status);

    List<Application> findByCustomer(User user);
}
