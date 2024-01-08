package com.elice.team6backspring.repository;

import com.elice.team6backspring.domain.Recipe;
import com.elice.team6backspring.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ReviewRepository extends JpaRepository<Review, Integer> {

    Review findOneByReviewId(Integer reviewId);

    @Query(value = "SELECT r FROM Review r WHERE r.deletedAt is null")
    List<Review> findAllWithoutDeletedDate();

    @Query(value = "SELECT r FROM Review r WHERE r.deletedAt is not null")
    List<Review> findAllDeletedRecipes();

}