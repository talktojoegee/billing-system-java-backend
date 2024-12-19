package com.billing.system.backend.controllers;

import com.billing.system.backend.dto.ReliefCreateDTO;
import com.billing.system.backend.dto.ReliefRecordDTO;
import com.billing.system.backend.entity.Relief;
import com.billing.system.backend.service.ReliefService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/relief")
public class ReliefController {

  private final ReliefService reliefService;

  @Autowired
  public ReliefController(ReliefService reliefService) {
    this.reliefService = reliefService;
  }

  @PostMapping("/new")
  public ResponseEntity<Map<String, String>> createPropertyAssessmentValue(
    @RequestBody @Valid ReliefCreateDTO dto) {
    Relief relief = reliefService.createRelief(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Action Successful!"));
  }


  @GetMapping("/all")
  public ResponseEntity<List<ReliefRecordDTO>> getAllReliefs() {
    List<ReliefRecordDTO> relief = reliefService.getAllReliefs();
    return ResponseEntity.ok(relief);
  }
}
