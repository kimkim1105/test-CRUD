package com.example.demospring.controller;

import com.example.demospring.model.Book;
import com.example.demospring.model.Order;
import com.example.demospring.model.OrderDetail;
import com.example.demospring.model.dto.OrderDetailDTO;
import com.example.demospring.service.IBookService;
import com.example.demospring.service.IOrderDetailService;
import com.example.demospring.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller
@RequestMapping("/orderdetails")
public class OrderDetailController {
    @Autowired
    IOrderDetailService iOrderDetailService;
    @Autowired
    IBookService iBookService;
    @Autowired
    IOrderService iOrderService;
    @GetMapping("/borrowing/{id}")
    public ResponseEntity<?> getListBorrowingByPerson(@PathVariable Long id){
        if (!iOrderDetailService.findAllInBorrowing(id).iterator().hasNext()){
            return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(iOrderDetailService.findAllInBorrowing(id),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public  ResponseEntity<?> findOrderDetailById(@PathVariable Long id){
        if (!iOrderDetailService.findById(id).isPresent()||!iOrderDetailService.findById(id).get().isStatus()){
            return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(iOrderDetailService.findById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> addMoreBook(@RequestBody OrderDetailDTO orderDetailDTO) {
        String rs = iOrderDetailService.addNewOrderDetail(orderDetailDTO);
        if (rs.equalsIgnoreCase("success")){
            return new ResponseEntity<>(iOrderDetailService.getLastestOrderDetail(),HttpStatus.OK);
        }
        return new ResponseEntity<>(rs,HttpStatus.OK);
    }

//    @PostMapping("")
//    public ResponseEntity<?> addMoreBook(@RequestBody @Valid OrderDetailDTO orderDetailDTO, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            Map<String, String> errors = new HashMap<>();
//
//            bindingResult.getFieldErrors().forEach(
//                    error -> errors.put(error.getField(), error.getDefaultMessage())
//            );
//            String errorMsg = "";
//
//            for (String key : errors.keySet()) {
//                errorMsg += "error in: " + key + ", by: " + errors.get(key) + "\n";
//            }
//            return new ResponseEntity<>(errorMsg, HttpStatus.OK);
//        }
//        if (iOrderDetailService.findOrderDetaiByBookAndPerson(orderDetailDTO.getBook().getId(), orderDetailDTO.getOrder().getId()).isPresent()) {
//            return new ResponseEntity<>("Add false  "+iBookService.findById(orderDetailDTO.getBook().getId()).get().getName()+": One person can borrow only one book at time", HttpStatus.OK);
//        }
//        OrderDetail orderDetail = new OrderDetail();
//        orderDetail.setStatus(true);
//        orderDetail.setOrder(iOrderService.findById(orderDetailDTO.getOrder().getId()).get());
//        orderDetail.setBook(iBookService.findById(orderDetailDTO.getBook().getId()).get());
//        orderDetail.setDateOn(LocalDate.now());
//        iOrderDetailService.save(orderDetail);
//        Order order = iOrderService.findById(orderDetailDTO.getOrder().getId()).get();
//        order.setBookSize(order.getBookSize() + 1);
//        iOrderService.save(order);
//        Book book = iBookService.findById(orderDetailDTO.getBook().getId()).get();
//        book.setInStock(book.getInStock() - 1);
//        iBookService.save(book);
//        return new ResponseEntity<>(iOrderDetailService.save(orderDetail), HttpStatus.OK);
//    }

    @PutMapping("/returnbook/{id}")
    public ResponseEntity<?> returnBook(@PathVariable Long id){
        String rs = iOrderDetailService.returnOrderDetail(id);
        if (rs.equalsIgnoreCase("success")){
            return new ResponseEntity<>(iOrderDetailService.findById(id),HttpStatus.OK);
        }
        return new ResponseEntity<>(rs,HttpStatus.OK);
    }

//    @PutMapping("/returnbook/{id}")
//    public ResponseEntity<?> returnBook(@PathVariable Long id){
//        if (!iOrderDetailService.findById(id).isPresent()||!iOrderDetailService.findById(id).get().isStatus()){
//            return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
//        }
//        OrderDetail orderDetail = iOrderDetailService.findById(id).get();
//        if (orderDetail.getDateOff()!=null){
//            return new ResponseEntity<>("book is returned", HttpStatus.OK);
//        }
//        orderDetail.setDateOff(LocalDate.now());
//        Book book = orderDetail.getBook();
//        book.setInStock(book.getInStock()+1);
//        iBookService.save(book);
//        Order order = orderDetail.getOrder();
//        order.setBookSize(order.getBookSize()-1);
//        order.setBookReturn(order.getBookReturn()+1);
//        iOrderService.save(order);
//        orderDetail.setBook(book);
//        orderDetail.setOrder(order);
//        iOrderDetailService.save(orderDetail);
//        return new ResponseEntity<>(iOrderDetailService.save(orderDetail), HttpStatus.OK);
//    }

    @PutMapping("/deleteOrderdetail/{id}")
    public ResponseEntity<?> deleteOrderdetail(@PathVariable Long id){
        String rs = iOrderDetailService.deleteOrderDetail(id);
        if (rs.equalsIgnoreCase("success")){
            return new ResponseEntity<>(iOrderDetailService.findById(id),HttpStatus.OK);
        }
        return new ResponseEntity<>(rs,HttpStatus.OK);
    }

//    @PutMapping("/deleteOrderdetail/{id}")
//    public ResponseEntity<?> deleteOrderdetail(@PathVariable Long id){
//        if (!iOrderDetailService.findById(id).isPresent()||!iOrderDetailService.findById(id).get().isStatus()){
//            return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
//        }
//        OrderDetail orderDetail = iOrderDetailService.findById(id).get();
//        if (orderDetail.getDateOff()!=null){
//            return new ResponseEntity<>("book is returned, can't delete", HttpStatus.OK);
//        }
//        orderDetail.setStatus(false);
//        Book book = orderDetail.getBook();
//        book.setInStock(book.getInStock()+1);
//        iBookService.save(book);
//        Order order = orderDetail.getOrder();
//        order.setBookSize(order.getBookSize()-1);
//        iOrderService.save(order);
//        orderDetail.setBook(book);
//        orderDetail.setOrder(order);
//        iOrderDetailService.save(orderDetail);
//        return new ResponseEntity<>(iOrderDetailService.save(orderDetail), HttpStatus.OK);
//    }

    @PutMapping("return-all/{orderId}")
    public ResponseEntity<?> returnAllBook(@PathVariable Long orderId){
        if (!iOrderDetailService.findAllInBorrowing(orderId).iterator().hasNext()){
            return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
        }
        Iterator<OrderDetail> orderDetailIterator = iOrderDetailService.findAllInBorrowing(orderId).iterator();
        while (orderDetailIterator.hasNext()){
            Long id = orderDetailIterator.next().getId();
            if (!iOrderDetailService.findById(id).isPresent()||!iOrderDetailService.findById(id).get().isStatus()){
                return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
            }
            OrderDetail orderDetail = iOrderDetailService.findById(id).get();
            if (orderDetail.getDateOff()!=null){
                return new ResponseEntity<>("book is returned", HttpStatus.OK);
            }
            orderDetail.setDateOff(LocalDate.now());
            Book book = orderDetail.getBook();
            book.setInStock(book.getInStock()+1);
            iBookService.save(book);
            Order order = orderDetail.getOrder();
            order.setBookSize(order.getBookSize()-1);
            iOrderService.save(order);
            orderDetail.setBook(book);
            orderDetail.setOrder(order);
            iOrderDetailService.save(orderDetail);
        }
        return  new ResponseEntity<>("ok", HttpStatus.OK);
    }

}
