package com.billing.system.backend.repository;

import com.billing.system.backend.entity.PropertyList;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PropertyListRepository extends JpaRepository<PropertyList, Long> {
  List<PropertyList> findByLga_Id(@NotNull(message = "Choose LGA") Integer lgaId);

  /*@Query("SELECT p FROM PropertyList p WHERE p. = :year AND b.lga.id = :lgaId")
  List<Billing> findAllByYearAndLgaId(@Param("year") int year, @Param("lgaId") int lgaId);*/


  @Query("SELECT p FROM PropertyList p WHERE p.buildingCode = :code ")
  Optional<PropertyList> findByBuildingCode(@Param("code") String code);

  long countByLgaId(int lgaId);

  @Query("SELECT COUNT(p) FROM PropertyList p")
  long countAllProperties();
}
