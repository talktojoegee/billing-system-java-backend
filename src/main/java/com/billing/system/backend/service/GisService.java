package com.billing.system.backend.service;

import com.billing.system.backend.repository.GisRepository;
import com.billing.system.backend.repository.LGARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GisService {

  private final GisRepository gisRepository;
  private final LGARepository lgaRepository;

  @Autowired
  public GisService(GisRepository gisRepository, LGARepository lgaRepository) {
    this.gisRepository = gisRepository;
    this.lgaRepository = lgaRepository;
  }


  //Check Gis table for new records that does not exist on the propety list table
 /* public List<Gis> getFreshRecordsByLgaId(int lgaId){
    lgaId = 12;
    Optional<LGA> lga = lgaRepository.findById(lgaId).orElse(nu)
  }*/

}
