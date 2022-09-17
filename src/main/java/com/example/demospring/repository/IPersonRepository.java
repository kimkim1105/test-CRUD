package com.example.demospring.repository;

import com.example.demospring.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPersonRepository extends JpaRepository<Person, Long> {
    Iterable<Person> findAllByStatusIsTrueOrderByIdDesc();
    Page<Person> findAllByStatusIsTrueOrderByIdDesc(Pageable pageable);
    @Query(value = "select * from person where status = true and concat(name,code) like :key",nativeQuery = true)
    Page<Person> findAllWithKey(String key, Pageable pageable);
    @Query(value = "select * from person where id = (select max(id) from person)", nativeQuery = true)
    Optional<Person> getLastestPerson();
    Optional<Person> findPersonByPhone(String phone);
    Iterable<Person> findAllByNameContainingAndStatusIsTrue(String name);
}
