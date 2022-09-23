package com.example.demospring.repository;

import com.example.demospring.model.Order;
import com.example.demospring.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {
    Iterable<Order> findOrderByPersonAndStatusIsTrue(Person person);
    Iterable<Order> findAllByStatusIsTrueOrderByIdDesc();
    Page<Order> findAllByStatusIsTrueOrderByIdDesc(Pageable pageable);
    @Query(value = "select * from orders where status = true and ((person_id in (select id from person where concat(name,code) like :key)) or(id in (select order_id from order_book where book_id in (select id from book where concat(name, code) like :key))))",nativeQuery = true)
    Page<Order> findAllWithKey(String key, Pageable pageable);
    @Query(value = "select * from orders where status = true and date_off is null and person_id = :id", nativeQuery = true)
    Iterable<Order> findAllByPersonAndDateOffNull(Long id);
    @Query(value = " select * from orders where status = true and date_off is null and id in (select order_id from order_book where book_id = :id)", nativeQuery = true)
    Iterable<Order> findAllByBookAndDateOffNull(Long id);
}
