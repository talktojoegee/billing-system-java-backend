package com.billing.system.backend.service;

import com.billing.system.backend.dto.PropertyListCreateDTO;
import com.billing.system.backend.dto.PropertyListRecordDTO;
import com.billing.system.backend.entity.*;
import com.billing.system.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PropertyListService {


  private final PropertyClassificationRepository propertyClassificationRepository;
  private final LGARepository lgaRepository;
  private final PropertyListRepository propertyListRepository;
  /*
  private final PropertyTitleRepository propertyTitleRepository;
  private final OwnersRepository ownersRepository;
  private final PropertyAssessmentValueRepository propertyAssessmentValueRepository;
  private final ZoneRepository zoneRepository;*/

  @Autowired
  public PropertyListService(PropertyClassificationRepository propertyClassificationRepository,
                             LGARepository lgaRepository,
                             /*PropertyClassificationRepository propertyClassificationRepository, PropertyTitleRepository propertyTitleRepository,
                             OwnersRepository ownersRepository,
                             PropertyAssessmentValueRepository propertyAssessmentValueRepository,
                             ZoneRepository zoneRepository*/PropertyListRepository propertyListRepository) {
    this.propertyClassificationRepository = propertyClassificationRepository;
    this.lgaRepository = lgaRepository;
    /*this.propertyClassificationRepository = propertyClassificationRepository;
    this.propertyTitleRepository = propertyTitleRepository;
    this.ownersRepository = ownersRepository;
    this.propertyAssessmentValueRepository = propertyAssessmentValueRepository;
    this.zoneRepository = zoneRepository;*/
    this.propertyListRepository = propertyListRepository;
  }


  public PropertyList storePropertyList(PropertyListCreateDTO dto){
    Optional<LGA> lga = lgaRepository.findById(dto.getLga()); //Lga here LGA ID
    Optional<PropertyClassification> propertyClassification = propertyClassificationRepository.findByName(dto.getClassId());
    //Optional<PropertyTitle> title = propertyTitleRepository.findById(dto.getTitle()); //title ID
    //Optional<Owners> owners = ownersRepository.findById(Long.valueOf(dto.getOwner()));// owner ID
    //Optional<PropertyAssessmentValue> propertyAssessmentValue = propertyAssessmentValueRepository.findById(dto.getPavCode());// PAV ID
    //Optional<Zone> zone = zoneRepository.findById(dto.getZoneId());
    if(lga.isEmpty() &&
      propertyClassification.isEmpty()
      /*&&
      title.isEmpty() &&
      owners.isEmpty() &&
      propertyAssessmentValue.isEmpty() &&
      zone.isEmpty()*/
    ){
      throw new IllegalArgumentException("Invalid IDS ");
    }
    PropertyList propertyList = PropertyList.propertyListBuilder(null,
      dto.getBuildingCode(),
      dto.getAddress(),
      dto.getArea(),
      dto.getSize(),
      dto.getImage(),
      dto.getPower(),
      dto.getWater(),
      dto.getBorehole(),
      dto.getStorey(),
      dto.getRefuse(),
      dto.getOwnerName(),
      dto.getOwnerEmail(),
      dto.getOwnerKgtin(),
      dto.getOwnerGsm(),
      dto.getPavCode(),
      dto.getTitle(),
      dto.getZoneName(),
      LocalDateTime.now(),
      LocalDateTime.now(),
      lga.get(),
      propertyClassification.get()
      /*lga.get(),
      title.get(),
      owners.get(),
      propertyAssessmentValue.get(),
      zone.get()*/
      );
    return propertyListRepository.save(propertyList);
  }



  public List<PropertyListRecordDTO> getAllPropertyList(){
    List<PropertyList> list = propertyListRepository.findAll();
    return list.stream().map(ls -> {
      String lgaName = ls.getLga().getLga_name() != null ? ls.getLga().getLga_name() : null;
      //String title = ls.getProperty_title().getTitle() != null ? ls.getProperty_title().getTitle() : null;
      //String owner = ls.getOwners().getName() != null  ? ls.getOwners().getName() : null;
      //String pav = ls.getPav().getPav_code() != null ? ls.getPav().getPav_code() : null;

      return PropertyListRecordDTO.listBuilder(
        ls.getBuildingCode(),
        ls.getAddress(),
        ls.getArea(),
        ls.getSize(),
        ls.getImage(),
        lgaName,
        ls.getTitle(),
        ls.getOwnerName(),
        ls.getPavCode()
      );
    }).collect(Collectors.toList());
  }



}
