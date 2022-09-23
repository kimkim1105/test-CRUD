package com.example.demospring.repository;

import com.example.demospring.model.Order;
import com.example.demospring.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderDetailRepository extends JpaRepository<OrderDetail,Long> {
    Iterable<OrderDetail> findAllByOrderAndStatusIsTrue(Order order);
}
