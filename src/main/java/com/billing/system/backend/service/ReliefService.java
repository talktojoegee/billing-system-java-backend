package com.billing.system.backend.service;

import com.billing.system.backend.dto.ReliefCreateDTO;
import com.billing.system.backend.dto.ReliefRecordDTO;
import com.billing.system.backend.entity.Relief;
import com.billing.system.backend.repository.ReliefRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReliefService {


  private final ReliefRepository reliefRepository;

  public ReliefService(ReliefRepository reliefRepository) {
    this.reliefRepository = reliefRepository;
  }


  public Relief createRelief(@Valid ReliefCreateDTO reliefCreateDTO) {
    Relief relief = new Relief();
    relief.setRate(reliefCreateDTO.getRate());
    relief.setDescription(reliefCreateDTO.getDescription());
    relief.setItem(reliefCreateDTO.getItem());
    return reliefRepository.save(relief);
  }


  public List<ReliefRecordDTO> getAllReliefs() {
    List<Relief> reliefList = reliefRepository.findAll();

    return reliefList.stream().map(relief -> ReliefRecordDTO.dtoBuilder(
      relief.getId(),
      relief.getItem(),
      relief.getDescription(),
      relief.getRate()
    )).collect(Collectors.toList());
  }



}
