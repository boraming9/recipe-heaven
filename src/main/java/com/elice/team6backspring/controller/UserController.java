package com.elice.team6backspring.controller;

import com.elice.team6backspring.domain.Recipe;
import com.elice.team6backspring.domain.User;
import com.elice.team6backspring.dto.RecipeRequest;
import com.elice.team6backspring.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@Slf4j
@Controller	// This means that this class is a Controller
@RequestMapping(path="/auth") // This means URL's start with /demo (after Application path)
public class UserController {
	@Autowired // This means to get the bean called userRepository
	private UserRepository userRepository;

	@CrossOrigin("*")
	@GetMapping(path="/{id}")
	public @ResponseBody ResponseEntity<User> getUser(@PathVariable("id") Integer id) {
		// This returns a JSON or XML with the users
//		log.info("##User 단건조회 : {}", userRepository.findOneById(id));
		return ResponseEntity.ok(userRepository.findOneById(id));
	}

	//전달받은 인가코드를 필드에 upsert 하기, PK가 안정해졌으니 로직고민
//	@PostMapping("/signin")
//	public @ResponseBody User addNewUser (@RequestBody RecipeRequest request) {
//		// @ResponseBody means the returned String is the response, not a view name
//		// @RequestParam means it is a parameter from the GET or POST request
//
//		Recipe recipe = request.convertRecipe();
////		recipe.setRecipeId(recipeRepository.getNextValMySequence().intValue());
//		Recipe newRecipe = userRepository.save(recipe);
//		log.info("###레시피 생성 : No{}.{}", newRecipe.getRecipeId(), newRecipe.getRecipeName());
//		return newRecipe;
//	}

	@CrossOrigin("*")
	@PatchMapping(path="/{id}/nickname/{nickname}")
	public @ResponseBody ResponseEntity<String> editNickname (@PathVariable("id")  Integer id, @PathVariable("nickname")  String nickname) {

		User user = userRepository.findOneById(id);
		String oldNickname = user.getNickname();
		user.setNickname(nickname);

		userRepository.save(user);
		return ResponseEntity.ok(oldNickname+" -> "+nickname+" 닉네임이 변경됐습니다.");
	}
	@CrossOrigin("*")
	@DeleteMapping(path="/{id}")
	public @ResponseBody ResponseEntity<String> removeUser(@PathVariable("id") Integer id) {
		// This returns a JSON or XML with the users
		User user = userRepository.findOneById(id);
		userRepository.deleteById(id);

		return ResponseEntity.ok("User"+user.getId()+" is removed");
	}
}
