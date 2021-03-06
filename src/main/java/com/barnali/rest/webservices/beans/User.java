package com.barnali.rest.webservices.beans;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="All details about the user")									//For API documentation
@Entity																				//For JPA
public class User {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min=2 , max=20, message="Name should be with 2 to 20 characters")
	@ApiModelProperty(notes="Name should be within 2 to 20 characters")				//For API documentation
	private String name;
	
	@Past
	@ApiModelProperty(notes="Birth date should before current date")				//For API documentation
	private Date birthDate;
	
	@OneToMany(mappedBy="user")
	private List<Post> post;
	
	//not necessary to be a public constructor
	protected User() {
		
	}

	public User(Integer id, String name, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public List<Post> getPost() {
		return post;
	}

	public void setPost(List<Post> post) {
		this.post = post;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}
	
}
