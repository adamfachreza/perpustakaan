package com.mandiri.perpustakaan.service.book;

import com.mandiri.perpustakaan.entity.Book;
import com.mandiri.perpustakaan.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookService {
  List<Book> getAllBook();

  Book getBookById(String id);

  Book saveBook(Book book);

  void delete(String id);

  Book updateBook(Book book);

  Page<Book> searchByTitleAuthorPublisher(
      @Param("title") String title,
      @Param("author") String author,
      @Param("publisher") String publisher,
      Pageable pageable);
  List<Book> findProductMinStock();
  }

