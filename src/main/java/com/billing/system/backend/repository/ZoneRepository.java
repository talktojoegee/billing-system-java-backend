package com.billing.system.backend.repository;

import com.billing.system.backend.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ZoneRepository extends JpaRepository<Zone, Integer> {
  @Query("SELECT z FROM Zone z WHERE z.zone = :zone ")
  Optional<Zone> findByZone(@Param("zone") String zone);;
}
