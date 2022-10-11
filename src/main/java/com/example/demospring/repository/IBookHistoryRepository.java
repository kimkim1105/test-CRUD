package com.example.demospring.repository;

import com.example.demospring.model.view.BookHistory;
import com.example.demospring.model.view.PersonHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface IBookHistoryRepository extends JpaRepository<BookHistory, String> {
    @Procedure(name = "set_key_book_history")
    void paramSetKeyBook(@Param("p_key_param") String p_key_param);

    //    @Query(value = "select * from person_borrow_history", nativeQuery = true)
    Page<BookHistory> findAllBy(Pageable pageable);

    @Override
    List<BookHistory> findAll();
}
