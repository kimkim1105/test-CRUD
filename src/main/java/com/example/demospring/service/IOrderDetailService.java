package com.example.demospring.service;

import com.example.demospring.model.Order;
import com.example.demospring.model.OrderDetail;

import java.util.Optional;

public interface IOrderDetailService extends IGeneralService<OrderDetail> {
    Iterable<OrderDetail> findAllInBorrowing(Long orderId);
    Optional<OrderDetail> findOrderDetaiByBookAndPerson(Long book_id, Long order_id);
}
