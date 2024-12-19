package com.billing.system.backend.service;

import com.billing.system.backend.dto.OwnersCreateDTO;
import com.billing.system.backend.dto.OwnersRecordDTO;
import com.billing.system.backend.entity.LGA;
import com.billing.system.backend.entity.Owners;
import com.billing.system.backend.repository.LGARepository;
import com.billing.system.backend.repository.OwnersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Validated
public class OwnerService {

  private final OwnersRepository ownersRepository;
  private final LGARepository lgaRepository;

  @Autowired
  public OwnerService(OwnersRepository ownersRepository, LGARepository lgaRepository) {
    this.ownersRepository = ownersRepository;
    this.lgaRepository = lgaRepository;
  }

  public Owners createOwner(OwnersCreateDTO dto) {
    // Fetch the associated PropertyClassification by ID
    Optional<LGA> lga = lgaRepository.findById(Math.toIntExact(dto.getLga_id()));

    if (lga.isEmpty()) {
      throw new IllegalArgumentException("Invalid LGA ID: " + dto.getLga_id());
    }

    // Create and save the Owners
    Owners owners = Owners.ownersBuild(
      null,
      dto.getKgtin(),
      dto.getName(),
      dto.getTelephone(),
      dto.getEmail(),
      dto.getResAddress(),
      lga.get()
    );
    return ownersRepository.save(owners);
  }

  // Retrieve all Owners
  public List<OwnersRecordDTO> getAllOwners() {
    return ownersRepository.findAll()
      .stream()
      .map(this::mapToDTO)
      .collect(Collectors.toList());
  }

  // Map Owners entity to OwnersRecordDTO
  private OwnersRecordDTO mapToDTO(Owners owner) {
    OwnersRecordDTO dto = new OwnersRecordDTO();
    dto.setId(owner.getId());
    dto.setKgtin(owner.getKgtin());
    dto.setName(owner.getName());
    dto.setTelephone(owner.getTelephone());
    dto.setEmail(owner.getEmail());
    dto.setResAddress(owner.getResAddress());
    dto.setLgaName(owner.getLga().getLga_name()); // Assuming LGA has a 'name' field
    return dto;
  }

  /*public List<OwnersRecordDTO> getAllOwners() {
    List<Owners> owners = ownersRepository.findAll();
    return owners.stream().map(lga -> {
      String lgaName = lga.getClass()  != null ? lga.getClass().getName() : null;

      return OwnersRecordDTO.dtoBuilder(
        lga.getId(),
        pav.getPav_code(),
        pav.getDescription(),
        pav.getAssessed_amount(),
        pav.getValue_rate(),
        lgaName
      );

    }).collect(Collectors.toList());

  }*/

}
