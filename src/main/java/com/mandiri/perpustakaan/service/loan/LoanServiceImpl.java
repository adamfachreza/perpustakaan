package com.mandiri.perpustakaan.service.loan;

import com.mandiri.perpustakaan.entity.Book;
import com.mandiri.perpustakaan.entity.Loan;
import com.mandiri.perpustakaan.entity.User;
import com.mandiri.perpustakaan.repository.LoanRepository;
import com.mandiri.perpustakaan.service.book.BookService;
import com.mandiri.perpustakaan.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements LoanService{
  LoanRepository loanRepository;
  UserService userService;
  BookService bookService;
  private final LocalDate now = LocalDate.now();
  @Override
  public Loan getLoanById(String id) {
    return loanRepository.findById(id).get();
  }

  @Override
  public Loan saveLoan(Loan loan) {
    LocalDate endDate = now.plusDays(7);
    loan.setLoanDate(now);
    loan.setEndDate(endDate);
    User user = userService.getUserById(loan.getUser().getId());
    loan.setUser(user);
    Book book = bookService.getBookById(loan.getBook().getId());
    loan.setBook(book);

    // Cek stok buku
    if (book.getStock() < 1) {
      throw new EntityNotFoundException("Book Stock 0");
    }

    // Kurangi stok buku
    book.setStock(book.getStock() - 1);
    bookService.saveBook(book);

    return loanRepository.save(loan);
  }
  @Override
  public Loan returnLoan(Loan loan) {
    Optional<Loan> optionalLoan = loanRepository.findById(loan.getId());
    if(optionalLoan.isPresent()){
      Loan returnedLoan = optionalLoan.get();
      returnedLoan.setReturnDate(now);

      Book book = bookService.getBookById(loan.getBook().getId());
      book.setStock(book.getStock() + 1);
      bookService.saveBook(book);

      return loanRepository.save(returnedLoan);
    } else {
      throw new EntityNotFoundException("Loan with id " + loan.getId() + " not found");
    }
  }

  @Override
  public Page<Loan> searchByReturnDateLoanDateBetweenStartDateAndEndDate(LocalDate returnDate, LocalDate loanDate, LocalDate endDate, Pageable pageable) {
    return loanRepository.searchByReturnDateLoanDateBetweenStartDateAndEndDate(returnDate,loanDate,endDate,pageable);
  }

}
