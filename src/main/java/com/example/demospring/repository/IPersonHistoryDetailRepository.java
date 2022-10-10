package com.example.demospring.repository;

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
public interface IPersonHistoryDetailRepository extends JpaRepository<PersonHistoryDetail, String> {
    @Procedure(name = "set_order_id")
    void paramSetOrderId(@Param("p_person_id") Long p_person_id);
    @Procedure(name = "set_key_book_person_history")
    void paramSetKeyBook(@Param("p_key_param") String p_key_param);
    Page<PersonHistoryDetail> findAllBy(Pageable pageable);
}
