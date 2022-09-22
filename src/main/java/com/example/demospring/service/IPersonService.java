package com.example.demospring.service;

import com.example.demospring.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IPersonService extends IGeneralService<Person> {
    Page<Person> findAllWithKey(String key, String from, String to, Pageable pageable);
    Page<Person> findAll(Pageable pageable);
    Optional<Person> getLastestPerson();
    Optional<Person> findPersonByPhone(String phone);
    Iterable<Person> findPersonWithKey(String key, String from, String to);
    Iterable<Person> findAllByNameContainingAndStatusIsTrue(String name);
    Iterable<Person> findPersonWithKeyBorroed(String key);
    Iterable<Person> findAllPersonInFree();
}
