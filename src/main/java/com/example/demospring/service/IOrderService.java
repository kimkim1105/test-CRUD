package com.example.demospring.service;

import com.example.demospring.model.Order;
import com.example.demospring.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrderService extends IGeneralService<Order>{
    Page<Order> findAllWithKey(String key, Pageable pageable);
    Page<Order> findAll(Pageable pageable);
}
