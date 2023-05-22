package com.p4pijk.roadquest.controller.admin;

import com.p4pijk.roadquest.entity.car.Car;
import com.p4pijk.roadquest.entity.user.Role;
import com.p4pijk.roadquest.entity.user.User;
import com.p4pijk.roadquest.service.impl.CarRQServiceImpl;
import com.p4pijk.roadquest.service.impl.UserRQServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private static final String REDIRECT_ADMIN = "redirect:/admin/";
    private static final String ADMIN_PAGE = "admin/admin";
    private static final String NEW_CAR_PAGE = "admin/newCar";
    private static final String UPDATE_CAR_PAGE = "admin/updateCar";
    private static final String CREATE_MANAGER_PAGE = "admin/newManager";
    private static final Role BASIC = new Role(3, "BASIC");
    private static final Role MANAGER = new Role(2, "MANAGER");
    private final CarRQServiceImpl carService;
    private final UserRQServiceImpl userService;

    @GetMapping
    public String init(Model model) {
        List<Car> carList = carService.findAll();
        List<User> userList = userService.findByRole(BASIC);
        List<User> managerList = userService.findByRole(MANAGER);

        model.addAttribute("cars", carList)
                .addAttribute("users", userList)
                .addAttribute("managers", managerList);
        return ADMIN_PAGE;
    }

    @GetMapping("/addCar")
    public String addCar(Model model) {
        Car newCar = new Car();

        model.addAttribute("car", newCar);
        return NEW_CAR_PAGE;
    }

    @GetMapping("/editCar")
    public String editCar(@RequestParam("carId") int id, Model model){
        Car editedCar = carService.findById(id);

        model.addAttribute("car",editedCar);
        return UPDATE_CAR_PAGE;
    }

    @PostMapping("/saveCar")
    public String saveCar(@ModelAttribute("car") Car car){
        carService.save(car);

        return REDIRECT_ADMIN;
    }

    @GetMapping("/deleteCar")
    public String deleteCar(@RequestParam("carId") int id){
        carService.deleteById(id);

        return REDIRECT_ADMIN;
    }

    @GetMapping("/changeUser")
    public String changeUserStatus(@ModelAttribute("user") User user){
        user.setStatus(!user.isStatus());

        userService.save(user);
        return REDIRECT_ADMIN;
    }

    @GetMapping("/addManager")
    public String addManager(Model model){
        User manager = new User();

        model.addAttribute("manager",manager);
        return CREATE_MANAGER_PAGE;
    }

    @GetMapping("/deleteManager")
    public String deleteManager(@RequestParam("managerId") int id){
        userService.deleteById(id);
        return REDIRECT_ADMIN;
    }
}
