package com.elice.team6backspring.domain;

import com.elice.team6backspring.dto.RecipeRequest;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity // This tells Hibernate to make a table out of this class
@Getter
@Setter
//@TableGenerator(
//		name = "RECIPE_SEQ_GENERATOR",
//		table = "RECIPE_SEQ",
//		pkColumnValue = "recipeid", allocationSize = 1
//		)
public class Recipe {
	@Id
//	@GeneratedValue(strategy= GenerationType.TABLE, generator = "RECIPE_SEQ_GENERATOR")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="recipeid")
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

	@UpdateTimestamp
	@Column(name = "updatedat")
	private LocalDateTime updatedAt;

	@Column(name = "deletedat")
	private LocalDateTime deletedAt;

//	@ManyToOne
//	@JoinColumn(name="Id")
//	private User userId;

//	@Column(name="categoryCategoryId")
//	private Integer categoryCategoryId;

// Custom method to conditionally update lastUpdateTime
public int updateDeleteTime() {
	if (deletedAt == null) {
		this.deletedAt=LocalDateTime.now();
		return 0;
	}
	return 1;
}

	public Recipe of(RecipeRequest request){
		this.recipeName = request.getRecipeName();
		this.recipeImgUrl = request.getImg();
		this.portion = request.getPortion();
		this.leadTime = request.getLeadTime();
		this.level = request.getLevel();
		this.ingredient = request.getIngredient();
		this.step = request.getStep();
		this.updatedAt = LocalDateTime.now();
//		this.userId = user.getUserId();
//		this.categoryCategoryId = request.getCategoryCategoryId();

		return this;
	}
}
