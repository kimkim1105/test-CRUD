package com.example.demospring.service;

import com.example.demospring.model.Person;
import com.example.demospring.model.dto.PersonDTO;
import com.example.demospring.model.view.PersonHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

public interface IPersonService extends IGeneralService<Person> {
    Page<Person> findAllWithKey(String key, String fromDate, String toDate, Pageable pageable);
    Page<Person> findAll(Pageable pageable);
    Optional<Person> getLastestPerson();
    Optional<Person> findPersonByPhone(String phone);
    Iterable<Person> findPersonWithKey(String key, String from, String to);
    Iterable<Person> findAllByNameContainingAndStatusIsTrue(String name);
    Iterable<Person> findPersonWithKeyBorroed(String key);
    Iterable<Person> findAllPersonInFree();
    String addNewPerson(PersonDTO personDTO);
    String updatePerson(Long id, PersonDTO personDTO);
    String deletPerson(Long id);
    Page<PersonHistory> findPersonHistoryWithKey(String key, Pageable pageable);
}
