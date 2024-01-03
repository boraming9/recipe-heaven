package com.elice.team6backspring.repository;

import com.elice.team6backspring.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.math.BigInteger;

// CRUD refers Create, Read, Update, Delete

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    Recipe findOneByRecipeId(Integer recipeId);

}
