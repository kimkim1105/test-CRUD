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
    @Query(value = "select * from person where status = true and type_action= false order by id desc", nativeQuery = true)
    Iterable<Person> findAllPersonInFree();
    Page<Person> findAllByStatusIsTrueOrderByIdDesc(Pageable pageable);
    @Query(value = "select * from person where status = true and concat(name,code) like :key order by id desc",nativeQuery = true)
    Page<Person> findAllWithKey(String key, Pageable pageable);
    @Query(value = "select * from person where status = true and concat(name,code) like :key order by id desc",nativeQuery = true)
    Iterable<Person> findPersonWithKey(String key);
    @Query(value = "select * from person where id = (select max(id) from person)", nativeQuery = true)
    Optional<Person> getLastestPerson();
    Optional<Person> findPersonByPhone(String phone);
    Iterable<Person> findAllByNameContainingAndStatusIsTrue(String name);
    @Query(value = "select * from person where status = true and type_action = false and concat(name,code) like :key order by id desc",nativeQuery = true)
    Iterable<Person> findPersonWithKeyBorroed(String key);
}
