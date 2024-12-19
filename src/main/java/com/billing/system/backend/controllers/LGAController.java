package com.billing.system.backend.controllers;


import com.billing.system.backend.dto.LGACreateDTO;
import com.billing.system.backend.dto.LGARecordDTO;
import com.billing.system.backend.entity.LGA;
import com.billing.system.backend.service.LGAService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/lga")
public class LGAController {

  private final LGAService lgaService;

  @Autowired
  public LGAController(LGAService lgaService) {
    this.lgaService = lgaService;
  }

  // Get all LGAs
  @GetMapping("/all")
  public ResponseEntity<List<LGARecordDTO>> getAllLGAs() {
    List<LGARecordDTO> lgas = lgaService.getAllLGAs();
    return new ResponseEntity<>(lgas, HttpStatus.OK);
  }

  // Get a specific LGA by id
  @GetMapping("/{id}")
  public ResponseEntity<LGA> getLGAById(@PathVariable int id) {
    Optional<LGA> lga = lgaService.getLGAById(id);
    return lga.map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  // Create a new LGA
  @PostMapping("/new")
  public ResponseEntity<Map<String, String>> createLGA(@RequestBody @Valid LGACreateDTO lgaCreateDTO) {
    lgaService.createLGA(lgaCreateDTO);
    return  ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Action Successful"));

  }

/*
  // Update an existing LGA
  @PutMapping("/{id}")
  public ResponseEntity<LGA> updateLGA(@PathVariable int id, @RequestBody LGA lga) {
    if (!lgaRepository.existsById(id)) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    lga.setId(id);
    LGA updatedLGA = lgaRepository.save(lga);
    return new ResponseEntity<>(updatedLGA, HttpStatus.OK);
  }

  // Delete an LGA by id
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteLGA(@PathVariable int id) {
    if (!lgaRepository.existsById(id)) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    lgaRepository.deleteById(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }*/
}

