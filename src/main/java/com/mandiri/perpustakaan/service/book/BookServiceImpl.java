package com.mandiri.perpustakaan.service.book;

import com.mandiri.perpustakaan.entity.Book;
import com.mandiri.perpustakaan.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService{

  BookRepository bookRepository;

  @Override
  public List<Book> getAllBook() {
    List<Book> list = bookRepository.findAll();
    list.stream().filter(element->element.getStock() > 0);
    return list;
  }

  @Override
  public Book getBookById(String id) {
    return bookRepository.findById(id).get();
  }

  @Override
  public Book saveBook(Book book) {
    return bookRepository.save(book);
  }

  @Override
  public void delete(String id) {
    bookRepository.deleteById(id);
  }

  @Override
  public Book updateBook(Book book) {
    if(bookRepository.findById(book.getId()).isPresent()){

      return saveBook(book);
    }else{
      throw new EntityNotFoundException("Book with id" + book.getId() +"not found");
    }
  }

  @Override
  public Page<Book> searchByTitleAuthorPublisher(String title, String author, String publisher, Pageable pageable) {
    return bookRepository.searchByTitleAuthorPublisher(title,author,publisher,pageable);
  }

}
