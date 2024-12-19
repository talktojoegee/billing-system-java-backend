package com.billing.system.backend.service;

import com.billing.system.backend.dto.LGACreateDTO;
import com.billing.system.backend.dto.LGARecordDTO;
import com.billing.system.backend.entity.LGA;
import com.billing.system.backend.entity.Accounts;
import com.billing.system.backend.entity.Owners;
import com.billing.system.backend.repository.LGARepository;
import com.billing.system.backend.repository.AccountsRepository;
import com.billing.system.backend.repository.OwnersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LGAService {

  private final LGARepository lgaRepository;
  private final AccountsRepository accountsRepository;
  private final OwnersRepository ownersRepository;

  @Autowired
  public LGAService(LGARepository lgaRepository, AccountsRepository accountsRepository, OwnersRepository ownersRepository) {
    this.lgaRepository = lgaRepository;
    this.accountsRepository = accountsRepository;
    this.ownersRepository = ownersRepository;
  }

  // Convert DTO to Entity and save it
  public LGA createLGA( LGACreateDTO lgaCreateDTO) {
    LGA lga = new LGA();
    lga.setLga_name(lgaCreateDTO.getLgaName());
    return lgaRepository.save(lga);
  }

  // Find all LGAs
  public List<LGARecordDTO> getAllLGAs() {
    List<LGA> lgas = lgaRepository.findAll();

    return lgas.stream().map(lga -> LGARecordDTO.dtoBuilder(
      lga.getId(),
      lga.getLga_name()
    )).collect(Collectors.toList());
  }

  // Find LGA by ID
  public Optional<LGA> getLGAById(int id) {
    return lgaRepository.findById(id);
  }

  // Delete an LGA and its related entities
  @Transactional
  public void deleteLGAById(int id) {
    Optional<LGA> lga = lgaRepository.findById(id);
    if (lga.isPresent()) {
      LGA lgaEntity = lga.get();

      // Delete related accounts
      accountsRepository.deleteAll(lgaEntity.getAccounts());

      // Delete related owners
      ownersRepository.deleteAll(lgaEntity.getOwners());

      // Delete LGA itself
      lgaRepository.deleteById(id);
    }
  }

  // Add an account to an LGA
  @Transactional
  public LGA addAccountToLGA(int lgaId, Accounts account) {
    Optional<LGA> lga = lgaRepository.findById(lgaId);
    if (lga.isPresent()) {
      account.setLga(lga.get());
      accountsRepository.save(account);
      return lga.get();
    }
    throw new RuntimeException("LGA not found with ID: " + lgaId);
  }

  // Add an owner to an LGA
 /* @Transactional
  public LGA addOwnerToLGA(int lgaId, Owners owner) {
    Optional<LGA> lga = lgaRepository.findById(lgaId);
    if (lga.isPresent()) {
      owner.setLga(lga.get());
      ownersRepository.save(owner);
      return lga.get();
    }
    throw new RuntimeException("LGA not found with ID: " + lgaId);
  }*/

  // Get all accounts for a specific LGA
  public List<Accounts> getAccountsByLGAId(int lgaId) {
    Optional<LGA> lga = lgaRepository.findById(lgaId);
    if (lga.isPresent()) {
      return lga.get().getAccounts();
    }
    throw new RuntimeException("LGA not found with ID: " + lgaId);
  }

  // Get all owners for a specific LGA
  public List<Owners> getOwnersByLGAId(int lgaId) {
    Optional<LGA> lga = lgaRepository.findById(lgaId);
    if (lga.isPresent()) {
      return lga.get().getOwners();
    }
    throw new RuntimeException("LGA not found with ID: " + lgaId);
  }
}
