package com.example.demospring.repository;

import com.example.demospring.model.Order;
import com.example.demospring.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IOrderDetailRepository extends JpaRepository<OrderDetail,Long> {
    Iterable<OrderDetail> findAllByOrderAndStatusIsTrue(Order order);
    @Query(value = "select * from order_detail where status = true and order_id = :orderId and date_off is null", nativeQuery = true)
    Iterable<OrderDetail> findAllInBorrowing(Long orderId);
    @Query(value = "select * from order_detail where date_off is null and status = true and book_id = :book_id and order_id  = :order_id", nativeQuery = true)
    Optional<OrderDetail> findOrderDetaiByBookAndPerson(Long book_id, Long order_id);
}
