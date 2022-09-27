package com.example.demospring.service;

import com.example.demospring.model.Order;
import com.example.demospring.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IOrderService extends IGeneralService<Order>{
    Page<Order> findAllWithKey(String key, Pageable pageable);
    Page<Order> findAllHistoryWithKey(String key, Pageable pageable);
//    Page<Order> findAll(Pageable pageable);
    Iterable<Order> findAllByPersonAndDateOffNull(Long id);
    Iterable<Order> findAllByBookAndDateOffNull(Long id);
Optional<Order> findOrderByPerson(Person person);
}
