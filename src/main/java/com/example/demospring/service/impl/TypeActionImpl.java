package com.example.demospring.service.impl;

import com.example.demospring.model.TypeAction;
import com.example.demospring.repository.ITypyActionRepository;
import com.example.demospring.service.ITypeActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TypeActionImpl implements ITypeActionService {
    @Autowired
    ITypyActionRepository iTypyActionRepository;
    @Override
    public Iterable<TypeAction> findAll() {
        return iTypyActionRepository.findAll();
    }

    @Override
    public Optional<TypeAction> findById(Long id) {
        return iTypyActionRepository.findById(id);
    }

    @Override
    public TypeAction save(TypeAction typeAction) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
