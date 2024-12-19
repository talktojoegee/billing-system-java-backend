package com.billing.system.backend.controllers;


import com.billing.system.backend.dto.DashboardDTO;
import com.billing.system.backend.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DashboardController {

  @Autowired
  private final DashboardService dashboardService;

  public DashboardController(DashboardService dashboardService) {
    this.dashboardService = dashboardService;
  }


  @GetMapping("/dashboard")
  public ResponseEntity<?> showApplicationDashboard(){
    DashboardDTO dto = dashboardService.fetchDashboardValues();
    if (dto == null) {
      Map<String, String> response = new HashMap<>();
      response.put("message", "No records found.");
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    return ResponseEntity.ok( dto);

  }
}
