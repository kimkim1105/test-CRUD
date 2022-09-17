package com.example.demospring.service.impl;

import com.example.demospring.model.BookCategory;
import com.example.demospring.repository.IBookCategoryRepository;
import com.example.demospring.service.IBookCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookCategoryImpl implements IBookCategoryService {
    @Autowired
    IBookCategoryRepository iBookCategoryRepository;
    @Override
    public Iterable<BookCategory> findAll() {
        return iBookCategoryRepository.findAll();
    }

    @Override
    public Optional<BookCategory> findById(Long id) {
        return iBookCategoryRepository.findById(id);
    }

    @Override
    public BookCategory save(BookCategory bookCategory) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
