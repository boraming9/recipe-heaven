package com.elice.team6backspring.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity // This tells Hibernate to make a table out of this class
@Setter
@Getter
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@Column(name="profilepicture")
	private String profilePicture;
	private String nickname;
//	private UserRoleEnum role;

	private String email;

	private String name;


}
