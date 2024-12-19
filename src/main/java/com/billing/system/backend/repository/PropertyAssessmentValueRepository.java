package com.billing.system.backend.repository;

import com.billing.system.backend.entity.PropertyAssessmentValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PropertyAssessmentValueRepository extends JpaRepository<PropertyAssessmentValue, Integer> {
  Optional<PropertyAssessmentValue> findById(PropertyAssessmentValue pav);

    //Optional<PropertyAssessmentValue> findByPavCode(String pavCode);

  @Query("SELECT pav FROM PropertyAssessmentValue pav WHERE pav.pav_code = :pavCode")
  Optional<PropertyAssessmentValue> findByPavCode(@Param("pavCode") String pavCode);
}
