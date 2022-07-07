package mx.ipn.upiicsa.segsw.el_rincon_del_tragon.valueobject;

import java.io.Serializable;

public class RatingRecipeValueObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id_recipe;
	private String email_user;
	private int rating;
	
	public int getId_recipe() {
		return id_recipe;
	}
	public void setId_recipe(int id_recipe) {
		this.id_recipe = id_recipe;
	}
	public String getEmail_user() {
		return email_user;
	}
	public void setEmail_user(String email_user) {
		this.email_user = email_user;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	
	
}
