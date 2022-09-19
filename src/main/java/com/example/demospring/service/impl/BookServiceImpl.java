package com.example.demospring.service.impl;

import com.example.demospring.model.Book;
import com.example.demospring.repository.IBookRepository;
import com.example.demospring.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookServiceImpl implements IBookService {
    @Autowired
    IBookRepository iBookRepository;
    @Override
    public Iterable<Book> findAll() {
        return iBookRepository.findAllByStatusIsTrueOrderByIdDesc();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return iBookRepository.findById(id);
    }

    @Override
    public Book save(Book book) {
        return iBookRepository.save(book);
    }

    @Override
    public void remove(Long id) {
iBookRepository.findById(id).get().setStatus(false);
    }

    @Override
    public Page<Book> findAllByStatusIsTrueOrderByIdDesc(Pageable pageable) {
        return iBookRepository.findAllByStatusIsTrueOrderByIdDesc(pageable);
    }

    @Override
    public Page<Book> findAllWithKey(String key, Pageable pageable) {
        try {
            if (!key.isEmpty()){
                return iBookRepository.findAllWithKey('%'+key+'%', pageable);
            }else {
                return iBookRepository.findAllByStatusIsTrueOrderByIdDesc(pageable);
            }
        }catch (Exception e){
            return iBookRepository.findAllByStatusIsTrueOrderByIdDesc(pageable);
        }
    }

    @Override
    public Iterable<Book> findBookWithKey(String key) {
        try {
            if (!key.isEmpty()){
                return iBookRepository.findBookWithKey('%'+key+'%');
            }else {
                return iBookRepository.findAllByStatusIsTrueOrderByIdDesc();
            }
        }catch (Exception e){
            return iBookRepository.findAllByStatusIsTrueOrderByIdDesc();
        }
    }

    @Override
    public Optional<Book> getLastestBook() {
        return iBookRepository.getLastestBook();
    }

    @Override
    public Optional<Book> findBookByName(String name) {
        return iBookRepository.findBookByName(name);
    }

    @Override
    public Optional<Book> findBookByCode(String code) {
        return iBookRepository.findBookByCode(code);
    }

    @Override
    public Iterable<Book> findAllByNameContainingAndStatusIsTrue(String name) {
        return null;
    }

    @Override
    public Iterable<Book> findBookWithKeyBorroed(String key) {
        try {
            if (!key.isEmpty()){
                return iBookRepository.findBookWithKeyBorroed('%'+key+'%');
            }else {
                return iBookRepository.findAllBookInFree();
            }
        }catch (Exception e){
            return iBookRepository.findAllBookInFree();
        }
    }

    @Override
    public Iterable<Book> findAllBookInFree() {
        return iBookRepository.findAllBookInFree();
    }
}
