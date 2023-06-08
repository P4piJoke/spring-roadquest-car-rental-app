package com.p4pijk.roadquest.controller.user;

import com.p4pijk.roadquest.entity.car.Car;
import com.p4pijk.roadquest.entity.car.CarType;
import com.p4pijk.roadquest.entity.user.User;
import com.p4pijk.roadquest.service.ApplicationRQService;
import com.p4pijk.roadquest.service.CarRQService;
import com.p4pijk.roadquest.service.CarTypeRQService;
import com.p4pijk.roadquest.service.impl.SmtpService;
import com.p4pijk.roadquest.service.impl.UserRQServiceImpl;
import com.p4pijk.roadquest.util.RQLiterals;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserRQServiceImpl userService;
    private final CarRQService carService;
    private final ApplicationRQService applicationService;
    private final CarTypeRQService carTypeService;
    private final SmtpService smtpService;
    private List<CarType> selectedFilters;
    private String filteredUrl = "";

    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal UserDetails userDetails,
                          Model model, Authentication auth) {
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        for (final GrantedAuthority grantedAuthority : authorities) {
            String authorityName = grantedAuthority.getAuthority();
            if (authorityName.equals("ADMIN")) {
                return RQLiterals.REDIRECT_ADMIN.value();
            } else if (authorityName.equals("MANAGER")) {
                return RQLiterals.REDIRECT_MANAGER.value();
            }
        }

        initUserCabinet(userDetails, model);
        return RQLiterals.USER_PROFILE.value();
    }

    @GetMapping("/rent")
    public String showRentPage(@RequestParam(required = false)
                               List<CarType> filters,
                               @RequestParam(required = false, defaultValue = "0")
                               int page,
                               @RequestParam(required = false, defaultValue = "10")
                               int size,
                               Model model, HttpServletRequest request) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Car> cars;
        Page<Car> pages;
        List<CarType> carTypes;

        if (filters != null) {
            if (request.getQueryString().matches("(?:filters=\\d+&?)+")) {
                setFilteredUrl(request.getQueryString() + "&");
            }
            setSelectedFilters(filters);
            cars = carService.findAllByCarTypeIn(filters, pageable);
            pages = carService.findAllByCarTypeIn(filters, pageable);
        } else {
            setFilteredUrl("");
            setSelectedFilters(new ArrayList<>());
            cars = carService.findAllActiveCars(pageable);
            pages = carService.findAllActiveCars(pageable);
        }

        model.addAttribute("actualCars", cars);
        model.addAttribute("pages", pages);
        model.addAttribute("filters", carTypeService.findAll());
        model.addAttribute("selectedFilters", getSelectedFilters());
        model.addAttribute("filteredUrl", getFilteredUrl());
        return RQLiterals.RENT_PAGE.value();
    }

    @GetMapping("/about")
    public String showAboutPage(Model model) {
        return RQLiterals.ABOUT_PAGE.value();
    }

    @PostMapping("/sendMail")
    public String sendMail(@RequestParam(required = false) String name,
                           @RequestParam String email,
                           @RequestParam(required = false) String message) {

        smtpService.sendEmail(email,name,message);
        return RQLiterals.REDIRECT_MAIN_PAGE.value();
    }

    private void initUserCabinet(UserDetails userDetails, Model model) {
        String login = userDetails.getUsername();
        User user = userService.findByLogin(login);
        model.addAttribute("currentUser", user);
        model.addAttribute("orders", applicationService.findByCustomer(user));
    }

    public List<CarType> getSelectedFilters() {
        return selectedFilters;
    }

    public void setSelectedFilters(List<CarType> selectedFilters) {
        this.selectedFilters = selectedFilters;
    }

    public String getFilteredUrl() {
        return filteredUrl;
    }

    public void setFilteredUrl(String filteredUrl) {
        this.filteredUrl = filteredUrl;
    }
}
