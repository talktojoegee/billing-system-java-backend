package com.billing.system.backend.repository;

import com.billing.system.backend.entity.PropertyClassification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PropertyClassificationRepository extends JpaRepository<PropertyClassification, Integer> {


  //@Query("SELECT p FROM PropertyClassification p WHERE p.className = :className")
  //Optional<PropertyClassification> findByClassName(@Param("className") String className);

  Optional<PropertyClassification> findByName(String name);
}
