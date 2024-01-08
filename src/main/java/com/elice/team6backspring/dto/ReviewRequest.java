package com.elice.team6backspring.dto;

import com.elice.team6backspring.domain.Review;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReviewRequest {

//    private Integer reviewId;
    private Integer star;

    private String comment;

    private Integer userId;

    private Integer recipeId;


    public static Review convertReview(ReviewRequest r){

        Review review = new Review();
        review.setStar(r.getStar());
        review.setComment(r.getComment());
        review.setCreatedAt(LocalDateTime.now());
        review.setUserId(r.getUserId());
        review.setRecipeId(r.getRecipeId());

        return review;
    }

}
