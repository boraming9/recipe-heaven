package com.elice.team6backspring.dto;

import com.elice.team6backspring.domain.Recipe;
import lombok.Getter;

import java.util.List;

@Getter
public class RecipeRequest {
    private String recipeName;
    private String img;
    private Integer portion;

    private Integer leadTime;
    private Integer level;

    private List<Object> ingredient;

    private List<Object> step;

    public Recipe convertRecipe(){

        Recipe recipe = new Recipe();
        recipe.setRecipeName(this.getRecipeName());
        recipe.setRecipeImgUrl(this.getImg());
        recipe.setPortion(this.getPortion());
        recipe.setLeadTime(this.getLeadTime());
        recipe.setLevel(this.getLevel());
        recipe.setIngredient(this.getIngredient());
        recipe.setStep(this.getStep());
        return recipe;
    }
}
