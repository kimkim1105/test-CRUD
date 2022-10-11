package com.example.demospring.controller;

import com.example.demospring.model.Book;
import com.example.demospring.model.BookCategory;
import com.example.demospring.model.Person;
import com.example.demospring.model.dto.BookDTO;
import com.example.demospring.model.dto.Validation;
import com.example.demospring.model.view.BookHistory;
import com.example.demospring.model.view.PersonHistory;
import com.example.demospring.repository.IBookHistoryDetailRepository;
import com.example.demospring.repository.IBookHistoryRepository;
import com.example.demospring.repository.IBookRepository;
import com.example.demospring.service.IBookCategoryService;
import com.example.demospring.service.IBookService;
import com.example.demospring.service.IOrderService;
import org.hibernate.annotations.NaturalId;
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
import java.util.Iterator;
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
    @Autowired
    IBookRepository bookRepository;
    @Autowired
    IBookHistoryRepository bookHistoryRepository;
    @Autowired
    IBookHistoryDetailRepository iBookHistoryDetailRepository;
    @GetMapping("/book-history")
    public String getListBookHistory(@RequestParam(required = false, name = "key") String key, Model model,
                                       @PageableDefault(value = 5) Pageable pageable){
        if (key==null){
            key="";
        }
        model.addAttribute("key", key);
        bookHistoryRepository.paramSetKeyBook('%'+key+'%');
        model.addAttribute("books", iBookService.findAllPage(pageable));
        return "book/book-history";
    }
    @GetMapping("/book-history-list")
    public ResponseEntity<?> getListHistoryBook(@RequestParam(required = false) String q){
        if (q==null){
            q="";
        }
        bookHistoryRepository.paramSetKeyBook('%'+q+'%');
        Iterable<BookHistory> bookHistories = bookHistoryRepository.findAll();
        return new ResponseEntity<>(bookHistoryRepository.findAll(),HttpStatus.OK);
    }
    @GetMapping("/book-history-detail/")
    public String getListBookHistoryDetail(@RequestParam Long id, @RequestParam(required = false, name = "key") String key,
                                             Model model,
                                             @PageableDefault(value = 5) Pageable pageable){
        if (key==null){
            key="";
        }
        model.addAttribute("key", key);
        model.addAttribute("id", id);
        iBookHistoryDetailRepository.paramSetKeyPersonBorrowBook('%'+key+'%');
        iBookHistoryDetailRepository.paramSetBookId(id);
        model.addAttribute("book", iBookService.findById(id).get());
        model.addAttribute("books", iBookService.findAllPageDetail(pageable));
        return "book/book-history-detail";
    }
