package com.billing.system.backend.service;

import com.billing.system.backend.dto.PropertyTitleCreateDTO;
import com.billing.system.backend.dto.PropertyTitleRecordDTO;
import com.billing.system.backend.entity.PropertyTitle;
import com.billing.system.backend.repository.PropertyTitleRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PropertyTitleService {

  private final PropertyTitleRepository propertyTitleRepository;

  @Autowired
  public PropertyTitleService(PropertyTitleRepository propertyTitleRepository) {
    this.propertyTitleRepository = propertyTitleRepository;
  }

  // Convert DTO to Entity and save it
  public PropertyTitle createPropertyTitle(@Valid PropertyTitleCreateDTO propertyTitleCreateDTO) {
    PropertyTitle propertyTitle = new PropertyTitle();
    propertyTitle.setTitle(propertyTitleCreateDTO.getTitle());
    return propertyTitleRepository.save(propertyTitle);
  }

  // Get a PropertyTitle by ID
  public Optional<PropertyTitle> getPropertyTitleById(Integer id) {
    return propertyTitleRepository.findById(id);
  }

  // Get all PropertyTitles
  public List<PropertyTitleRecordDTO> getAllPropertyTitles() {
    List<PropertyTitle> propertyTitleList = propertyTitleRepository.findAll();
    return propertyTitleList.stream().map(list->PropertyTitleRecordDTO.titleBuilder(
      list.getTitle(),
      list.getId()
    )).collect(Collectors.toList());
  }

  // Update an existing PropertyTitle
  public PropertyTitle updatePropertyTitle(Integer id, @Valid PropertyTitleCreateDTO propertyTitleCreateDTO) {
    if (!propertyTitleRepository.existsById(id)) {
      return null; // or throw an exception
    }
    PropertyTitle propertyTitle = new PropertyTitle();
    propertyTitle.setId(id);
    propertyTitle.setTitle(propertyTitleCreateDTO.getTitle());
    return propertyTitleRepository.save(propertyTitle);
  }

  // Delete a PropertyTitle by ID
  public boolean deletePropertyTitle(Integer id) {
    if (propertyTitleRepository.existsById(id)) {
      propertyTitleRepository.deleteById(id);
      return true;
    }
    return false;
  }
}
