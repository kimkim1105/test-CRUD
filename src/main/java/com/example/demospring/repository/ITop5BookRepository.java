package com.example.demospring.repository;

import com.example.demospring.model.view.Top5Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ITop5BookRepository extends JpaRepository<Top5Book, String> {
    @Query(value = "select total_book_instock from dual", nativeQuery = true)
    Integer getTotalBook();
    @Query(value = "select total_person from dual", nativeQuery = true)
    Integer getTotalPerson();
    @Query(value = "select count_of_borrowing_book from dual", nativeQuery = true)
    Integer getBorrowingBook();
    @Query(value = "select count_of_borrowing_person from dual", nativeQuery = true)
    Integer getBorrowingPerson();
    @Query(value = "select count_of_borrowing_over_date from dual", nativeQuery = true)
    Integer getBorrowingOverDate();
}
