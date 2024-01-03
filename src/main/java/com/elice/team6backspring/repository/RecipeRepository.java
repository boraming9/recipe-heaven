package com.elice.team6backspring.repository;

import com.elice.team6backspring.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

// CRUD refers Create, Read, Update, Delete

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    Recipe findOneByRecipeId(Integer recipeId);

}
