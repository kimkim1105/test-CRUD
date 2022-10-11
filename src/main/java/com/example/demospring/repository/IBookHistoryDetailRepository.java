package com.example.demospring.repository;

import com.example.demospring.model.view.BookHistoryDetail;
import com.example.demospring.model.view.PersonHistoryDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface IBookHistoryDetailRepository extends JpaRepository<BookHistoryDetail, String> {
    @Procedure(name = "set_book_id")
    void paramSetBookId(@Param("p_book_id") Long p_person_id);
    @Procedure(name = "set_key_person_search_book_history")
    void paramSetKeyPersonBorrowBook(@Param("p_key_param") String p_key_param);
    Page<BookHistoryDetail> findAllBy(Pageable pageable);
}
