package com.elice.team6backspring.domain;

import com.elice.team6backspring.dto.RecipeRequest;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity // This tells Hibernate to make a table out of this class
@Getter
@Setter
public class Category {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="categoryid")
	private Integer categoryId;
	@Column(name="categoryname")
	private String categoryName;

}
