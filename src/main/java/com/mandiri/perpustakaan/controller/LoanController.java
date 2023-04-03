package com.mandiri.perpustakaan.controller;

import com.mandiri.perpustakaan.entity.Book;
import com.mandiri.perpustakaan.entity.Loan;
import com.mandiri.perpustakaan.service.loan.LoanService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(value = "loans")
@AllArgsConstructor
public class LoanController {
  LoanService loanService;
  @GetMapping("/{id}")
  public Loan getLoan(@PathVariable String id){
    return loanService.getLoanById(id);
  }

  @PostMapping
  public Loan createLoan(@RequestBody Loan loan) {
    return loanService.saveLoan(loan);
  }

  @PutMapping("/{id}")
  public Loan returnLoan(@PathVariable String id, @RequestBody Loan loan) {
    loan.setId(id);
    return loanService.returnLoan(loan);
  }

  @GetMapping
  public Page<Loan> getLoanPerpage(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                   @RequestParam(name = "size", defaultValue = "3") Integer sizePerPage,
                                   @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate returnDate,
                                   @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate loanDate,
                                   @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate){
    Pageable pageable = PageRequest.of(page, sizePerPage);
    return loanService.searchByReturnDateLoanDateBetweenStartDateAndEndDate(returnDate, loanDate, endDate, pageable);
  }
}
