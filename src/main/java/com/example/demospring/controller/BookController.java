package com.example.demospring.controller;

import com.example.demospring.model.Book;
import com.example.demospring.model.BookCategory;
import com.example.demospring.model.Person;
import com.example.demospring.model.dto.BookDTO;
import com.example.demospring.model.dto.Validation;
import com.example.demospring.service.IBookCategoryService;
import com.example.demospring.service.IBookService;
import com.example.demospring.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    IBookService iBookService;
    @Autowired
    IBookCategoryService iBookCategoryService;
    @Autowired
    IOrderService iOrderService;
    @GetMapping
    public String getListBook(@RequestParam(required = false, name = "key") String key,
                              @RequestParam(required = false, name = "category_id") Long category_id,
                              Model model,
                              @PageableDefault(value = 5) Pageable pageable){
        model.addAttribute("books", iBookService.findAllWithKeyAndCategory(key, category_id, pageable));
        return "book/bookList";
    }
    @GetMapping("/listBook")
    public ResponseEntity<?> getListBookWithKey(@RequestParam(required = false, name = "key") String key,
                                                @RequestParam(required = false, name = "category_id") Long category_id){
        if (!iBookService.findBookWithKeyAndCategory(key, category_id).iterator().hasNext()){
            return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(iBookService.findBookWithKeyAndCategory(key, category_id), HttpStatus.OK);
    }
    @GetMapping("/free")
    public ResponseEntity<?> getListBookInFree(@RequestParam(required = false, name = "key") String key){
        return new ResponseEntity<>(iBookService.findBookWithKeyBorroed(key), HttpStatus.OK);
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
        if (!iBookService.findById(id).isPresent()){
            return new ResponseEntity<>("not found",HttpStatus.NOT_FOUND);
        }
        if (!iBookService.findById(id).get().isStatus()){
            return new ResponseEntity<>("Not found",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(iBookService.findById(id),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> addNewBook(@RequestBody @Valid BookDTO bookDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            Map<String, String> errors= new HashMap<>();

            bindingResult.getFieldErrors().forEach(
                    error -> errors.put(error.getField(), error.getDefaultMessage())
            );
            String errorMsg= "";

            for(String key: errors.keySet()){
                errorMsg+= "error in: " + key + ", by: " + errors.get(key) + "\n";
            }
            return new ResponseEntity<>(errorMsg,HttpStatus.OK);
        }
        if (iBookService.findBookByName(bookDTO.getName()).isPresent()){
            return  new ResponseEntity<>("bookname existed", HttpStatus.OK);
        }
        if (iBookService.findBookByCode(bookDTO.getCode()).isPresent()){
            return  new ResponseEntity<>("code existed", HttpStatus.OK);
        }
        Book book = new Book();
        book.setName(bookDTO.getName());
        book.setStatus(true);
        book.setCode(bookDTO.getCode());
        book.setAuthor(bookDTO.getAuthor());
        Optional<BookCategory> bookCategoryOptional = iBookCategoryService.findById(bookDTO.getCategory().getId());
        book.setCategory(bookCategoryOptional.get());
        book.setInStock(Integer.valueOf(bookDTO.getInStock()));
        return new ResponseEntity<>(iBookService.save(book),HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<?> editBook(@RequestParam Long id,@RequestBody @Valid BookDTO book, BindingResult bindingResult){
        if (!iBookService.findById(id).isPresent()){
            return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
        }
        Book bookOptional = iBookService.findById(id).get();
        if (iOrderService.findAllByBookAndDateOffNull(id).iterator().hasNext()){
            return new ResponseEntity<>("Book in borrowing, can't edit",HttpStatus.OK);
        }
        if (bindingResult.hasErrors()){
            Map<String, String> errors= new HashMap<>();

            bindingResult.getFieldErrors().forEach(
                    error -> errors.put(error.getField(), error.getDefaultMessage())
            );
            String errorMsg= "";

            for(String key: errors.keySet()){
                errorMsg+= "error in: " + key + ", by: " + errors.get(key) + "\n";
            }
            return new ResponseEntity<>(errorMsg,HttpStatus.OK);
        }
        if (bookOptional.getName().equals(book.getName())){
            bookOptional.setName(book.getName());
        }else {
            if (iBookService.findBookByName(book.getName()).isPresent()){
                return new ResponseEntity<>("bookname existed, pls try again",HttpStatus.OK);
            }
        }
        if (!bookOptional.getCode().equals(book.getCode())){
            return new ResponseEntity<>("code not valid", HttpStatus.OK);
        }
        bookOptional.setName(book.getName());
        bookOptional.setStatus(true);
        bookOptional.setAuthor(book.getAuthor());
        Optional<BookCategory> bookCategoryOptional = iBookCategoryService.findById(book.getCategory().getId());
        bookOptional.setCategory(bookCategoryOptional.get());
        return new ResponseEntity<>(iBookService.save(bookOptional),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id){
        Book bookOptional = iBookService.findById(id).get();
        if (iOrderService.findAllByBookAndDateOffNull(id).iterator().hasNext()){
            return new ResponseEntity<>("Book in borrowing, can't edit",HttpStatus.OK);
        }
        iBookService.remove(bookOptional.getId());
        return new ResponseEntity<>(iBookService.save(bookOptional),HttpStatus.OK);
    }
}
