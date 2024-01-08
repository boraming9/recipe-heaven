package com.elice.team6backspring.dto;

import com.elice.team6backspring.domain.Recipe;
import com.elice.team6backspring.domain.Review;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ReviewResponse {

    private Integer reviewId;
    private Integer star;

    private String comment;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;
    private Integer userId;

    private Integer recipeId;


    public static ReviewResponse of(Review r){

        ReviewResponse response = new ReviewResponse();
        response.reviewId = r.getReviewId();
        response.star = r.getStar();
        response.comment = r.getComment();
        response.createdAt = r.getCreatedAt();
        response.updatedAt = r.getUpdatedAt();
        response.userId = r.getUserId();
        response.recipeId = r.getRecipeId();

        return response;
    }

}
