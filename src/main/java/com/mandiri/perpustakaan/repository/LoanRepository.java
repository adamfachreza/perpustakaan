package com.mandiri.perpustakaan.repository;

import com.mandiri.perpustakaan.entity.Loan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface LoanRepository extends JpaRepository<Loan, String> {

  @Query("SELECT u FROM Loan u WHERE " +
      "(:returnDate IS NULL) AND " +
      "(:loanDate IS NULL OR u.loanDate BETWEEN :loanDate AND :endDate)")
  Page<Loan> searchByReturnDateLoanDateBetweenStartDateAndEndDate(
      @Param("returnDate") LocalDate returnDate,
      @Param("loanDate") LocalDate loanDate,
      @Param("endDate") LocalDate endDate,
      Pageable pageable);
}
