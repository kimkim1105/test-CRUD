package com.example.demospring.service;

import com.example.demospring.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IBookService extends IGeneralService<Book> {
    Page<Book> findAllByStatusIsTrueOrderByIdDesc(Pageable pageable);
    Page<Book> findAllWithKeyAndCategory(String key,Long category_id, Pageable pageable);
    Iterable<Book> findBookWithKeyAndCategory(String key,Long category_id);
    Optional<Book> getLastestBook();
    Optional<Book> findBookByName(String name);
    Optional<Book> findBookByCode(String code);
    Iterable<Book> findAllByNameContainingAndStatusIsTrue(String name);
    Iterable<Book> findBookWithKeyBorroed(String key);
    Iterable<Book> findAllBookInFree();
}
