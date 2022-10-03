package com.example.demospring.service;

import com.example.demospring.model.Order;
import com.example.demospring.model.OrderDetail;
import com.example.demospring.model.Person;
import com.example.demospring.model.dto.OrderDetailDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IOrderDetailService extends IGeneralService<OrderDetail> {
    Iterable<OrderDetail> findAllInBorrowing(Long orderId);
    Optional<OrderDetail> findOrderDetaiByBookAndPerson(Long book_id, Long order_id);
    Page<OrderDetail> findAllByOrderCompleted(Long orderId, Pageable pageable, String key, String from, String to);
    String addNewOrderDetail(OrderDetailDTO orderDetailDTO);
    String returnOrderDetail(Long id);
    String deleteOrderDetail(Long id);
    Optional<Person> getLastestOrderDetail();
}
