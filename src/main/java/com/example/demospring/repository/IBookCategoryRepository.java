package com.example.demospring.repository;

import com.example.demospring.model.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookCategoryRepository extends JpaRepository<BookCategory,Long> {
}
