package com.myPantry.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RecipeIngredient {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_ingredient;
	private String name;
	private String unit;
	private Integer type;

	public RecipeIngredient() {
		super();
	}

	public RecipeIngredient(String name, String unit, Integer type) {
		super();
		this.setName(name);
		this.setUnit(unit);
		this.setType(type);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	

}
