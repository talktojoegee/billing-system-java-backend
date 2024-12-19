package com.billing.system.backend.controllers;

import com.billing.system.backend.dto.PropertyClassRecordDTO;
import com.billing.system.backend.dto.PropertyClassificationCreateDTO;
import com.billing.system.backend.entity.PropertyClassification;
import com.billing.system.backend.service.PropertyClassificationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/property-classification")
public class PropertyClassificationController {

  private final PropertyClassificationService propertyClassificationService;

  @Autowired
  public PropertyClassificationController(PropertyClassificationService propertyClassificationService) {
    this.propertyClassificationService = propertyClassificationService;
  }

  // Create a new PropertyClassification
  @PostMapping("/new")
  public ResponseEntity<Map<String, String>> createPropertyClassification(@RequestBody @Valid PropertyClassificationCreateDTO propertyClassificationCreateDTO) {
    propertyClassificationService.createPropertyClassification(propertyClassificationCreateDTO);
    return  ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Action Successful"));
  }

  // Get all PropertyClassifications
  @GetMapping("/all")
  public ResponseEntity<List<PropertyClassRecordDTO>> getAllPropertyClassifications() {
    List<PropertyClassRecordDTO> pClass = propertyClassificationService.getAllPropertyClassifications();
    return ResponseEntity.ok(pClass);
  }
  /*public ResponseEntity<Iterable<PropertyClassification>> getAllPropertyClassifications() {
    Iterable<PropertyClassification> propertyClassifications = propertyClassificationService.getAllPropertyClassifications();
    return new ResponseEntity<>(propertyClassifications, HttpStatus.OK);
  }*/

  // Get a specific PropertyClassification by ID
  @GetMapping("/{id}")
  public ResponseEntity<PropertyClassification> getPropertyClassificationById(@PathVariable Integer id) {
    Optional<PropertyClassification> propertyClassification = propertyClassificationService.getPropertyClassificationById(id);
    return propertyClassification.map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  // Update an existing PropertyClassification by ID
  @PutMapping("/{id}")
  public ResponseEntity<PropertyClassification> updatePropertyClassification(@PathVariable Integer id,
                                                                             @RequestBody @Valid PropertyClassificationCreateDTO propertyClassificationCreateDTO) {
    PropertyClassification updatedPropertyClassification = propertyClassificationService.updatePropertyClassification(id, propertyClassificationCreateDTO);
    return updatedPropertyClassification != null ? new ResponseEntity<>(updatedPropertyClassification, HttpStatus.OK)
      : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

  // Delete a PropertyClassification by ID
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePropertyClassification(@PathVariable Integer id) {
    boolean deleted = propertyClassificationService.deletePropertyClassification(id);
    return deleted ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
      : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }
}
