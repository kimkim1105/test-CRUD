package com.example.demospring.repository;

import com.example.demospring.model.Book;
import com.example.demospring.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBookRepository extends JpaRepository<Book,Long> {
    @Procedure(name = "addNewBook")
    String addNewBook(@Param("v_author") String v_author, @Param("v_code") String v_code, @Param("v_inStock") String v_inStock, @Param("v_name") String v_name, @Param("v_category_id") Long v_category_id);

    @Procedure(name = "updateBook")
    String updateBook(@Param("v_author") String v_author, @Param("v_name") String v_name, @Param("v_category_id") Long v_category_id, @Param("v_book_id") Long v_book_id);

    @Procedure(name = "deleteBook")
    String deletBook( @Param("v_book_id") Long v_book_id);


//    @Query(value = "select BOOK_CRUD_PKG.addNewBook(?,?,?,?,?) from dual", nativeQuery = true)
//    Integer addNewBook(String v_author, String v_code, String v_inStock, String v_name, Long v_category_id);

    Iterable<Book> findAllByStatusIsTrueOrderByIdDesc();
    @Query(value = "select * from book where status = 1 and in_stock>0 order by id desc", nativeQuery = true)

    Iterable<Book> findAllBookInFree();
    Page<Book> findAllByStatusIsTrueOrderByIdDesc(Pageable pageable);
//    @Query(value = "select * from book where status = true and concat(name,code,author) like :key order by id desc",nativeQuery = true)
    @Query(value = "select * from book where status = 1 and lower(concat(concat(name,code),author)) like lower(:key) order by id desc",nativeQuery = true)
    Page<Book> findAllWithKey(String key, Pageable pageable);
//    @Query(value = "select * from book where status = true and concat(name,code,author) like :key and category_id = :category_id order by id desc",nativeQuery = true)
    @Query(value = "select * from book where status = 1 and lower(concat(concat(name,code),author)) like lower(:key) and category_id = :category_id order by id desc",nativeQuery = true)
    Page<Book> findAllWithKeyAndCategory(String key,Long category_id, Pageable pageable);
//    @Query(value = "select * from book where status = true and concat(name,code,author) like :key order by id desc",nativeQuery = true)
    @Query(value = "SELECT * FROM BOOK WHERE STATUS =1 AND lower(concat(concat(name,code),author)) LIKE lower(:key) ORDER BY ID DESC",nativeQuery = true)
    Iterable<Book> findBookWithKey(String key);
//    @Query(value = "select * from book where status = true and concat(name,code,author) like :key and category_id = :category_id order by id desc",nativeQuery = true)
    @Query(value = "select * from book where status = true and lower(concat(concat(name,code),author)) like lower(:key) and category_id = :category_id order by id desc",nativeQuery = true)
    Iterable<Book> findBookWithKeyAndCategory(String key,Long category_id);
    @Query(value = "select * from book where id = (select max(id) from book)", nativeQuery = true)
    Optional<Book> getLastestBook();
    Optional<Book> findBookByName(String name);
    Iterable<Book> findAllByName(String string);
    Optional<Book> findBookByCode(String code);
    Iterable<Book> findAllByNameContainingAndStatusIsTrue(String name);
//    @Query(value = "select * from book where status = true and in_stock>0 and concat(name,code,author) like :key order by id desc",nativeQuery = true)
    @Query(value = "select * from book where status = 1 and in_stock>0 and lower(concat(concat(name,code),author)) like lower(:key) order by id desc",nativeQuery = true)
    Iterable<Book> findBookWithKeyBorroed(String key);
}
