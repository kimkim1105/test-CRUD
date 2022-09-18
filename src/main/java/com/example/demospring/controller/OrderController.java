package com.example.demospring.controller;

import com.example.demospring.model.Order;
import com.example.demospring.model.dto.OrderShowDTO;
import com.example.demospring.service.IOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    IOrderService iOrderService;
    @GetMapping
    public String getListOrder(@RequestParam(required = false, name = "key") String key, Model model, @PageableDefault(value = 5) Pageable pageable){
        model.addAttribute("orders", iOrderService.findAllWithKey(key, pageable));
        return "order/orderList";
    }
    @PostMapping
    public ResponseEntity<?> addNewOrder(){
        return new ResponseEntity<>(iOrderService.save(new Order()), HttpStatus.OK);
    }
}
