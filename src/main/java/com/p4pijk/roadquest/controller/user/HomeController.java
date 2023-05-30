package com.p4pijk.roadquest.controller.user;

import com.p4pijk.roadquest.entity.order.RentStatus;
import com.p4pijk.roadquest.service.ApplicationRQService;
import com.p4pijk.roadquest.service.CarRQService;
import com.p4pijk.roadquest.service.impl.UserRQServiceImpl;
import com.p4pijk.roadquest.util.RQLiterals;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private static final RentStatus ON_HOLD = new RentStatus(2,"On hold");
    private static final RentStatus PAID = new RentStatus(3,"Paid");
    private static final RentStatus COMPLETED = new RentStatus(4,"Completed");
    private static final RentStatus REFUNDED = new RentStatus(5,"Refunded");

    private final UserRQServiceImpl userService;
    private final CarRQService carService;
    private final ApplicationRQService applicationService;

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
    public String showRentPage(Model model) {
        model.addAttribute("actualCars",carService.findAllActiveCars());
        return RQLiterals.RENT_PAGE.value();
    }

    @GetMapping("/about")
    public String showAboutPage(Model model) {
        return RQLiterals.ABOUT_PAGE.value();
    }
    private void initUserCabinet(UserDetails userDetails, Model model) {
        String login = userDetails.getUsername();
        model.addAttribute("currentUser", userService.findByLogin(login));
        model.addAttribute("holdOrders",applicationService.findAllByRentStatus(ON_HOLD));
        model.addAttribute("paidOrders",applicationService.findAllByRentStatus(PAID));
        model.addAttribute("completedOrders",applicationService.findAllByRentStatus(COMPLETED));
        model.addAttribute("refundedOrders",applicationService.findAllByRentStatus(REFUNDED));
    }
}
