package com.billing.system.backend.repository;

import com.billing.system.backend.entity.Relief;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReliefRepository extends JpaRepository<Relief, Long> {

}
