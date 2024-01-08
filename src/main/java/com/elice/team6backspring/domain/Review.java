package com.elice.team6backspring.domain;

import com.elice.team6backspring.dto.ReviewRequest;
import com.elice.team6backspring.dto.ReviewResponse;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity // This tells Hibernate to make a table out of this class
@Getter
@Setter
public class Review {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="reviewid")
	private Integer reviewId;
	@Column(name="star")
	private Integer star;

	@Column(name="comment")
	private String comment;

	@Column(name = "createdat")
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updatedat")
	private LocalDateTime updatedAt;

	@Column(name = "deletedat")
	private LocalDateTime deletedAt;

	@Column(name="userid")
	private Integer userId;

	@Column(name="reciperecipeid")
	private Integer recipeId;

	public int updateDeleteTime() {
		if (deletedAt == null) {
			this.deletedAt=LocalDateTime.now();
			return 0;
		}
		return 1;
	}

	public Review edit(ReviewRequest r){

		this.setStar(r.getStar());
		this.setComment(r.getComment());

		return this;
	}
	public static ReviewResponse of(Review r){

		ReviewResponse review = new ReviewResponse();
		review.setReviewId(r.getReviewId());
		review.setStar(r.getStar());
		review.setComment(r.getComment());
		review.setCreatedAt(r.getCreatedAt());
		review.setUpdatedAt(r.getUpdatedAt());
		review.setUserId(r.getUserId());
		review.setRecipeId(r.getRecipeId());

		return review;
	}

}
