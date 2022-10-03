package com.example.demospring.service.impl;

import com.example.demospring.model.Book;
import com.example.demospring.model.dto.BookDTO;
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
    public Page<Book> findAllWithKeyAndCategory(String key,String category_id, Pageable pageable) {
        try {
            key = '%'+key+'%';
            if (category_id.isEmpty()||category_id.equals("0")||category_id==null){
                return iBookRepository.findAllWithKey('%'+key+'%', pageable);
            }else {
                return iBookRepository.findAllWithKeyAndCategory(key, Long.valueOf(category_id), pageable);
            }
        }catch (Exception e){
            return iBookRepository.findAllByStatusIsTrueOrderByIdDesc(pageable);
        }
    }

    @Override
    public Iterable<Book> findBookWithKeyAndCategory(String key,String category_id) {
        try {
            key = '%'+key+'%';
            if (category_id.isEmpty()||category_id.equals("0")){
                return iBookRepository.findBookWithKey('%'+key+'%');
            }else {
                return iBookRepository.findBookWithKeyAndCategory(key, Long.valueOf(category_id));
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

    @Override
    public String addNewBook(BookDTO bookDTO) {
        return iBookRepository.addNewBook(bookDTO.getAuthor(),bookDTO.getCode(),bookDTO.getInStock(),bookDTO.getName(),bookDTO.getCategory().getId());
    }

    @Override
    public String updateBook(Long id, BookDTO bookDTO) {
        return iBookRepository.updateBook(bookDTO.getAuthor(), bookDTO.getName(),bookDTO.getCategory().getId(),id );
    }

    @Override
    public String deleteBook(Long id) {
        return iBookRepository.deletBook(id);
    }
}
