package com.myPantry.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class Recipe {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String timp_preparare;
	@Column(name = "ingrediente", length = 2000)
	private String ingrediente;
	@Column(name = "mod_de_preparare", length = 5000)
	private String mod_de_preparare;
	private boolean favorite = false;

	public boolean isFavorite() {
		return favorite;
	}

	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}

	@Transient
	private MultipartFile recipeImage;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(String ingrediente) {
		this.ingrediente = ingrediente;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTimp_preparare() {
		return timp_preparare;
	}

	public void setTimp_preparare(String timp_preparare) {
		this.timp_preparare = timp_preparare;
	}

	public String getMod_de_preparare() {
		return mod_de_preparare;
	}

	public void setMod_de_preparare(String mod_de_preparare) {
		this.mod_de_preparare = mod_de_preparare;
	}

	public MultipartFile getRecipeImage() {
		return recipeImage;
	}

	public void setRecipeImage(MultipartFile recipeImage) {
		this.recipeImage = recipeImage;
	}

}
