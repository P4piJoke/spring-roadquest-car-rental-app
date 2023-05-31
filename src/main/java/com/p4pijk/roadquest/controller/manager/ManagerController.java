package com.p4pijk.roadquest.controller.manager;

import com.p4pijk.roadquest.entity.order.Application;
import com.p4pijk.roadquest.entity.order.RentStatus;
import com.p4pijk.roadquest.model.ApplicationModel;
import com.p4pijk.roadquest.service.ApplicationRQService;
import com.p4pijk.roadquest.util.RQLiterals;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerController {

    private static final RentStatus ON_HOLD = new RentStatus(2,"On hold");
    private static final RentStatus COMPLETED = new RentStatus(4,"Completed");
    private static final RentStatus REFUNDED = new RentStatus(5,"Refunded");
    private final ApplicationRQService service;

    @GetMapping
    public String init(Model model){
        model.addAttribute("applications",service.findAll());
        return RQLiterals.MANAGER_PAGE.value();
    }

    @GetMapping("/inspectOrder")
    public String inspectOrder(@RequestParam("applicationId") int id, Model model) {
        Application application = service.findById(id);
        model.addAttribute("order", application);
        System.out.println(application.getStartDate());
        System.out.println(application.getEndDate());
        return RQLiterals.INSPECT_ORDER.value();
    }

    @PostMapping("/declineApplication")
    public String declineApplication(@ModelAttribute("application") ApplicationModel applicationModel) {
        int id = applicationModel.getId();
        Application application = service.findById(id);
        application.setDescription(applicationModel.getDescription());
        application.setRentStatus(REFUNDED);
        service.save(application);
        return RQLiterals.REDIRECT_MANAGER.value();
    }

    @GetMapping("/acceptApplication")
    public String acceptApplication(@RequestParam("applicationId") int id){
        Application application = service.findById(id);
        application.setRentStatus(ON_HOLD);
        service.save(application);

        return RQLiterals.REDIRECT_MANAGER.value();
    }
}
