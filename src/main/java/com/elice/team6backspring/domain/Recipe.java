package com.elice.team6backspring.domain;

import com.elice.team6backspring.dto.RecipeRequest;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.util.List;

@Entity // This tells Hibernate to make a table out of this class
@Getter
@Setter
@TableGenerator(
		name = "RECIPE_SEQ_GENERATOR",
		table = "RECIPE_SEQUENCES",
		pkColumnValue = "RECIPE_SEQ", allocationSize = 1
		)
public class Recipe {
	@Id
	@GeneratedValue(strategy= GenerationType.TABLE, generator = "RECIPE_SEQ_GENERATOR")
	private Integer recipeId;
	@Column(name="recipename")
	private String recipeName;

	@Column(name="img")
	private String recipeImgUrl;

	private Integer portion;

	@Column(name="leadtime")
	private Integer leadTime;
	private Integer level;

	@Type(JsonType.class)
	@Column(name = "ingredient", columnDefinition = "longtext")
	private List<Object> ingredient;

	@Type(JsonType.class)
	@Column(name = "step", columnDefinition = "longtext")
	private List<Object> step;

	public Recipe of(RecipeRequest request){
		this.recipeName = request.getRecipeName();
		this.recipeImgUrl = request.getImg();
		this.portion = request.getPortion();
		this.leadTime = request.getLeadTime();
		this.level = request.getLevel();
		this.ingredient = request.getIngredient();
		this.step = request.getStep();

		return this;
	}
}
