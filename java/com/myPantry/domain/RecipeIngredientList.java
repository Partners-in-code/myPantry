package com.myPantry.domain;

import javax.persistence.Entity;

@Entity
public class RecipeIngredientList {
	private Long id;
	private int listLenght;
	private RecipeIngredient recipeIngredient[];
	public RecipeIngredientList() {
		super();
	}

	public RecipeIngredientList(Recipe recipe) {
		super();
		this.setId(recipe.getId());
		String ingredients = recipe.getIngrediente();
		String ingredientList[] = ingredients.split(";");
		
		for (int i=0; i< ingredientList.length; i++)
		{
			try {
			String recIngredient[] = ingredientList[i].split(" ",3);
			Integer type = Integer.parseInt(recIngredient[1]);
			String unit = recIngredient[2];
			String name = recIngredient[3];
			this.recipeIngredient[i].setName(name);
			this.recipeIngredient[i].setType(type);
			this.recipeIngredient[i].setUnit(unit);
			}
			catch(NumberFormatException e)
			{
				i--;
			}
		}
		this.listLenght = recipeIngredient.length;
	
	}

	public RecipeIngredient[] getRecipeIngredient() {
		return recipeIngredient;
	}

	public void setRecipeIngredient(RecipeIngredient recipeIngredient[]) {
		this.recipeIngredient = recipeIngredient;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getListLenght() {
		return listLenght;
	}

	public void setListLenght() {
		this.listLenght = recipeIngredient.length;
	}
	
	public RecipeIngredient elementAt(int pos) {
		if(pos<0 || pos >= this.listLenght)
			return null;
		return recipeIngredient[pos];
	}
	
}
