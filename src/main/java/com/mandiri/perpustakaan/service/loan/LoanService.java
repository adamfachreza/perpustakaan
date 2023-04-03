package com.mandiri.perpustakaan.service.loan;


import com.mandiri.perpustakaan.entity.Loan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface LoanService {

  Loan getLoanById(String id);
  Loan saveLoan(Loan loan);
  Loan returnLoan(Loan loan);
  Page<Loan> searchByReturnDateLoanDateBetweenStartDateAndEndDate(
      @Param("returnDate") LocalDate returnDate,
      @Param("loanDate") LocalDate loanDate,
      @Param("endDate") LocalDate endDate,
      Pageable pageable);
}
