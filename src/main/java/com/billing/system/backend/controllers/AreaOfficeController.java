package com.billing.system.backend.controllers;

import com.billing.system.backend.dto.AreaOfficeCreateDTO;
import com.billing.system.backend.dto.AreaOfficeRecordDTO;
import com.billing.system.backend.service.AreaOfficeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/area-office")
public class AreaOfficeController {


  private final AreaOfficeService areaOfficeService;


  @Autowired
  public AreaOfficeController(AreaOfficeService areaOfficeService) {
    this.areaOfficeService = areaOfficeService;
  }

  @GetMapping("/all")
  public ResponseEntity<List<AreaOfficeRecordDTO>> showAllAreaOffices(){
    return ResponseEntity.ok(areaOfficeService.fetchAllAreaOffice());
  }


  @PostMapping("/new")
  public ResponseEntity<Map<String, String>> createAreaOffice(@RequestBody @Valid AreaOfficeCreateDTO areaOfficeCreateDTO){
    areaOfficeService.storeAreaOffice(areaOfficeCreateDTO);
    return ResponseEntity.status(HttpStatus.CREATED)
      .body(Map.of("message", "New Area Office successfully added to the system."));
  }









}
