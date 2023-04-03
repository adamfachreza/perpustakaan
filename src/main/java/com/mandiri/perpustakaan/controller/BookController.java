package com.mandiri.perpustakaan.controller;

import com.mandiri.perpustakaan.entity.Book;
import com.mandiri.perpustakaan.entity.User;
import com.mandiri.perpustakaan.service.book.BookService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "books")
@AllArgsConstructor
public class BookController {
  BookService bookService;

  @GetMapping("/{id}")
  public Book getBook(@PathVariable String id){
    return bookService.getBookById(id);
  }

  @PostMapping
  public Book createBook(@RequestBody Book book) {
    return bookService.saveBook(book);
  }

  @PutMapping("/{id}")
  public void updateBook(@PathVariable String id,@RequestBody Book book){
    book.setId(id);
    Book updateUser = bookService.updateBook(book);
    bookService.saveBook(book);
  }

  @DeleteMapping("/{id}")
  public void deleteBook(@PathVariable String id) {
    bookService.delete(id);
  }
  @GetMapping
  public Page<Book> getBookPerpage(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                   @RequestParam(name = "size", defaultValue = "3") Integer sizePerPage,
                                   @RequestParam(name = "title", defaultValue = "") String title,
                                   @RequestParam(name = "author", defaultValue = "") String author,
                                   @RequestParam(name = "publisher", defaultValue = "") String publisher){
    Pageable pageable = PageRequest.of(page, sizePerPage);
    return bookService.searchByTitleAuthorPublisher(title, author, publisher, pageable);
  }

  @GetMapping("/min-stock")
  public List<Book> getBookMinStock(){
    return bookService.findBookMinStock();
  }
}
