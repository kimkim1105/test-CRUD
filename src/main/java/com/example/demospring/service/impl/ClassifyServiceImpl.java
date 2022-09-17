package com.example.demospring.service.impl;

import com.example.demospring.model.Classify;
import com.example.demospring.repository.IClassifyRepository;
import com.example.demospring.service.IClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClassifyServiceImpl implements IClassifyService {
    @Autowired
    IClassifyRepository iClassifyRepository;

    @Override
    public Iterable<Classify> findAll() {
        return iClassifyRepository.findAll();
    }

    @Override
    public Optional<Classify> findById(Long id) {
        return iClassifyRepository.findById(id);
    }

    @Override
    public Classify save(Classify classify) {
        return iClassifyRepository.save(classify);
    }

    @Override
    public void remove(Long id) {
        iClassifyRepository.deleteById(id);
    }
}
