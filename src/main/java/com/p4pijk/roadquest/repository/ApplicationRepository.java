package com.p4pijk.roadquest.repository;

import com.p4pijk.roadquest.entity.order.Application;
import com.p4pijk.roadquest.entity.order.RentStatus;
import com.p4pijk.roadquest.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application,Integer> {

    List<Application> findAllByRentStatus(RentStatus status);
    Optional<Application> findByCustomer(User user);
}
