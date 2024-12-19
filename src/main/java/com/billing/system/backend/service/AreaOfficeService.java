package com.billing.system.backend.service;

import com.billing.system.backend.dto.AreaOfficeCreateDTO;
import com.billing.system.backend.dto.AreaOfficeRecordDTO;
import com.billing.system.backend.entity.AreaOffice;
import com.billing.system.backend.entity.LGA;
import com.billing.system.backend.repository.AreaOfficeRepository;
import com.billing.system.backend.repository.LGARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AreaOfficeService {

  private final AreaOfficeRepository areaOfficeRepository;
  private final LGARepository lgaRepository;


  @Autowired
  public AreaOfficeService(AreaOfficeRepository areaOfficeRepository, LGARepository lgaRepository) {

    this.areaOfficeRepository = areaOfficeRepository;
    this.lgaRepository = lgaRepository;
  }


  public AreaOffice storeAreaOffice( AreaOfficeCreateDTO createDTO){
    Optional<LGA> lga = lgaRepository.findById(createDTO.getLgaId());
    if(lga.isEmpty()){
      throw new IllegalArgumentException("Invalid LGA ID: " + createDTO.getLgaId());
    }
    AreaOffice areaOffice = AreaOffice.areaBuilder(null,
      createDTO.getAreaOfficeId(),
      createDTO.getAreaName(),
      LocalDateTime.now(),
      LocalDateTime.now(),
      lga.get()
      );
    return areaOfficeRepository.save(areaOffice);

  }


  public List<AreaOfficeRecordDTO> fetchAllAreaOffice(){
    List<AreaOffice> areaOfficeList = areaOfficeRepository.findAll();

    return areaOfficeList.stream().map(area->{
      String lgaName = area.getLga() != null ? area.getLga().getLga_name() : null;

      return AreaOfficeRecordDTO.dtoBuilder(
        area.getAreaOfficeId(),
        area.getAreaName(),
        lgaName
      );
    }).collect(Collectors.toList());
  }



}
