package com.library.controller;

import org.springframework.web.bind.annotation.*;
import com.library.dto.DashboardStatsDTO;
import com.library.service.DashboardService;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public DashboardStatsDTO getDashboardStats() {
        return dashboardService.getDashboardStats();
    }

}//end class DashboardController