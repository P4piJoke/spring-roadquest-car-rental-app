package com.p4pijk.roadquest.controller.user;

import com.p4pijk.roadquest.entity.order.Application;
import com.p4pijk.roadquest.entity.order.RentStatus;
import com.p4pijk.roadquest.model.ApplicationModel;
import com.p4pijk.roadquest.service.ApplicationRQService;
import com.p4pijk.roadquest.service.impl.CarRQServiceImpl;
import com.p4pijk.roadquest.service.impl.UserRQServiceImpl;
import com.p4pijk.roadquest.util.RQLiterals;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/rent")
@RequiredArgsConstructor
public class RentController {

    private static final RentStatus NEW = new RentStatus(1, "New");
    private final UserRQServiceImpl userService;
    private final CarRQServiceImpl carService;
    private final ApplicationRQService applicationService;

    @GetMapping("/fillApplication")
    private String fillApplication(@RequestParam("carId") int id,
                                   @AuthenticationPrincipal UserDetails userDetails,
                                   Model model, Authentication auth) {

        String login = userDetails.getUsername();
        model.addAttribute("chosenCar", carService.findById(id))
                .addAttribute("user", userService.findByLogin(login))
                .addAttribute("application", new Application());
        return RQLiterals.FILL_APPLICATION_PAGE.value();
    }

    @PostMapping("/saveApplication")
    private String saveApplication(@Valid @ModelAttribute("application")
                                   ApplicationModel applicationModel,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return RQLiterals.FILL_APPLICATION_PAGE.value();
        }

        applicationService.save(
                Application.builder()
                        .id(applicationModel.getId())
                        .customer(applicationModel.getCustomer())
                        .car(applicationModel.getCar())
                        .startDate(applicationModel.getStartDate())
                        .endDate(applicationModel.getEndDate())
                        .rentStatus(NEW)
                        .price(applicationModel.getCar().getCarType().getPrice()).build()
        );

        return RQLiterals.REDIRECT_USER.value();
    }
}
