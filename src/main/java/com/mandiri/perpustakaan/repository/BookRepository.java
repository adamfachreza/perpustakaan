package com.mandiri.perpustakaan.repository;

import com.mandiri.perpustakaan.entity.Book;
import com.mandiri.perpustakaan.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
  @Query("SELECT u FROM Book u WHERE " +
      "(:title IS NULL OR u.title LIKE %:title%) AND " +
      "(:author IS NULL OR u.author LIKE %:author%) AND " +
      "(:publisher IS NULL OR u.publisher LIKE %:publisher%)")
  Page<Book> searchByTitleAuthorPublisher(
      @Param("title") String title,
      @Param("author") String author,
      @Param("publisher") String publisher,
      Pageable pageable);

  @Query(value = "SELECT * from mst_book p where p.stock < 5", nativeQuery = true)
  List<Book> findBookMinStock();
}
