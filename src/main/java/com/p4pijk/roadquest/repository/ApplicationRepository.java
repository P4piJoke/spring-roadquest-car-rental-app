package com.p4pijk.roadquest.repository;

import com.p4pijk.roadquest.entity.order.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application,Integer> {
}
