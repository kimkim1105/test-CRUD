package com.example.demospring.repository;

import com.example.demospring.model.view.PersonHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface IPersonHistoryRepository extends JpaRepository<PersonHistory, String> {
    @Query(value = "SELECT * from PERSON_BORROW_HISTORY per \n" +
            "where lower(concat(CONCAT(per.PERSON_CODE,per.PERSON_NAME),per.PERSON_PHONE)) \n" +
            "like lower(:key) order by per.PERSON_ID desc",nativeQuery = true)
    Page<PersonHistory> finPersonHistoryWithKey(String key, Pageable pageable);
    @Query(value = "SELECT * from PERSON_BORROW_HISTORY per \n" +
            "where lower(concat(CONCAT(per.PERSON_CODE,per.PERSON_NAME),per.PERSON_PHONE)) \n" +
            "like lower(:key) order by per.PERSON_ID desc",nativeQuery = true)
    Iterable<PersonHistory> getHistory(String key);
}
