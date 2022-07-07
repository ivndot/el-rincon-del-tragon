package mx.ipn.upiicsa.segsw.el_rincon_del_tragon.valueobject;

import java.io.Serializable;

public class RecipeValueObject implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String recipeCreatorEmail;
	private String name;
	private String description;
	private String ingredientList;
	private String procedure;
	private String image;
	private int rating;
	private int no_people;
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRecipeCreatorEmail() {
		return recipeCreatorEmail;
	}
	public void setRecipeCreatorEmail(String recipeCreatorEmail) {
		this.recipeCreatorEmail = recipeCreatorEmail;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIngredientList() {
		return ingredientList;
	}
	public void setIngredientList(String ingredientList) {
		this.ingredientList = ingredientList;
	}
	public String getProcedure() {
		return procedure;
	}
	public void setProcedure(String procedure) {
		this.procedure = procedure;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public int getNo_people() {
		return no_people;
	}
	public void setNo_people(int no_people) {
		this.no_people = no_people;
	}
}
