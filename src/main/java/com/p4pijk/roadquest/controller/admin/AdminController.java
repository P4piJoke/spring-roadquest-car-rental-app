package com.p4pijk.roadquest.controller.admin;

import com.p4pijk.roadquest.entity.car.Car;
import com.p4pijk.roadquest.entity.car.CarType;
import com.p4pijk.roadquest.entity.user.Role;
import com.p4pijk.roadquest.entity.user.User;
import com.p4pijk.roadquest.model.CarModel;
import com.p4pijk.roadquest.model.UserModel;
import com.p4pijk.roadquest.service.impl.CarRQServiceImpl;
import com.p4pijk.roadquest.service.impl.CarTypeRQServiceImpl;
import com.p4pijk.roadquest.service.impl.UserRQServiceImpl;
import com.p4pijk.roadquest.util.RQLiterals;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private static final Role BASIC = new Role(3, "BASIC");
    private static final Role MANAGER = new Role(2, "MANAGER");
    private final CarRQServiceImpl carService;
    private final CarTypeRQServiceImpl carTypeService;
    private final UserRQServiceImpl userService;
    private final PasswordEncoder encoder;

    @GetMapping
    public String init(Model model) {
        model.addAttribute("cars", carService.findAll())
                .addAttribute("users", userService.findByRole(BASIC))
                .addAttribute("managers", userService.findByRole(MANAGER))
                .addAttribute("types",carTypeService.findAll());
        return RQLiterals.ADMIN_PAGE.value();
    }

    @GetMapping("/addCar")
    public String addCar(Model model) {
        Car newCar = new Car();

        model.addAttribute("car", newCar);
        model.addAttribute("types", carTypeService.findAll());
        return RQLiterals.CAR_PAGE.value();
    }

    @GetMapping("/editCar")
    public String editCar(@RequestParam("carId") int id, Model model) {
        model.addAttribute("car", carService.findById(id));
        model.addAttribute("types", carTypeService.findAll());
        return RQLiterals.CAR_PAGE.value();
    }

    @PostMapping("/saveCar")
    public String saveCar(@Valid @ModelAttribute("car") CarModel carModel,
                          BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("types", carTypeService.findAll());
            return RQLiterals.CAR_PAGE.value();
        }
        carService.save(
                Car.builder()
                        .id(carModel.getId())
                        .name(carModel.getName())
                        .carType(carModel.getCarType())
                        .status(true)
                        .build()
        );

        return RQLiterals.REDIRECT_ADMIN.value();
    }

    @GetMapping("/deleteCar")
    public String deleteCar(@RequestParam("carId") int id) {
        carService.deleteById(id);

        return RQLiterals.REDIRECT_ADMIN.value();
    }

    @GetMapping("/changeUser")
    public String changeUserStatus(@RequestParam("userId") int id) {
        User userById = userService.findById(id);
        userById.setStatus(!userById.isStatus());

        userService.save(userById);
        return RQLiterals.REDIRECT_ADMIN.value();
    }

    @GetMapping("/addManager")
    public String addManager(Model model) {
        User manager = new User();

        model.addAttribute("manager", manager);
        return RQLiterals.CREATE_MANAGER_PAGE.value();
    }

    @PostMapping("/saveManager")
    public String saveManager(@Valid @ModelAttribute("manager") UserModel managerModel,
                              BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return RQLiterals.CREATE_MANAGER_PAGE.value();
        }
        userService.save(
                User.builder()
                        .login(managerModel.getLogin())
                        .password(encoder.encode(managerModel.getPassword()))
                        .email(managerModel.getEmail())
                        .firstName(managerModel.getFirstName())
                        .lastName(managerModel.getLastName())
                        .phoneNumber(managerModel.getPhoneNumber())
                        .role(MANAGER)
                        .status(true)
                        .build()
        );

        return RQLiterals.REDIRECT_ADMIN.value();
    }

    @GetMapping("/deleteManager")
    public String deleteManager(@RequestParam("managerId") int id) {
        userService.deleteById(id);

        return RQLiterals.REDIRECT_ADMIN.value();
    }

    @GetMapping("/changePrice")
    public String changeCarTypePrice(@RequestParam("typeId") int id, @RequestParam("price") int price){
        CarType carType = carTypeService.findById(id);
        carTypeService.save(CarType.builder()
                .id(carType.getId())
                .name(carType.getName())
                .price(price).build());
        return RQLiterals.REDIRECT_ADMIN.value();
    }
}
