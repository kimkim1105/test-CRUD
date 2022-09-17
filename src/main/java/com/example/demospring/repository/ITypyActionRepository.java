package com.example.demospring.repository;

import com.example.demospring.model.TypeAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITypyActionRepository extends JpaRepository<TypeAction, Long> {
}
