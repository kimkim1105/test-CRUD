package com.example.demospring.service;

import com.example.demospring.model.Order;
import com.example.demospring.model.OrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IOrderDetailService extends IGeneralService<OrderDetail> {
    Iterable<OrderDetail> findAllInBorrowing(Long orderId);
    Optional<OrderDetail> findOrderDetaiByBookAndPerson(Long book_id, Long order_id);
    Page<OrderDetail> findAllByOrderCompleted(Long orderId, Pageable pageable, String key, String from, String to);
}
