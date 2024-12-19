package com.billing.system.backend.controllers;

import com.billing.system.backend.dto.PropertyAssessmentValueCreateDTO;
import com.billing.system.backend.dto.PropertyAssessmentValueRecordDTO;
import com.billing.system.backend.entity.PropertyAssessmentValue;
import com.billing.system.backend.service.PropertyAssessmentValueService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/property-assessment-value")
public class PropertyAssessmentValueController {

  private final PropertyAssessmentValueService propertyAssessmentValueService;

  @Autowired
  public PropertyAssessmentValueController(PropertyAssessmentValueService propertyAssessmentValueService) {
    this.propertyAssessmentValueService = propertyAssessmentValueService;
  }

  // Create a new PropertyAssessmentValue
  @PostMapping("/new")
  public ResponseEntity<Map<String, String>> createPropertyAssessmentValue(
     @RequestBody @Valid PropertyAssessmentValueCreateDTO dto) {
     propertyAssessmentValueService.createPropertyAssessmentValue(dto);
    //return new ResponseEntity<>(propertyAssessmentValueService.createPropertyAssessmentValue(dto), HttpStatus.CREATED);
    return  ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Action successful"));
  }


  /*public ResponseEntity<PropertyAssessmentValue> createPropertyAssessmentValue(@RequestBody @Valid PropertyAssessmentValueCreateDTO dto) {
    PropertyAssessmentValue createdPav = propertyAssessmentValueService.createPropertyAssessmentValue(dto);
    return createdPav != null ? new ResponseEntity<>(createdPav, HttpStatus.CREATED)
      : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
  }*/

  // Get all PropertyAssessmentValues
  @GetMapping("/all")
  public ResponseEntity<List<PropertyAssessmentValueRecordDTO>> getAllPropertyAssessmentValues() {
    List<PropertyAssessmentValueRecordDTO> pavList = propertyAssessmentValueService.getAllPropertyAssessmentValues();
    return ResponseEntity.ok(pavList);
  }
  /*public ResponseEntity<Iterable<PropertyAssessmentValue>> getAllPropertyAssessmentValues() {
    Iterable<PropertyAssessmentValue> pavList = propertyAssessmentValueService.getAllPropertyAssessmentValues();
    return new ResponseEntity<>(pavList, HttpStatus.OK);
  }*/





  // Get PropertyAssessmentValue by ID
  @GetMapping("/{id}")
  public ResponseEntity<PropertyAssessmentValue> getPropertyAssessmentValueById(@PathVariable Long id) {
    Optional<PropertyAssessmentValue> pav = propertyAssessmentValueService.getPropertyAssessmentValueById(id);
    return pav.map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  // Update an existing PropertyAssessmentValue
  @PutMapping("/{id}")
  public ResponseEntity<PropertyAssessmentValue> updatePropertyAssessmentValue(@PathVariable Long id,
                                                                               @RequestBody @Valid PropertyAssessmentValueCreateDTO dto) {
    PropertyAssessmentValue updatedPav = propertyAssessmentValueService.updatePropertyAssessmentValue(id, dto);
    return updatedPav != null ? new ResponseEntity<>(updatedPav, HttpStatus.OK)
      : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

  // Delete PropertyAssessmentValue by ID
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePropertyAssessmentValue(@PathVariable Long id) {
    boolean deleted = propertyAssessmentValueService.deletePropertyAssessmentValue(id);
    return deleted ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
      : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

}
