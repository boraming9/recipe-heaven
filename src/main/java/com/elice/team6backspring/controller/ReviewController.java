package com.elice.team6backspring.controller;

import com.elice.team6backspring.domain.Recipe;
import com.elice.team6backspring.domain.Review;
import com.elice.team6backspring.dto.*;
import com.elice.team6backspring.repository.RecipeRepository;
import com.elice.team6backspring.repository.ReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@Slf4j
@Controller	// This means that this class is a Controller
@RequestMapping(path="/reviews") // This means URL's start with /demo (after Application path)
public class ReviewController {
	@Autowired // This means to get the bean called userRepository
	private ReviewRepository reviewRepository;
	@CrossOrigin("*")
	@GetMapping
	public @ResponseBody ResponseEntity<Iterable<ReviewResponse>> getAllRecipes() {
		// This returns a JSON or XML with the users
		List<ReviewResponse> responses = new ArrayList<>();
		reviewRepository.findAllWithoutDeletedDate().forEach(r->{
			responses.add(ReviewResponse.of(r));
		});
		return ResponseEntity.ok(responses);
	}
	@CrossOrigin("*")
	@GetMapping(path="/deletedReivews")
	public @ResponseBody ResponseEntity<Iterable<Review>> getAllDeletedRecipes() {
		// This returns a JSON or XML with the users
		return ResponseEntity.ok(reviewRepository.findAllDeletedRecipes());
	}
	@CrossOrigin("*")
	@GetMapping(path="/{id}")
	public @ResponseBody ResponseEntity<Review> getRecipe(@PathVariable("id") Integer id) {
		// This returns a JSON or XML with the users
		Review review = reviewRepository.findOneByReviewId(id);
		log.info("##리뷰 단건조회 : {}",review);
		if(review==null){
			return new ResponseEntity<>(review, HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(reviewRepository.findOneByReviewId(id));
	}
	@CrossOrigin("*")
	@PostMapping
	public @ResponseBody ResponseEntity<Review> addNewRecipe (@RequestBody ReviewRequest request) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		Review review = ReviewRequest.convertReview(request);
//		recipe.setRecipeId(recipeRepository.getNextValMySequence().intValue());
		Review newReview = reviewRepository.save(review);
		log.info("###리뷰 생성 : No{}.{}", newReview.getReviewId(), newReview.getRecipeId());
		return ResponseEntity.ok(newReview);
	}
	@CrossOrigin("*")
	@PatchMapping(path="/{id}")
	public @ResponseBody ResponseEntity<Review> patchReview (@PathVariable("id")  Integer id, @RequestBody ReviewRequest request) {

		Review review = reviewRepository.findOneByReviewId(id);
		Review updateReview = review.edit(request);

		updateReview = reviewRepository.save(updateReview);
		return ResponseEntity.ok(updateReview);
	}
	@CrossOrigin("*")
	@DeleteMapping(path="/{id}")
	public @ResponseBody ResponseEntity<ResultDto<Review>> removeReview(@PathVariable("id") Integer id) {
		// This returns a JSON or XML with the users
		Review review = reviewRepository.getReferenceById(id);
		int result = review.updateDeleteTime();

		if(result==0)
			review = reviewRepository.save(review);
		else return ResponseEntity.ok((ResultDto.res(HttpStatus.FORBIDDEN, "이미 삭제된 리뷰입니다.")));

		return ResponseEntity.ok((ResultDto.res(HttpStatus.OK, "리뷰가 삭제됐습니다.(deleteAt upated)", review)));
	}
}
