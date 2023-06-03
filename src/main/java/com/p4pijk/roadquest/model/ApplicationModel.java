package com.p4pijk.roadquest.model;


import com.p4pijk.roadquest.entity.car.Car;
import com.p4pijk.roadquest.entity.order.RentStatus;
import com.p4pijk.roadquest.entity.user.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class ApplicationModel {

    private int id;

    private User customer;

    private Car car;

    private LocalDate startDate;

    private LocalDate endDate;


    private LocalDate rentDate;

    private RentStatus rentStatus;

    private int price;

    private String description;
}