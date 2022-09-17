package com.example.demospring.controller;

import com.example.demospring.service.IBookCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
public class BookCategoryController {
    @Autowired
    IBookCategoryService iBookCategoryService;
    @GetMapping
    public ResponseEntity<?> getListCategories(){
        return new ResponseEntity<>(iBookCategoryService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id){
        return new ResponseEntity<>(iBookCategoryService.findById(id),HttpStatus.OK);
    }
}
