package com.elice.team6backspring.controller;

import com.elice.team6backspring.domain.Recipe;
import com.elice.team6backspring.dto.RecipeRequest;
import com.elice.team6backspring.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

@Slf4j
@Controller	// This means that this class is a Controller
@RequestMapping(path="/recipes") // This means URL's start with /demo (after Application path)
public class RecipeController {
	@Autowired // This means to get the bean called userRepository
	private RecipeRepository recipeRepository;

	@GetMapping
	public @ResponseBody ResponseEntity<Iterable<Recipe>> getAllRecipes() {
		// This returns a JSON or XML with the users
		return ResponseEntity.ok(recipeRepository.findAllWithoutDeletedDate());
	}

	@GetMapping(path="/deletedRecipes")
	public @ResponseBody ResponseEntity<Iterable<Recipe>> getAllDeletedRecipes() {
		// This returns a JSON or XML with the users
		return ResponseEntity.ok(recipeRepository.findAllDeletedRecipes());
	}

	@GetMapping(path="/{id}")
	public @ResponseBody ResponseEntity<Recipe> getRecipe(@PathVariable("id") Integer recipeId) {
		// This returns a JSON or XML with the users
		Recipe recipe = recipeRepository.findOneByRecipeId(recipeId);
		log.info("##레시피 단건조회 : {}",recipe);
		if(recipe==null){
			return new ResponseEntity<>(recipe, HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(recipeRepository.findOneByRecipeId(recipeId));
	}

	@PostMapping
	public @ResponseBody ResponseEntity<Recipe> addNewRecipe (@RequestBody RecipeRequest request) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		Recipe recipe = request.convertRecipe();
//		recipe.setRecipeId(recipeRepository.getNextValMySequence().intValue());
		Recipe newRecipe = recipeRepository.save(recipe);
		log.info("###레시피 생성 : No{}.{}", newRecipe.getRecipeId(), newRecipe.getRecipeName());
		return ResponseEntity.ok(newRecipe);
	}

	@PatchMapping(path="/{id}")
	public @ResponseBody ResponseEntity<String> patchRecipe (@PathVariable("id")  Integer recipeId, @RequestBody RecipeRequest request) {

		Recipe recipe = recipeRepository.findOneByRecipeId(recipeId);
		Recipe updateRecipe = recipe.of(request);

		recipeRepository.save(updateRecipe);
		return ResponseEntity.ok("No."+recipe.getRecipeId()+" "+recipe.getRecipeName()+" is edited");
	}

	@DeleteMapping(path="/{id}")
	public @ResponseBody ResponseEntity<String> removeRecipe(@PathVariable("id") Integer recipeId) {
		// This returns a JSON or XML with the users
		Recipe recipe = recipeRepository.getReferenceById(recipeId);
		//		recipeRepository.deleteById(recipeId);

		int result = recipe.updateDeleteTime();

		if(result==0)
			recipeRepository.save(recipe);
		else return new ResponseEntity<>(("Already No."+recipe.getRecipeId()+" "+recipe.getRecipeName()+" was deleted"), HttpStatus.BAD_REQUEST);

		return ResponseEntity.ok("No."+recipe.getRecipeId()+" "+recipe.getRecipeName()+" is updated DeletedAt field with nowDate");
	}
}
