package com.elice.team6backspring.controller;

import com.elice.team6backspring.domain.Category;
import com.elice.team6backspring.domain.Recipe;
import com.elice.team6backspring.dto.RecipeRequest;
import com.elice.team6backspring.dto.RecipeResponse;
import com.elice.team6backspring.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@Slf4j
@Controller	// This means that this class is a Controller
@RequestMapping(path="/categories") // This means URL's start with /demo (after Application path)
public class CategoryController {
	@Autowired // This means to get the bean called userRepository
	private CategoryRepository categoryRepository;
	@CrossOrigin("*")
	@GetMapping
	public @ResponseBody ResponseEntity<Iterable<Category>> getAllRecipes() {
		// This returns a JSON or XML with the users
		List<Category> responses = categoryRepository.findAll();
		return ResponseEntity.ok(responses);
	}
	@CrossOrigin("*")
	@GetMapping(path="/{id}")
	public @ResponseBody ResponseEntity<Category> getCategory(@PathVariable("id") Integer id) {
		// This returns a JSON or XML with the users
		Category category = categoryRepository.findOneBycategoryId(id);
		log.info("##카테고리 단건조회 : {}",category);
		if(category==null){
			return new ResponseEntity<>(category, HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(categoryRepository.findOneBycategoryId(id));
	}
	@CrossOrigin("*")
	@PostMapping(path="/{name}")
	public @ResponseBody ResponseEntity<Category> addNewCategory (@PathVariable("name") String categoryName) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		Category category = new Category();
		category.setCategoryName(categoryName);
//		recipe.setRecipeId(recipeRepository.getNextValMySequence().intValue());
		Category newCategory = categoryRepository.save(category);
		log.info("###레시피 생성 : No{}.{}", newCategory.getCategoryId(), newCategory.getCategoryName());
		return ResponseEntity.ok(newCategory);
	}

	@CrossOrigin("*")
	@PatchMapping(path="/{id}/{name}")
	public @ResponseBody ResponseEntity<Category> editCategory (@PathVariable("id") Integer categoryId,@PathVariable("name") String categoryName) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request
		Category category = categoryRepository.findOneBycategoryId(categoryId);
		category.setCategoryName(categoryName);

		Category newCategory = categoryRepository.save(category);
		log.info("###레시피 수정 : No{}.{}", newCategory.getCategoryId(), newCategory.getCategoryName());
		return ResponseEntity.ok(newCategory);
	}

	@CrossOrigin("*")
	@DeleteMapping(path="/{id}")
	public @ResponseBody ResponseEntity<String> removeRecipe(@PathVariable("id") Integer categoryId) {
		// This returns a JSON or XML with the users
		categoryRepository.deleteById(categoryId);

		return ResponseEntity.ok("No."+categoryId+" is deleted");
	}
}
