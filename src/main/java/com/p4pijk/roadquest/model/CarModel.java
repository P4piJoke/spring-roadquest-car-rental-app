package com.p4pijk.roadquest.model;

import com.p4pijk.roadquest.entity.car.CarType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CarModel {

    private Integer id;
    @NotBlank(message = "Name is required")
    @Size(min = 6, max = 32)
    private String name;

    @NotNull(message = "Type is required")
    private CarType carType;
}
