package com.billing.system.backend.repository;

import com.billing.system.backend.entity.Gis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GisRepository extends JpaRepository<Gis, Integer> {
}
