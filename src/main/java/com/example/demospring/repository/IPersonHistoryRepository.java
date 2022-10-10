package com.example.demospring.repository;

import com.example.demospring.model.view.PersonHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface IPersonHistoryRepository extends JpaRepository<PersonHistory, String> {
    @Procedure(name = "set_key_person_history")
    void paramSetKeyPerson(@Param("p_key_param") String p_key_param);
//    @Query(value = "select * from person_borrow_history", nativeQuery = true)
    Page<PersonHistory> findAllBy(Pageable pageable);

    @Override
    List<PersonHistory> findAll();
}
