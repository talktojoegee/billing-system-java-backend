package com.billing.system.backend.controllers;

import com.billing.system.backend.dto.OwnersCreateDTO;
import com.billing.system.backend.dto.OwnersRecordDTO;
import com.billing.system.backend.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/owners")
public class OwnersController {

  private final OwnerService ownerService;

  @Autowired
  public OwnersController(OwnerService ownerService) {
    this.ownerService = ownerService;
  }



  // Create a new PropertyAssessmentValue
  @PostMapping("/new")
  public ResponseEntity<Map<String, String>> createOwner(
    @RequestBody OwnersCreateDTO dto) {
     ownerService.createOwner(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Action successful"));

  }

  // Get all Owners
  @GetMapping("/all")
  public ResponseEntity<List<OwnersRecordDTO>> getAllOwners() {
    List<OwnersRecordDTO> owners = ownerService.getAllOwners();
    return ResponseEntity.ok(owners);
  }

}
