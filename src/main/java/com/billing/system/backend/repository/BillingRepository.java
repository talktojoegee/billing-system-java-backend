package com.billing.system.backend.repository;

import com.billing.system.backend.entity.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BillingRepository extends JpaRepository<Billing, Long> {

  //List<Billing> findByYearAndLga();

  List<Billing> findByYear(int year);

  Optional<List<Billing>> findAllByYear(int year);

  Optional<List<Billing>> findAllByLga_Id(int lga);

  @Query("SELECT b FROM Billing b WHERE b.year = :year AND b.lga.id = :lgaId")
  List<Billing> findAllByYearAndLgaId(@Param("year") int year, @Param("lgaId") int lgaId);


  @Query("SELECT b FROM Billing b WHERE b.paid = :status AND b.objection = :objection")
  List<Billing> findOutstandingBillsByPaymentStatus(@Param("status") int status, @Param("objection") int objection);

  @Query("SELECT b.lga.id AS lgaId, SUM(b.bill_amount) AS totalBillAmount, COUNT(b) AS totalBills " +
    "FROM Billing b WHERE b.year = :year AND b.lga.id = :lgaId " +
    "GROUP BY b.lga.id")
  List<Object[]> findBillSummaryByYearAndLgaId(@Param("year") int year, @Param("lgaId") int lgaId);


  @Query("SELECT SUM(b.bill_amount) FROM Billing b")
  double sumBillAmount();
  @Query("SELECT SUM(b.paid_amount) FROM Billing b")
  double sumPaidAmount();



  @Query("SELECT COUNT(b) FROM Billing b")
  long countAllBills();
  @Query("SELECT COUNT(b) FROM Billing b WHERE b.objection = :flag")
  long countAllObjectionBills(@Param("flag") int flag);

}
