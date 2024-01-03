package com.elice.team6backspring.repository;

import com.elice.team6backspring.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

// CRUD refers Create, Read, Update, Delete

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    Recipe findOneByRecipeId(Integer recipeId);

    @Query(value = "SELECT r FROM Recipe r WHERE r.deletedAt is null")
    List<Recipe> findAllWithoutDeletedDate();

    @Query(value = "SELECT r FROM Recipe r WHERE r.deletedAt is not null")
    List<Recipe> findAllDeletedRecipes();

}