package com.example.demospring.repository;

import com.example.demospring.model.Book;
import com.example.demospring.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBookRepository extends JpaRepository<Book,Long> {
    Iterable<Book> findAllByStatusIsTrueOrderByIdDesc();
    @Query(value = "select * from book where status = true and in_stock>0 order by id desc", nativeQuery = true)
    Iterable<Book> findAllBookInFree();
    Page<Book> findAllByStatusIsTrueOrderByIdDesc(Pageable pageable);
    @Query(value = "select * from book where status = true and concat(name,code,author) like :key order by id desc",nativeQuery = true)
    Page<Book> findAllWithKey(String key, Pageable pageable);
    @Query(value = "select * from book where status = true and concat(name,code,author) like :key and category_id = :category_id order by id desc",nativeQuery = true)
    Page<Book> findAllWithKeyAndCategory(String key,Long category_id, Pageable pageable);
    @Query(value = "select * from book where status = true and concat(name,code,author) like :key order by id desc",nativeQuery = true)
    Iterable<Book> findBookWithKey(String key);
    @Query(value = "select * from book where status = true and concat(name,code,author) like :key and category_id = :category_id order by id desc",nativeQuery = true)
    Iterable<Book> findBookWithKeyAndCategory(String key,Long category_id);
    @Query(value = "select * from book where id = (select max(id) from book)", nativeQuery = true)
    Optional<Book> getLastestBook();
    Optional<Book> findBookByName(String name);
    Iterable<Book> findAllByName(String string);
    Optional<Book> findBookByCode(String code);
    Iterable<Book> findAllByNameContainingAndStatusIsTrue(String name);
    @Query(value = "select * from book where status = true and in_stock>0 and concat(name,code,author) like :key order by id desc",nativeQuery = true)
    Iterable<Book> findBookWithKeyBorroed(String key);
}
