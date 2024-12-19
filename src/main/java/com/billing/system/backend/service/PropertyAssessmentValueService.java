package com.billing.system.backend.service;

import com.billing.system.backend.dto.PropertyAssessmentValueCreateDTO;
import com.billing.system.backend.dto.PropertyAssessmentValueRecordDTO;
import com.billing.system.backend.entity.PropertyAssessmentValue;
import com.billing.system.backend.entity.PropertyClassification;
import com.billing.system.backend.repository.PropertyAssessmentValueRepository;
import com.billing.system.backend.repository.PropertyClassificationRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Validated
public class PropertyAssessmentValueService {


  private final PropertyAssessmentValueRepository propertyAssessmentValueRepository;
  private final PropertyClassificationRepository propertyClassificationRepository;

  @Autowired
  public PropertyAssessmentValueService(PropertyAssessmentValueRepository propertyAssessmentValueRepository,
                                        PropertyClassificationRepository propertyClassificationRepository) {
    this.propertyAssessmentValueRepository = propertyAssessmentValueRepository;
    this.propertyClassificationRepository = propertyClassificationRepository;
  }

  public PropertyAssessmentValue createPropertyAssessmentValue(PropertyAssessmentValueCreateDTO dto) {
    // Fetch the associated PropertyClassification by ID
    Optional<PropertyClassification> propertyClassification = propertyClassificationRepository.findById(dto.getClass_id());

    if (propertyClassification.isEmpty()) {
      throw new IllegalArgumentException("Invalid Property Classification ID: " );
    }

    // Create and save the PropertyAssessmentValue
    PropertyAssessmentValue pav = PropertyAssessmentValue.pavBuild(
      0,
      dto.getPav_code(),
      dto.getOccupancy(),
      dto.getAssessed_amount(),
      dto.getValue_rate(),
      LocalDateTime.now(),
      LocalDateTime.now(),
      propertyClassification.get()
      // propertyLists (default to null)
    );

    return propertyAssessmentValueRepository.save(pav);
  }

  // Create a new PropertyAssessmentValue
 /* public PropertyAssessmentValue createPropertyAssessmentValue(@Valid PropertyAssessmentValueCreateDTO dto) {
    // Retrieve the PropertyClassification based on the provided class_id
    Optional<PropertyClassification> propertyClassification = propertyClassificationRepository.findById(dto.getClass_id());

    if (propertyClassification.isPresent()) {
      // Create a new PropertyAssessmentValue and set the properties from the DTO
      PropertyAssessmentValue pav = new PropertyAssessmentValue();
      pav.setPav_code(dto.getPav_code());
      pav.setDescription(dto.getDescription());
      pav.setAssessed_amount(dto.getAssessed_amount());
      pav.setValue_rate(dto.getValue_rate());

      // Set the PropertyClassification object to establish the one-to-one relationship
      pav.setProperty_class(propertyClassification.get());

      // Save the PropertyAssessmentValue entity and return it
      return propertyAssessmentValueRepository.save(pav);
    } else {
      // Throw an exception if the PropertyClassification with the given ID doesn't exist
      throw new EntityNotFoundException("PropertyClassification with ID " + dto.getClass_id() + " not found.");
    }
  }
  */




  // Get a PropertyAssessmentValue by ID
  public Optional<PropertyAssessmentValue> getPropertyAssessmentValueById(Long id) {
    return propertyAssessmentValueRepository.findById(Math.toIntExact(id));
  }

  // Get all PropertyAssessmentValues
/*  public Iterable<PropertyAssessmentValue> getAllPropertyAssessmentValues() {
    return propertyAssessmentValueRepository.findAll();
  }*/


  public List<PropertyAssessmentValueRecordDTO> getAllPropertyAssessmentValues() {
    // Fetch all PropertyAssessmentValue records
    List<PropertyAssessmentValue> pavList = propertyAssessmentValueRepository.findAll();

    // Map each PropertyAssessmentValue to a PropertyAssessmentValueDTO
    return pavList.stream().map(pav -> {
      String className = pav.getProperty_class() != null ? pav.getProperty_class().getName() : null;

      return PropertyAssessmentValueRecordDTO.dtoBuilder(
        pav.getId(),
        pav.getPav_code(),
        pav.getOccupancy(),
        pav.getAssessed_amount(),
        pav.getValue_rate(),
        className // Include class_name from PropertyClassification
      );

    }).collect(Collectors.toList());
  }

  // Update an existing PropertyAssessmentValue
  public PropertyAssessmentValue updatePropertyAssessmentValue(Long id, @Valid PropertyAssessmentValueCreateDTO dto) {
    if (!propertyAssessmentValueRepository.existsById(Math.toIntExact(id))) {
      return null; // Or throw an exception
    }

    Optional<PropertyClassification> propertyClassification = propertyClassificationRepository.findById(dto.getClass_id());
    if (propertyClassification.isPresent()) {
      PropertyAssessmentValue pav = new PropertyAssessmentValue();
      pav.setId(Math.toIntExact(id));
      pav.setPav_code(dto.getPav_code());
      pav.setOccupancy(dto.getOccupancy());
      pav.setAssessed_amount(dto.getAssessed_amount());
      pav.setValue_rate(dto.getValue_rate());
      pav.setProperty_class(propertyClassification.get());
      return propertyAssessmentValueRepository.save(pav);
    } else {
      return null; // Or throw an exception
    }
  }

  // Delete a PropertyAssessmentValue by ID
  public boolean deletePropertyAssessmentValue(Long id) {
    if (propertyAssessmentValueRepository.existsById(Math.toIntExact(id))) {
      propertyAssessmentValueRepository.deleteById(Math.toIntExact(id));
      return true;
    }
    return false;
  }

}
