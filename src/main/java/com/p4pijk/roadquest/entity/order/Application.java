package com.p4pijk.roadquest.entity.order;

import com.p4pijk.roadquest.entity.car.Car;
import com.p4pijk.roadquest.entity.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "application")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "customer")
    private User customer;

    @ManyToOne
    @JoinColumn(name = "car")
    private Car car;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "rent_date")
    @CreationTimestamp
    private LocalDateTime rentDate;

    @ManyToOne
    @JoinColumn(name = "rent_status")
    private RentStatus rentStatus;

    @Column(name = "price")
    private int price;

    @Column(name = "descr")
    private String description;
}
