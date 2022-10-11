package com.example.demospring.repository;

import com.example.demospring.model.view.OrderDetailView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface IOrderSearchDetailRepository extends JpaRepository<OrderDetailView, String> {
    @Procedure(name = "set_order_id_search_detail")
    void paramSetOrderIdSearch(@Param("p_order_id") Long p_order_id);
}
