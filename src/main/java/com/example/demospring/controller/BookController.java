package com.example.demospring.controller;

import com.example.demospring.model.Book;
import com.example.demospring.model.BookCategory;
import com.example.demospring.model.dto.BookDTO;
import com.example.demospring.service.IBookCategoryService;
import com.example.demospring.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    IBookService iBookService;
    @Autowired
    IBookCategoryService iBookCategoryService;
    @GetMapping
    public String getListBook(@RequestParam(required = false, name = "key") String key, Model model, @PageableDefault(value = 5) Pageable pageable){
        model.addAttribute("books", iBookService.findAllWithKey(key, pageable));
        return "book/bookList";
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<?> getBookByName(@PathVariable String name){
        if (iBookService.findBookByName(name).isPresent()){
            return new ResponseEntity<>("existed", HttpStatus.OK);
        }
        return new ResponseEntity<>("ok",HttpStatus.OK);
    }
    @GetMapping("/code/{code}")
    public ResponseEntity<?> getBookByCode(@PathVariable String code){
        if (iBookService.findBookByCode(code).isPresent()){
            return new ResponseEntity<>("existed", HttpStatus.OK);
        }
        return new ResponseEntity<>("ok",HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id){
        return new ResponseEntity<>(iBookService.findById(id),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> addNewBook(@RequestBody BookDTO bookDTO){
        if (iBookService.findBookByName(bookDTO.getName()).isPresent()){
            return new ResponseEntity<>("bookname existed, pls try again",HttpStatus.OK);
        }
        if (iBookService.findBookByCode(bookDTO.getCode()).isPresent()){
            return new ResponseEntity<>("code existed, pls try again",HttpStatus.OK);
        }
        Book book = new Book();
        book.setName(bookDTO.getName());
        book.setStatus(true);
        book.setCode(bookDTO.getCode());
        book.setAuthor(bookDTO.getAuthor());
        Optional<BookCategory> bookCategoryOptional = iBookCategoryService.findById(bookDTO.getCategory().getId());
        book.setCategory(bookCategoryOptional.get());
        if (bookDTO.getInStock()>0){
            book.setInStock(bookDTO.getInStock());
        }else {
            book.setInStock(0);
        }
//        Optional<Book> bookOptional = iBookService.getLastestBook();
//        StringBuffer bookCode = new StringBuffer();
//        Long lastId = bookOptional.get().getId();
//        String format = String.format("%05d",(lastId+1));
//        String categoryCode = bookCategoryOptional.get().getCode();
//        bookCode.append(categoryCode);
//        bookCode.append(format);
//        book.setCode(bookCode.toString());
        return new ResponseEntity<>(iBookService.save(book),HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<?> editBook(@RequestBody Book book){
        if (iBookService.findBookByName(book.getName()).isPresent()){
            return new ResponseEntity<>("bookname existed, pls try again",HttpStatus.OK);
        }
        if (iBookService.findBookByCode(book.getCode()).isPresent()){
            return new ResponseEntity<>("code existed, pls try again",HttpStatus.OK);
        }
        Book bookOptional = iBookService.findById(book.getId()).get();
        bookOptional.setName(book.getName());
        bookOptional.setStatus(true);
        bookOptional.setCode(book.getCode());
        bookOptional.setAuthor(book.getAuthor());
        Optional<BookCategory> bookCategoryOptional = iBookCategoryService.findById(book.getCategory().getId());
        bookOptional.setCategory(bookCategoryOptional.get());
        return new ResponseEntity<>(iBookService.save(bookOptional),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id){
        Book bookOptional = iBookService.findById(id).get();
        iBookService.remove(bookOptional.getId());
        return new ResponseEntity<>(iBookService.save(bookOptional),HttpStatus.OK);
    }
}
