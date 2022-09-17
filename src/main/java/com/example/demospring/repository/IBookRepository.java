package com.example.demospring.repository;

import com.example.demospring.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBookRepository extends JpaRepository<Book,Long> {
    Iterable<Book> findAllByStatusIsTrueOrderByIdDesc();
    Page<Book> findAllByStatusIsTrueOrderByIdDesc(Pageable pageable);
    @Query(value = "select * from book where status = true and concat(name,code,author) like :key",nativeQuery = true)
    Page<Book> findAllWithKey(String key, Pageable pageable);
    @Query(value = "select * from book where id = (select max(id) from book)", nativeQuery = true)
    Optional<Book> getLastestBook();
    Optional<Book> findBookByName(String name);
    Optional<Book> findBookByCode(String code);
    Iterable<Book> findAllByNameContainingAndStatusIsTrue(String name);
}
