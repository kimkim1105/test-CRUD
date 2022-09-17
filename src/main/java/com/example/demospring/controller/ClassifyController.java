package com.example.demospring.controller;

import com.example.demospring.service.IClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/classifies")
public class ClassifyController {
    @Autowired
    IClassifyService iClassifyService;

    @GetMapping
    public ResponseEntity<?> getListClassify(){
        return new ResponseEntity<>(iClassifyService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getClassify(@PathVariable Long id){
        return new ResponseEntity<>(iClassifyService.findById(id), HttpStatus.OK);
    }

}
