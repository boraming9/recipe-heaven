package com.elice.team6backspring.dto;

import com.elice.team6backspring.domain.Recipe;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class RecipeResponse {

    private Integer recipeId;
    private String recipeName;
    private String img;
    private Integer portion;

    private Integer leadTime;
    private Integer level;

    private List<Object> ingredient;

    private List<Object> step;

    private Integer userId;

    private Integer categoryId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    public static RecipeResponse of(Recipe recipe){

        RecipeResponse response = new RecipeResponse();
        response.recipeId = recipe.getRecipeId();
        response.recipeName = recipe.getRecipeName();
        response.img = recipe.getRecipeImgUrl();
        response.portion = recipe.getPortion();
        response.leadTime = recipe.getLeadTime();
        response.level = recipe.getLevel();
        response.ingredient = recipe.getIngredient();
        response.step = recipe.getStep();
        response.createdAt = recipe.getCreatedAt();
        response.updatedAt = recipe.getUpdatedAt();
//        response.userId = recipe.getUser().getId();
        response.userId = recipe.getUserId();
        response.categoryId = recipe.getCategoryId();

        return response;
    }

}
