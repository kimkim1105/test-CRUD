package com.example.demospring.service.impl;

import com.example.demospring.model.Order;
import com.example.demospring.model.OrderDetail;
import com.example.demospring.model.Person;
import com.example.demospring.model.dto.OrderDetailDTO;
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
    public Page<OrderDetail> findAllByOrderCompleted(Long orderId, Pageable pageable, String key, String fromDate, String toDate) {
        String toDatefinal = new String();
        try {
            key = '%'+key+'%';
            if (fromDate.isEmpty()){
                fromDate = "1900-01-01";
            }
            if (toDate.isEmpty()){
                toDatefinal = LocalDate.now().plusDays(1).toString();
            }else {
                LocalDate toDateLocal = LocalDate.parse(toDate);
                toDateLocal = toDateLocal.plusDays(1);
                toDatefinal = toDateLocal.toString();
            }
            return iOrderDetailRepository.findAllByOrderCompleted(orderId, pageable, key, fromDate, toDatefinal);
        }catch (Exception e){
            return iOrderDetailRepository.findAllByOrderCompleted(orderId, pageable, key, fromDate, toDatefinal);
        }
    }

    @Override
    public String addNewOrderDetail(OrderDetailDTO orderDetailDTO) {
        return iOrderDetailRepository.addNewOrderDetail(orderDetailDTO.getBook().getId(), orderDetailDTO.getOrder().getId());
    }

    @Override
    public String returnOrderDetail(Long id) {
        return iOrderDetailRepository.returnOrderDetail(id);
    }

    @Override
    public String deleteOrderDetail(Long id) {
        return iOrderDetailRepository.deleteOrderDetail(id);
    }

    @Override
    public Optional<OrderDetail> getLastestOrderDetail() {
        return iOrderDetailRepository.getLastestOrderDetail();
    }
}
