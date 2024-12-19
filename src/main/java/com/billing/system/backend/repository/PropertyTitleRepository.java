package com.billing.system.backend.repository;

import com.billing.system.backend.entity.PropertyTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyTitleRepository extends JpaRepository<PropertyTitle, Integer> {
}
