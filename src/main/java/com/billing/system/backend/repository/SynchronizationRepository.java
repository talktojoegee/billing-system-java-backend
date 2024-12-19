package com.billing.system.backend.repository;

import com.billing.system.backend.dto.SyncLogDTO;
import com.billing.system.backend.entity.SynchronizationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SynchronizationRepository extends JpaRepository<SynchronizationLog, Integer> {


  @Query("SELECT new com.billing.system.backend.dto.SyncLogDTO(l.lga_name, s.gGis, s.kLabs, s.lastSync) " +
    "FROM  SynchronizationLog s " +
    "JOIN LGA l ON s.lga_id = l.id")
  List<SyncLogDTO> fetchSynchronizationLogs();

}
