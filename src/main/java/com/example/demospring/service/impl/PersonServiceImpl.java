package com.example.demospring.service.impl;

import com.example.demospring.model.Person;
import com.example.demospring.repository.IPersonRepository;
import com.example.demospring.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class PersonServiceImpl implements IPersonService {
    @Autowired
    IPersonRepository iPersonRepository;

    @Override
    public Page<Person> findAll(Pageable pageable) {
        return iPersonRepository.findAllByStatusIsTrueOrderByIdDesc(pageable);
    }

    @Override
    public Optional<Person> getLastestPerson() {
        return iPersonRepository.getLastestPerson();
    }

    @Override
    public Optional<Person> findPersonByPhone(String phone) {
        return iPersonRepository.findPersonByPhone(phone);
    }

    @Override
    public Iterable<Person> findPersonWithKey(String key, String from, String to) {
        try {
            key = '%'+key+'%';
            if (from.isEmpty()){
                from = "1900-01-01";
            }
            if (to.isEmpty()){
                to = LocalDate.now().toString();
            }
            return iPersonRepository.findPersonWithKey(key,from,to);
        }catch (Exception e){
            return iPersonRepository.findAllByStatusIsTrueOrderByIdDesc();
        }
    }

    @Override
    public Iterable<Person> findAllByNameContainingAndStatusIsTrue(String name) {
        return iPersonRepository.findAllByNameContainingAndStatusIsTrue(name);
    }

    @Override
    public Iterable<Person> findPersonWithKeyBorroed(String key) {
        try {
            if (!key.isEmpty()){
                return iPersonRepository.findPersonWithKeyBorroed('%'+key+'%');
            }else {
                return iPersonRepository.findAllPersonInFree();
            }
        }catch (Exception e){
            return iPersonRepository.findAllPersonInFree();
        }
    }

    @Override
    public Iterable<Person> findAllPersonInFree() {
        return iPersonRepository.findAllPersonInFree();
    }

    @Override
    public Iterable<Person> findAll() {
        return iPersonRepository.findAllByStatusIsTrueOrderByIdDesc();
    }

    @Override
    public Optional<Person> findById(Long id) {
        return iPersonRepository.findById(id);
    }

    @Override
    public Person save(Person person) {
        return iPersonRepository.save(person);
    }

    @Override
    public void remove(Long id) {
        iPersonRepository.findById(id).get().setStatus(false);
    }

    @Override
    public Page<Person> findAllWithKey(String key, String from, String to, Pageable pageable) {
        try {
            key = '%'+key+'%';
            if (from.isEmpty()){
                from = "1900-01-01";
            }
            if (to.isEmpty()){
                to = LocalDate.now().toString();
            }
                return iPersonRepository.findAllWithKey(key, from, to, pageable);
        }catch (Exception e){
            return iPersonRepository.findAllByStatusIsTrueOrderByIdDesc(pageable);
        }
    }
}
