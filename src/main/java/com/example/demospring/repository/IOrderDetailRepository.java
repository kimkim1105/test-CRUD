package com.example.demospring.repository;

import com.example.demospring.model.Order;
import com.example.demospring.model.OrderDetail;
import com.example.demospring.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface IOrderDetailRepository extends JpaRepository<OrderDetail,Long> {

    @Procedure(name = "addMoreBook")
    String addNewOrderDetail(@Param("v_book_id") Long v_book_id, @Param("v_order_id") Long v_order_id);

    @Procedure(name = "returnBook")
    String returnOrderDetail(@Param("v_order_detail_id") Long v_order_detail_id);

    @Procedure(name = "deleteBookOrder")
    String deleteOrderDetail(@Param("v_order_detail_id") Long v_order_detail_id);

//    @Query(value = "select * from order_detail where status = true and order_id = :orderId and book_id in (select id from book where concat(name, author,code) like :key) and date_on between :from and :to and date_off is not null", nativeQuery = true)
    @Query(value = "select * from order_detail \n" +
            "where status = 1 and order_id = :orderId \n" +
            "and book_id in (select id from book where lower(concat(concat(name,code),author)) like lower(:key)) \n" +
            "and date_on >= to_date(:fromDate,'yyyy-mm-dd') and  date_on <=  to_date(:toDate,'yyyy-mm-dd') and date_off is not null", nativeQuery = true)
    Page<OrderDetail> findAllByOrderCompleted(Long orderId, Pageable pageable, String key, String fromDate, String toDate);
//    @Query(value = "select * from order_detail where status = true and order_id = :orderId and date_off is null", nativeQuery = true)
    @Query(value = "select * from order_detail where status = 1 and order_id = :orderId and date_off is null", nativeQuery = true)
    Iterable<OrderDetail> findAllInBorrowing(Long orderId);
//    @Query(value = "select * from order_detail where date_off is null and status = true and book_id = :book_id and order_id  = :order_id", nativeQuery = true)
    @Query(value = "select * from order_detail where date_off is null and status = 1 and book_id = :book_id and order_id  = :order_id", nativeQuery = true)
    Optional<OrderDetail> findOrderDetaiByBookAndPerson(Long book_id, Long order_id);
    @Query(value = "select * from order_detail where id = (select max(id) from order_detail)", nativeQuery = true)
    Optional<OrderDetail> getLastestOrderDetail();
}
