package com.billing.system.backend.repository;

import com.billing.system.backend.entity.AreaOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaOfficeRepository extends JpaRepository<AreaOffice, Integer> {
}
