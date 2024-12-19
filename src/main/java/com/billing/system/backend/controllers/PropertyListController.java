package com.billing.system.backend.controllers;

import com.billing.system.backend.dto.PropertyListCreateDTO;
import com.billing.system.backend.dto.PropertyListRecordDTO;
import com.billing.system.backend.service.PropertyListService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/property-list")
public class PropertyListController {
  private final PropertyListService propertyListService;

  @Autowired
  public PropertyListController(PropertyListService propertyListService) {
    this.propertyListService = propertyListService;
  }

  @PostMapping("/new")
  public ResponseEntity<Map<String, String>> createProperty(@RequestBody @Valid PropertyListCreateDTO dto){
    propertyListService.storePropertyList(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "New property added"));
  }


  @GetMapping("/all")
  public ResponseEntity<List<PropertyListRecordDTO>> showAllPropertyList(){
    return ResponseEntity.ok(propertyListService.getAllPropertyList());
  }




}
