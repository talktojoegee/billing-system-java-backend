package com.billing.system.backend.controllers;

import com.billing.system.backend.dto.PropertyTitleCreateDTO;
import com.billing.system.backend.dto.PropertyTitleRecordDTO;
import com.billing.system.backend.entity.PropertyTitle;
import com.billing.system.backend.service.PropertyTitleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/property-title")
public class PropertyTitleController {
  private final PropertyTitleService propertyTitleService;

  @Autowired
  public PropertyTitleController(PropertyTitleService propertyTitleService) {
    this.propertyTitleService = propertyTitleService;
  }

  // Create a new PropertyTitle with DTO
  @PostMapping("/new")
  public ResponseEntity<Map<String, String>> createPropertyTitle(@RequestBody @Valid PropertyTitleCreateDTO propertyTitleCreateDTO) {
    propertyTitleService.createPropertyTitle(propertyTitleCreateDTO);
    return  ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Action Successful"));
  }

  // Get all PropertyTitles
  @GetMapping("/all")
  public ResponseEntity<List<PropertyTitleRecordDTO>> getAllPropertyTitles() {
    ;
    return ResponseEntity.ok(propertyTitleService.getAllPropertyTitles());
  }

  // Get a specific PropertyTitle by ID
  @GetMapping("/{id}")
  public ResponseEntity<PropertyTitle> getPropertyTitleById(@PathVariable Integer id) {
    Optional<PropertyTitle> propertyTitle = propertyTitleService.getPropertyTitleById(id);
    return propertyTitle.map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  // Update an existing PropertyTitle by ID
  @PutMapping("/{id}")
  public ResponseEntity<PropertyTitle> updatePropertyTitle(@PathVariable Integer id,
                                                           @RequestBody @Valid PropertyTitleCreateDTO propertyTitleCreateDTO) {
    PropertyTitle updatedPropertyTitle = propertyTitleService.updatePropertyTitle(id, propertyTitleCreateDTO);
    return updatedPropertyTitle != null ? new ResponseEntity<>(updatedPropertyTitle, HttpStatus.OK)
      : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

  // Delete a PropertyTitle by ID
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePropertyTitle(@PathVariable Integer id) {
    boolean deleted = propertyTitleService.deletePropertyTitle(id);
    return deleted ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
      : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }
}
