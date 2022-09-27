package com.example.demospring.repository;

import com.example.demospring.model.Order;
import com.example.demospring.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {
//    Iterable<Order> findOrderByPersonAndStatusIsTrue(Person person);
//    Iterable<Order> findAllByStatusIsTrueOrderByIdDesc();
//    @Query(value = "select * from orders where id in (select order_id from order_detail where status = true)", nativeQuery = true)
//    Page<Order> findAllByStatusIsTrueOrderByIdDesc(Pageable pageable);
    @Query(value = "select * from orders where book_size > 0 and (person_id in (select id from person where concat(name,code) like :key)) and id in (select order_id from order_detail where status = true)",nativeQuery = true)
    Page<Order> findAllWithKey(String key, Pageable pageable);
//    @Query(value = "select * from orders where status = true and date_off is null and person_id = :id", nativeQuery = true)
//    Iterable<Order> findAllOrderWithKey(String key);
    @Query(value = " select * from orders where id in (select order_id from order_detail where book_id = :id and status = true and date_off is null)", nativeQuery = true)
    Iterable<Order> findAllByBookAndDateOffNull(Long id);
        @Query(value = "select * from orders where person_id = :id and id in (select order_id from order_detail where status = true and date_off is null)", nativeQuery = true)
    Iterable<Order> findAllByPersonAndDateOffNull(Long id);
@Query(value = "select * from orders where book_return > 0 and (person_id in (select id from person where concat(name,code) like :key)) and id in (select order_id from order_detail where status = true)",nativeQuery = true)
Page<Order> findAllHistoryWithKey(String key, Pageable pageable);

    Optional<Order> findOrderByPerson(Person person);
}
