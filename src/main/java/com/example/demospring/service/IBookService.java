package com.example.demospring.service;

import com.example.demospring.model.Book;
import com.example.demospring.model.dto.BookDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IBookService extends IGeneralService<Book> {
    Page<Book> findAllByStatusIsTrueOrderByIdDesc(Pageable pageable);
    Page<Book> findAllWithKeyAndCategory(String key,String category_id, Pageable pageable);
    Iterable<Book> findBookWithKeyAndCategory(String key,String category_id);
    Optional<Book> getLastestBook();
    Optional<Book> findBookByName(String name);
    Optional<Book> findBookByCode(String code);
    Iterable<Book> findAllByNameContainingAndStatusIsTrue(String name);
    Iterable<Book> findBookWithKeyBorroed(String key);
    Iterable<Book> findAllBookInFree();
    String addNewBook(BookDTO bookDTO);
    String updateBook(Long id, BookDTO bookDTO);
    String deleteBook(Long id);
}