//    @PostMapping("/addnew")
//    public ResponseEntity<?> testAddNew(@RequestBody BookDTO bookDTO){
////        System.out.println(bookDTO.toString());
//    String rs = bookRepository.addNewBook(bookDTO.getAuthor(),bookDTO.getCode(),bookDTO.getInStock(),bookDTO.getName(),bookDTO.getCategory().getId());
//    if (rs.equalsIgnoreCase("sucess")){
//        return new ResponseEntity<>(iBookService.findBookByCode(bookDTO.getCode()),HttpStatus.OK);
//    }
//        return new ResponseEntity<>(rs,HttpStatus.OK);
//    }
    @GetMapping
    public String getListBook(@RequestParam(required = false, name = "key") String key,
                              @RequestParam(required = false, name = "category_id") String category_id,
                              Model model,
                              @PageableDefault(value = 5) Pageable pageable){
        if (key==null){
            key="";
        }
        if (category_id==null||category_id.equals("0")){
            category_id="0";
        }
        model.addAttribute("categories", iBookCategoryService.findAll());
        model.addAttribute("key", key);
        model.addAttribute("category_id", category_id);
        model.addAttribute("books", iBookService.findAllWithKeyAndCategory(key, category_id, pageable));
        return "book/bookList";
//        return new ResponseEntity<>(iBookService.findAllWithKeyAndCategory(key, category_id, pageable),HttpStatus.OK);
    }
    @GetMapping("/listBook")
    public ResponseEntity<?> getListBookWithKey(@RequestParam(required = false, name = "key") String key,
                                                @RequestParam(required = false, name = "category_id") String category_id){
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
        Iterable<Book> books = bookRepository.findAllByName(name);
        Iterator<Book> bookIterator = books.iterator();
        if (books.iterator().hasNext()){
            while (bookIterator.hasNext()){
                if (bookIterator.next().getName().equals(name)&&bookIterator.next().getName().compareTo(name)==0){
                    return new ResponseEntity<>("existed", HttpStatus.OK);
                }
            }
        }
//        if (iBookService.findBookByName(name).isPresent()){
//            return new ResponseEntity<>("existed", HttpStatus.OK);
//        }
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

//    @PostMapping
//    public ResponseEntity<?> addNewBook(@RequestBody @Valid BookDTO bookDTO, BindingResult bindingResult){
//        if (bindingResult.hasErrors()){
//            Map<String, String> errors= new HashMap<>();
//
//            bindingResult.getFieldErrors().forEach(
//                    error -> errors.put(error.getField(), error.getDefaultMessage())
//            );
//            String errorMsg= "";
//
//            for(String key: errors.keySet()){
//                errorMsg+= "error in: " + key + ", by: " + errors.get(key) + "\n";
//            }
//            return new ResponseEntity<>(errorMsg,HttpStatus.OK);
//        }
////        if (iBookService.findBookByName(bookDTO.getName()).isPresent()){
////            return  new ResponseEntity<>("bookname existed", HttpStatus.OK);
////        }
//        Iterable<Book> books = bookRepository.findAllByName(bookDTO.getName());
//        Iterator<Book> bookIterator = books.iterator();
////        if (books.iterator().hasNext()){
////            while (bookIterator.hasNext()){
////                if (bookIterator.next().getName().equals(bookDTO.getName())&&bookIterator.next().getName().compareTo(bookDTO.getName())==0){
////                    return new ResponseEntity<>("bookname existed", HttpStatus.OK);
////                }
////            }
////        }
//        if (iBookService.findBookByCode(bookDTO.getCode()).isPresent()){
//            return  new ResponseEntity<>("bookcode existed", HttpStatus.OK);
//        }
//        Book book = new Book();
//        book.setName(bookDTO.getName());
//        book.setStatus(true);
//        book.setCode(bookDTO.getCode());
//        book.setAuthor(bookDTO.getAuthor());
//        Optional<BookCategory> bookCategoryOptional = iBookCategoryService.findById(bookDTO.getCategory().getId());
//        book.setCategory(bookCategoryOptional.get());
//        book.setInStock(Integer.valueOf(bookDTO.getInStock()));
//        return new ResponseEntity<>(iBookService.save(book),HttpStatus.OK);
//    }

    @PostMapping
    public ResponseEntity<?> addNewBook(@RequestBody BookDTO bookDTO){
        String rs = iBookService.addNewBook(bookDTO);
        if (rs.equalsIgnoreCase("success")){
            return new ResponseEntity<>(iBookService.findBookByCode(bookDTO.getCode()),HttpStatus.OK);
        }
        return new ResponseEntity<>(rs,HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> editBook(@RequestParam Long id,@RequestBody BookDTO bookDTO){
        String rs = iBookService.updateBook(id,bookDTO);
        if (rs.equalsIgnoreCase("success")){
            return new ResponseEntity<>(iBookService.findById(id),HttpStatus.OK);
        }
        return new ResponseEntity<>(rs,HttpStatus.OK);
    }

//    @PutMapping
//    public ResponseEntity<?> editBook(@RequestParam Long id,@RequestBody @Valid BookDTO book, BindingResult bindingResult){
//        if (!iBookService.findById(id).isPresent()||!iBookService.findById(id).get().isStatus()){
//            return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
//        }
//        Book bookOptional = iBookService.findById(id).get();
//        if (iOrderService.findAllByBookAndDateOffNull(id).iterator().hasNext()){
//            return new ResponseEntity<>("Book in borrowing, can't edit",HttpStatus.OK);
//        }
//        if (bindingResult.hasErrors()){
//            Map<String, String> errors= new HashMap<>();
//
//            bindingResult.getFieldErrors().forEach(
//                    error -> errors.put(error.getField(), error.getDefaultMessage())
//            );
//            String errorMsg= "";
//
//            for(String key: errors.keySet()){
//                errorMsg+= "error in: " + key + ", by: " + errors.get(key) + "\n";
//            }
//            return new ResponseEntity<>(errorMsg,HttpStatus.OK);
//        }
//        if (bookOptional.getName().equals(book.getName())){
//            bookOptional.setName(book.getName());
//        }else {
//            if (iBookService.findBookByName(book.getName()).isPresent()){
//                return new ResponseEntity<>("bookname existed",HttpStatus.OK);
//            }
//        }
//        if (!bookOptional.getCode().equals(book.getCode())){
//            return new ResponseEntity<>("code not valid", HttpStatus.OK);
//        }
//        bookOptional.setName(book.getName());
//        bookOptional.setStatus(true);
//        bookOptional.setAuthor(book.getAuthor());
//        Optional<BookCategory> bookCategoryOptional = iBookCategoryService.findById(book.getCategory().getId());
//        bookOptional.setCategory(bookCategoryOptional.get());
//        return new ResponseEntity<>(iBookService.save(bookOptional),HttpStatus.OK);
//    }

//    @PutMapping("/{id}")
//    public ResponseEntity<?> deleteBook(@PathVariable Long id){
//        Book bookOptional = iBookService.findById(id).get();
//        if (iOrderService.findAllByBookAndDateOffNull(id).iterator().hasNext()){
//            return new ResponseEntity<>("Book in borrowing, can't edit",HttpStatus.OK);
//        }
//        iBookService.remove(bookOptional.getId());
//        return new ResponseEntity<>(iBookService.save(bookOptional),HttpStatus.OK);
//    }
    @PutMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id){
        String rs = iBookService.deleteBook(id);
        if (rs.equalsIgnoreCase("success")){
            return new ResponseEntity<>(iBookService.findById(id),HttpStatus.OK);
        }
        return new ResponseEntity<>(rs,HttpStatus.OK);
    }

}
