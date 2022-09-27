package com.example.demospring.service.impl;

import com.example.demospring.model.Order;
import com.example.demospring.model.OrderDetail;
import com.example.demospring.repository.IOrderDetailRepository;
import com.example.demospring.service.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class OrderDetailServiceImpl implements IOrderDetailService {
    @Autowired
    IOrderDetailRepository iOrderDetailRepository;
    @Override
    public Iterable<OrderDetail> findAll() {
        return null;
    }

    @Override
    public Optional<OrderDetail> findById(Long id) {
        return iOrderDetailRepository.findById(id);
    }

    @Override
    public OrderDetail save(OrderDetail orderDetail) {
        return iOrderDetailRepository.save(orderDetail);
    }

    @Override
    public void remove(Long id) {
        OrderDetail orderDetail = iOrderDetailRepository.findById(id).get();
        orderDetail.setStatus(false);
        iOrderDetailRepository.save(orderDetail);
    }

    @Override
    public Iterable<OrderDetail> findAllInBorrowing(Long orderId) {
        return iOrderDetailRepository.findAllInBorrowing(Long.valueOf(orderId));
    }

    @Override
    public Optional<OrderDetail> findOrderDetaiByBookAndPerson(Long book_id, Long order_id) {
        return iOrderDetailRepository.findOrderDetaiByBookAndPerson(book_id, order_id);
    }

    @Override
    public Page<OrderDetail> findAllByOrderCompleted(Long orderId, Pageable pageable, String key, String from, String to) {
        try {
            key = '%'+key+'%';
            if (from.isEmpty()){
                from = "1900-01-01";
            }
            if (to.isEmpty()){
                to = LocalDate.now().toString();
            }
            return iOrderDetailRepository.findAllByOrderCompleted(orderId, pageable, key, from, to);
        }catch (Exception e){
            return iOrderDetailRepository.findAllByOrderCompleted(orderId, pageable, key, from, to);
        }
    }
}
