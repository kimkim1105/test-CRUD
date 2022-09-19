package com.example.demospring.controller;

import com.example.demospring.model.Book;
import com.example.demospring.model.Order;
import com.example.demospring.model.Person;
import com.example.demospring.model.dto.OrderDTO;
import com.example.demospring.service.IBookService;
import com.example.demospring.service.IOrderService;
import com.example.demospring.service.IPersonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Iterator;


@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    IOrderService iOrderService;
    @Autowired
    IBookService iBookService;
    @Autowired
    IPersonService iPersonService;
    @GetMapping
    public String getListOrder(@RequestParam(required = false, name = "key") String key, Model model, @PageableDefault(value = 5) Pageable pageable){
        model.addAttribute("orders", iOrderService.findAllWithKey(key, pageable));
        return "order/orderList";
    }
    @GetMapping("/listOrder")
    public ResponseEntity<?> getOrderList(){
        return new ResponseEntity<>(iOrderService.findAll(),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id){
        if (iOrderService.findById(id).isPresent()){
            return new ResponseEntity<>(iOrderService.findById(id).get(),HttpStatus.OK);
        }
        return new ResponseEntity<>("Not found",HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<?> addNewOrder(@RequestBody OrderDTO orderDTO){
        Order order = new Order();
        Person person = iPersonService.findById(orderDTO.getPerson().getId()).get();
        if (!person.isStatus()){
            return new ResponseEntity<>("person not valid", HttpStatus.OK);
        }else if (person.isTypeAction()){
            return new ResponseEntity<>("person in borrowing", HttpStatus.OK);
        }
        person.setTypeAction(true);
        iPersonService.save(person);
        Iterator<Book> bookIterator = orderDTO.getBook().iterator();
        while (bookIterator.hasNext()){
            Book book = iBookService.findById(bookIterator.next().getId()).get();
            if (book.getInStock()<=0){
                return new ResponseEntity<>("out of stock", HttpStatus.OK);
            }
            book.setInStock(book.getInStock()-1);
            iBookService.save(book);
        }
        order.setBook(orderDTO.getBook());
        order.setPerson(orderDTO.getPerson());
        order.setDateOn(LocalDate.now());
        order.setStatus(true);
        return new ResponseEntity<>(iOrderService.save(order), HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<?> returnBook(@RequestBody Order order){
        Order orderOptional = iOrderService.findById(order.getId()).get();
        if (orderOptional.getDateOff()!=null){
            return new ResponseEntity<>("order completed", HttpStatus.OK);
        }
        Person person = iPersonService.findById(orderOptional.getPerson().getId()).get();
        person.setTypeAction(false);
        iPersonService.save(person);
        Iterator<Book> bookIterator = order.getBook().iterator();
        while (bookIterator.hasNext()){
            Book book = iBookService.findById(bookIterator.next().getId()).get();
            book.setInStock(book.getInStock()+1);
            iBookService.save(book);
        }
        orderOptional.setBook(order.getBook());
        orderOptional.setPerson(order.getPerson());
        orderOptional.setDateOff(LocalDate.now());
        orderOptional.setStatus(true);
        return new ResponseEntity<>(iOrderService.save(orderOptional), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id){
        Order orderOptional = iOrderService.findById(id).get();
        iOrderService.remove(orderOptional.getId());
        return new ResponseEntity<>(iOrderService.save(orderOptional),HttpStatus.OK);
    }
}
