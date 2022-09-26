package com.example.demospring.controller;

import com.example.demospring.model.Book;
import com.example.demospring.model.Order;
import com.example.demospring.model.OrderDetail;
import com.example.demospring.model.Person;
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

    @GetMapping("/history")
    public String getListOrderHistory(@RequestParam(required = false, name = "key") String key, Model model, @PageableDefault(value = 5) Pageable pageable) {
        model.addAttribute("key", key);
        model.addAttribute("ordersHistory", iOrderService.findAllHistoryWithKey(key, pageable));
        return "order/history";
    }

    @GetMapping("/history-detail/")
    public String getListOrderHistoryDetail(@RequestParam(required = false, name = "key") String key,
                                            @RequestParam(required = false) Long id,
                                            Model model, @PageableDefault(value = 5) Pageable pageable) {
        model.addAttribute("id", id);
        model.addAttribute("person", iOrderService.findById(id).get().getPerson());
        model.addAttribute("ordersDetail", iOrderDetailService.findAllByOrderCompleted(id, pageable));
        return "order/history-detail";
    }

    @GetMapping("/listOrder")
    public ResponseEntity<?> getOrderList(@RequestParam(required = false, name = "key") String key) {
        return new ResponseEntity<>(iOrderService.findAll(), HttpStatus.OK);
    }


    @GetMapping("/person/{id}")
    public ResponseEntity<?> getOrderByPerson(@PathVariable Long id) {
        if (!iPersonService.findById(id).isPresent()) {
            return new ResponseEntity<>("person not found", HttpStatus.NOT_FOUND);
        }
        Person person = iPersonService.findById(id).get();
        if (!iOrderService.findOrderByPerson(person).isPresent()) {
            return new ResponseEntity<>("order not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(iOrderService.findOrderByPerson(person), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id) {
        if (!iOrderService.findById(id).isPresent()) {
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(iOrderService.findById(id).get(), HttpStatus.OK);
    }

}
