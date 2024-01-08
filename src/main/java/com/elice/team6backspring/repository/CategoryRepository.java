package com.elice.team6backspring.repository;

import com.elice.team6backspring.domain.Category;
import com.elice.team6backspring.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category findOneBycategoryId(Integer categoryId);
}