package com.p4pijk.roadquest.controller.user;

import com.p4pijk.roadquest.entity.car.Car;
import com.p4pijk.roadquest.entity.order.Application;
import com.p4pijk.roadquest.entity.order.RentStatus;
import com.p4pijk.roadquest.entity.user.User;
import com.p4pijk.roadquest.service.ApplicationRQService;
import com.p4pijk.roadquest.service.CarRQService;
import com.p4pijk.roadquest.service.impl.CarRQServiceImpl;
import com.p4pijk.roadquest.service.impl.UserRQServiceImpl;
import com.p4pijk.roadquest.util.RQLiterals;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private static final RentStatus PAID = new RentStatus(3,"Paid");

    private static final RentStatus COMPLETED = new RentStatus(4,"Completed");
    private final UserRQServiceImpl userService;
    private final ApplicationRQService applicationService;
    private final CarRQServiceImpl carService;

    @PostMapping("/cashIn")
    public String cashIn(@RequestParam("userId") int id, @RequestParam("cash") int cash){
        User byId = userService.findById(id);
        byId.setBill(byId.getBill() + cash);
        userService.save(byId);

        return RQLiterals.REDIRECT_USER.value();
    }

    @GetMapping("/payApplication")
    public String payApplication(@RequestParam("orderId") int id){
        Application application = applicationService.findById(id);
        User customer = application.getCustomer();
        customer.setBill(customer.getBill() - application.getPrice());
        application.setRentStatus(PAID);

        userService.save(customer);
        applicationService.save(application);

        return RQLiterals.REDIRECT_USER.value();
    }

    @GetMapping("/returnCar")
    public String returnCar(@RequestParam("orderId") int id){
        Application application = applicationService.findById(id);
        Car car = application.getCar();

        application.setRentStatus(COMPLETED);
        car.setStatus(true);

        applicationService.save(application);
        carService.save(car);

        return RQLiterals.REDIRECT_USER.value();
    }
}
