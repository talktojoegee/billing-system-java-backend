package com.billing.system.backend.service;

import com.billing.system.backend.dto.PropertyClassRecordDTO;
import com.billing.system.backend.dto.PropertyClassificationCreateDTO;
import com.billing.system.backend.entity.PropertyClassification;
import com.billing.system.backend.repository.PropertyClassificationRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Validated
public class PropertyClassificationService {

  private final PropertyClassificationRepository propertyClassificationRepository;

  @Autowired
  public PropertyClassificationService(PropertyClassificationRepository propertyClassificationRepository) {
    this.propertyClassificationRepository = propertyClassificationRepository;
  }

  // Create a new PropertyClassification
  public PropertyClassification createPropertyClassification(@Valid PropertyClassificationCreateDTO propertyClassificationCreateDTO) {
    PropertyClassification propertyClassification = new PropertyClassification();
    propertyClassification.setName(propertyClassificationCreateDTO.getClassName());
    return propertyClassificationRepository.save(propertyClassification);
  }

  // Get a PropertyClassification by ID
  public Optional<PropertyClassification> getPropertyClassificationById(Integer id) {
    return propertyClassificationRepository.findById(id);
  }

  // Get all PropertyClassifications

  public List<PropertyClassRecordDTO> getAllPropertyClassifications() {
    List<PropertyClassification> pClass = propertyClassificationRepository.findAll();

    // Map each PropertyAssessmentValue to a PropertyAssessmentValueDTO
    return pClass.stream().map(pclass -> {
      String className = pclass.getName() != null ? pclass.getName() : null;
      return PropertyClassRecordDTO.dtoBuilder(
        Long.valueOf(pclass.getId()),
        className
      );
    }).collect(Collectors.toList());
  }
  /*
  public Iterable<PropertyClassification> getAllPropertyClassifications() {
    return propertyClassificationRepository.findAll();
  }*/



  // Update an existing PropertyClassification
  public PropertyClassification updatePropertyClassification(Integer id, @Valid PropertyClassificationCreateDTO propertyClassificationCreateDTO) {
    if (!propertyClassificationRepository.existsById(id)) {
      return null; // or throw an exception
    }
    PropertyClassification propertyClassification = new PropertyClassification();
    propertyClassification.setId(id);
    propertyClassification.setName(propertyClassificationCreateDTO.getClassName());
    return propertyClassificationRepository.save(propertyClassification);
  }

  // Delete a PropertyClassification by ID
  public boolean deletePropertyClassification(Integer id) {
    if (propertyClassificationRepository.existsById(id)) {
      propertyClassificationRepository.deleteById(id);
      return true;
    }
    return false;
  }
}
