package com.example.demospring.service.impl;

import com.example.demospring.model.Order;
import com.example.demospring.model.Person;
import com.example.demospring.repository.IOrderRepository;
import com.example.demospring.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    IOrderRepository iOrderRepository;
    @Override
    public Iterable<Order> findAll() {
        return iOrderRepository.findAllByStatusIsTrueOrderByIdDesc();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return iOrderRepository.findById(id);
    }

    @Override
    public Order save(Order order) {
        return iOrderRepository.save(order);
    }

    @Override
    public void remove(Long id) {
        iOrderRepository.findById(id).get().setStatus(false);
    }

    @Override
    public Page<Order> findAllWithKey(String key, Pageable pageable) {
        try {
            if (!key.isEmpty()){
                return iOrderRepository.findAllWithKey('%'+key+'%', pageable);
            }else {
                return iOrderRepository.findAllByStatusIsTrueOrderByIdDesc(pageable);
            }
        }catch (Exception e){
            return iOrderRepository.findAllByStatusIsTrueOrderByIdDesc(pageable);
        }
    }

    @Override
    public Page<Order> findAll(Pageable pageable) {
        return iOrderRepository.findAllByStatusIsTrueOrderByIdDesc(pageable);
    }

    @Override
    public Iterable<Order> findAllByPersonAndDateOffNull(Long id) {
        return iOrderRepository.findAllByPersonAndDateOffNull(id);
    }

    @Override
    public Iterable<Order> findAllByBookAndDateOffNull(Long id) {
        return iOrderRepository.findAllByBookAndDateOffNull(id);
    }
}
