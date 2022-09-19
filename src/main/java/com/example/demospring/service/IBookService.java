package com.example.demospring.service;

import com.example.demospring.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IBookService extends IGeneralService<Book> {
    Page<Book> findAllByStatusIsTrueOrderByIdDesc(Pageable pageable);
    Page<Book> findAllWithKey(String key, Pageable pageable);
    Iterable<Book> findBookWithKey(String key);
    Optional<Book> getLastestBook();
    Optional<Book> findBookByName(String name);
    Optional<Book> findBookByCode(String code);
    Iterable<Book> findAllByNameContainingAndStatusIsTrue(String name);
    Iterable<Book> findBookWithKeyBorroed(String key);
    Iterable<Book> findAllBookInFree();
}
