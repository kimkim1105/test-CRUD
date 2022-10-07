package com.example.demospring.controller;

import com.example.demospring.model.view.Top5Book;
import com.example.demospring.repository.ITop5BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatisticCotroller {
    @Autowired
    ITop5BookRepository iTop5BookRepository;

    @GetMapping("")
    public String showStatistic(Model model){
        model.addAttribute("top5", iTop5BookRepository.findAll());
        model.addAttribute("totalBook", iTop5BookRepository.getTotalBook());
        model.addAttribute("totalPerson", iTop5BookRepository.getTotalPerson());
        model.addAttribute("borrowingBook", iTop5BookRepository.getBorrowingBook());
        model.addAttribute("borrowingPerson", iTop5BookRepository.getBorrowingPerson());
        model.addAttribute("borrowingOverDate", iTop5BookRepository.getBorrowingOverDate());
        return "index";
    }
}
