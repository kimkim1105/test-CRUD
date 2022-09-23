package com.example.demospring.controller;

import com.example.demospring.service.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orderdetails")
public class OrderDetailController {
    @Autowired
    IOrderDetailService iOrderDetailService;
    @GetMapping("/borrowing/{id}")
    public ResponseEntity<?> getListBorrowingByPerson(@PathVariable String id){
        if (!iOrderDetailService.findAllInBorrowing(id).iterator().hasNext()){
            return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(iOrderDetailService.findAllInBorrowing(id),HttpStatus.OK);
    }
}
