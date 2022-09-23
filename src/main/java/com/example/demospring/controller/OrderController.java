package com.example.demospring.controller;

import com.example.demospring.model.Book;
import com.example.demospring.model.Order;
import com.example.demospring.model.OrderDetail;
import com.example.demospring.model.dto.OrderDetailDTO;
import com.example.demospring.repository.IOrderDetailRepository;
import com.example.demospring.service.IBookService;
import com.example.demospring.service.IOrderDetailService;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    IOrderService iOrderService;
    @Autowired
    IBookService iBookService;
    @Autowired
    IPersonService iPersonService;
    @Autowired
    IOrderDetailService iOrderDetailService;

    @GetMapping
    public String getListOrder(@RequestParam(required = false, name = "key") String key, Model model, @PageableDefault(value = 5) Pageable pageable) {
        model.addAttribute("orders", iOrderService.findAllWithKey(key, pageable));
        return "order/orderList";
    }

    @GetMapping("/listOrder")
    public ResponseEntity<?> getOrderList(@RequestParam(required = false, name = "key") String key) {
        return new ResponseEntity<>(iOrderService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/addmore")
    public ResponseEntity<?> addMoreBook(@RequestBody @Valid OrderDetailDTO orderDetailDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();

            bindingResult.getFieldErrors().forEach(
                    error -> errors.put(error.getField(), error.getDefaultMessage())
            );
            String errorMsg = "";

            for (String key : errors.keySet()) {
                errorMsg += "error in: " + key + ", by: " + errors.get(key) + "\n";
            }
            return new ResponseEntity<>(errorMsg, HttpStatus.OK);
        }
        if (iOrderDetailService.findOrderDetaiByBookAndPerson(orderDetailDTO.getBook().getId(), orderDetailDTO.getOrder().getId()).isPresent()) {
            return new ResponseEntity<>("One person can borrow only one book at time", HttpStatus.OK);
        }
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setStatus(true);
        orderDetail.setOrder(iOrderService.findById(orderDetailDTO.getOrder().getId()).get());
        orderDetail.setBook(iBookService.findById(orderDetailDTO.getBook().getId()).get());
        orderDetail.setDateOn(LocalDate.now());
        iOrderDetailService.save(orderDetail);
        Order order = iOrderService.findById(orderDetailDTO.getOrder().getId()).get();
        order.setBookSize(order.getBookSize() + 1);
        iOrderService.save(order);
        Book book = iBookService.findById(orderDetailDTO.getBook().getId()).get();
        book.setInStock(book.getInStock() - 1);
        iBookService.save(book);
        return new ResponseEntity<>(iOrderDetailService.save(orderDetail), HttpStatus.OK);
    }
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getOrderById(@PathVariable Long id){
//        if (iOrderService.findById(id).isPresent()){
//            if (!iOrderService.findById(id).get().isStatus()){
//                return new ResponseEntity<>("Not found",HttpStatus.NOT_FOUND);
//            }
//            return new ResponseEntity<>(iOrderService.findById(id).get(),HttpStatus.OK);
//        }
//        return new ResponseEntity<>("Not found",HttpStatus.NOT_FOUND);
//    }
//    @PostMapping
//    public ResponseEntity<?> addNewOrder(@RequestBody @Valid OrderDTO orderDTO, BindingResult bindingResult){
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
//        Order order = new Order();
//        Person person = iPersonService.findById(orderDTO.getPerson().getId()).get();
//        if (!person.isStatus()){
//            return new ResponseEntity<>("person not valid", HttpStatus.OK);
//        }else if (person.isTypeAction()){
//            return new ResponseEntity<>("person in borrowing", HttpStatus.OK);
//        }
//        person.setTypeAction(true);
//        iPersonService.save(person);
//        Iterator<Book> bookIterator = orderDTO.getBook().iterator();
//        while (bookIterator.hasNext()){
//            Book book = iBookService.findById(bookIterator.next().getId()).get();
//            if (book.getInStock()<=0){
//                return new ResponseEntity<>("out of stock", HttpStatus.OK);
//            }
//            book.setInStock(book.getInStock()-1);
//            iBookService.save(book);
//        }
//        order.setBook(orderDTO.getBook());
//        order.setPerson(orderDTO.getPerson());
//        order.setDateOn(LocalDate.now());
//        order.setStatus(true);
//        return new ResponseEntity<>(iOrderService.save(order), HttpStatus.OK);
//    }
//    @PutMapping
//    public ResponseEntity<?> returnBook(@RequestBody Order order){
//        Order orderOptional = iOrderService.findById(order.getId()).get();
//        if (orderOptional.getDateOff()!=null){
//            return new ResponseEntity<>("order completed", HttpStatus.OK);
//        }
//        Person person = iPersonService.findById(orderOptional.getPerson().getId()).get();
//        person.setTypeAction(false);
//        iPersonService.save(person);
//        Iterator<Book> bookIterator = order.getBook().iterator();
//        while (bookIterator.hasNext()){
//            Book book = iBookService.findById(bookIterator.next().getId()).get();
//            book.setInStock(book.getInStock()+1);
//            iBookService.save(book);
//        }
//        orderOptional.setBook(order.getBook());
//        orderOptional.setPerson(order.getPerson());
//        orderOptional.setDateOff(LocalDate.now());
//        orderOptional.setStatus(true);
//        return new ResponseEntity<>(iOrderService.save(orderOptional), HttpStatus.OK);
//    }
//    @PutMapping("/{id}")
//    public ResponseEntity<?> deleteOrder(@PathVariable Long id){
//        Order orderOptional = iOrderService.findById(id).get();
//        if (orderOptional.getDateOff()!=null){
//            return new ResponseEntity<>("order completed", HttpStatus.OK);
//        }
//        Person person = iPersonService.findById(orderOptional.getPerson().getId()).get();
//        person.setTypeAction(false);
//        iPersonService.save(person);
//        Iterator<Book> bookIterator = orderOptional.getBook().iterator();
//        while (bookIterator.hasNext()){
//            Book book = iBookService.findById(bookIterator.next().getId()).get();
//            book.setInStock(book.getInStock()+1);
//            iBookService.save(book);
//        }
//        iOrderService.remove(orderOptional.getId());
//        return new ResponseEntity<>(iOrderService.save(orderOptional),HttpStatus.OK);
//    }
//    @GetMapping("/person/{id}")
//    public ResponseEntity<?> findAllByPersonAndDateOffNull(@PathVariable Long id){
//        if (iOrderService.findAllByPersonAndDateOffNull(id).iterator().hasNext()){
//            return new ResponseEntity<>("existed", HttpStatus.OK);
//        }
//        return new ResponseEntity<>("ok", HttpStatus.OK);
//    }
//    @GetMapping("/book/{id}")
//    public ResponseEntity<?> findAllByBookAndDateOffNull(@PathVariable Long id){
//        if (iOrderService.findAllByBookAndDateOffNull(id).iterator().hasNext()){
//            return new ResponseEntity<>("existed", HttpStatus.OK);
//        }
//        return new ResponseEntity<>("ok", HttpStatus.OK);
//    }
}
