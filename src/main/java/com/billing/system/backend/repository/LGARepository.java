package com.billing.system.backend.repository;

import com.billing.system.backend.entity.LGA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LGARepository extends JpaRepository<LGA, Integer> {
  //Optional<LGA> findByLgaName(String lga);

  @Query("SELECT l FROM LGA l WHERE l.lga_name = :lga ")
  Optional<LGA> findByLgaName(@Param("lga") String lga);
  //List<Billing> findAllByYearAndLgaId(@Param("year") int year, @Param("lgaId") int lgaId);
}
