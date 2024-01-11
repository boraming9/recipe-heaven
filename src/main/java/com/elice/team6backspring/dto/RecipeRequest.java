package com.elice.team6backspring.dto;

import com.elice.team6backspring.domain.Recipe;
import jakarta.persistence.Column;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class RecipeRequest {

//    private String recipeId;
    private String recipeName;
    private String img;
    private Integer portion;

    private Integer leadTime;
    private Integer level;

    private List<Object> ingredient;

    private List<Object> step;

    private Integer userId;

    private Integer categoryCategoryId;

    public Recipe convertRecipe(){

        Recipe recipe = new Recipe();
        recipe.setCreatedAt(LocalDateTime.now());
        recipe.setRecipeName(this.getRecipeName());
        recipe.setRecipeImgUrl(this.getImg());
        recipe.setPortion(this.getPortion());
        recipe.setLeadTime(this.getLeadTime());
        recipe.setLevel(this.getLevel());
        recipe.setIngredient(this.getIngredient());
        recipe.setStep(this.getStep());
        recipe.setUserId(this.getUserId());
        recipe.setCategoryId(this.getCategoryCategoryId());
        return recipe;
    }
}
