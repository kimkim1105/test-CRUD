package com.example.demospring.repository;

import com.example.demospring.model.Classify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClassifyRepository extends JpaRepository<Classify, Long> {
}
