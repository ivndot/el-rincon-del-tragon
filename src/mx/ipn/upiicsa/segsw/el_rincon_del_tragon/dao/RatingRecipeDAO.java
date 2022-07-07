package mx.ipn.upiicsa.segsw.el_rincon_del_tragon.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mx.ipn.upiicsa.segsw.el_rincon_del_tragon.exception.DAOInitializationException;
import mx.ipn.upiicsa.segsw.el_rincon_del_tragon.valueobject.RatingRecipeValueObject;

public class RatingRecipeDAO extends DataAccessObject{

	public RatingRecipeDAO() throws ClassNotFoundException, SQLException {
		super();
	}
	
	/**
	 * 
	 * @param id_recipe
	 * @param email_user
	 * @return RatingRecipe
	 * @throws SQLException
	 * @throws DAOInitializationException
	 */
	public RatingRecipeValueObject findRatingRecipe(int id_recipe, String email_user) throws SQLException, DAOInitializationException{
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		RatingRecipeValueObject recipe_rating = null;
		
		String sql = "SELECT * FROM rating_recipe WHERE id_recipe = ? AND email_user = ?"; //query
		System.out.println(sql);
		
		try {
			
			ps = prepareStatement(sql); // se prepara el statement
			ps.setInt(1, id_recipe); // se manda el id de la receta
			ps.setString(2, email_user); // se manda el email del usuario
			
			rs = ps.executeQuery(); // se ejecuta el query
			
			if(rs.next()) {
				//existe una calificacion del usuario a esa reseta
				
				// Crear Value Object u asignar valores de la base de datos
				recipe_rating = new RatingRecipeValueObject();
				
				recipe_rating.setId_recipe(rs.getInt("id_recipe"));
				recipe_rating.setEmail_user(rs.getString("email_user"));
				recipe_rating.setRating(rs.getInt("rating"));
			}
			
			return recipe_rating;
			
		}finally {
			closeResultSet(rs);
			closeStatement(ps);
		}
		
	}
	
	/**
	 * 
	 * @param rating_racipe RatingRecipe
	 * @param rating_value String
	 * @throws SQLException
	 * @throws DAOInitializationException
	 */
	public void updateRatingRecipe(RatingRecipeValueObject rating_recipe,String rating_value) throws SQLException, DAOInitializationException{
		
		PreparedStatement ps = null;
		
		String sql = "UPDATE rating_recipe SET rating = ? WHERE id_recipe = ? AND email_user = ?"; //query
		System.out.println(sql);
		
		try {
			
			ps = prepareStatement(sql); // se prepara el statement
			ps.setInt(1, Integer.parseInt(rating_value)); // se envia el nuevo valor del rating
			ps.setInt(2, rating_recipe.getId_recipe()); // se envia el id de la receta
			ps.setString(3, rating_recipe.getEmail_user()); // se envia el email del usuario
			
			ps.executeUpdate(); // se ejecuta el query
			
		}finally {
			closeStatement(ps);
		}
		
	}
	
	/**
	 * 
	 * @param id_recipe int
	 * @param rating_value String
	 * @param email_user String
	 * @throws SQLException
	 * @throws DAOInitializationException
	 */
	public void insertRatingRecipe(int id_recipe,String email_user, String rating_value) throws SQLException, DAOInitializationException{
		
		PreparedStatement ps = null;
		
		String sql = "INSERT INTO rating_recipe VALUES (?, ?, ?)"; //query
		System.out.println(sql);
		
		try {
			
			ps = prepareStatement(sql); // se prepara el statement
			ps.setInt(1, id_recipe); // se envia el id de la receta
			ps.setString(2, email_user); // se envia el email del usuario
			ps.setInt(3, Integer.parseInt(rating_value)); // se envia el rating de la receta
			
			ps.executeUpdate(); // se ejecuta el query
			
		}finally {
			closeStatement(ps);
		}
		
	}

	/**
	 * 
	 * @param id_recipe int
	 * @param email_user String
	 * @throws SQLException
	 * @throws DAOInitializationException
	 */
	public ArrayList<RatingRecipeValueObject> getRatingsRecipe(int id_recipe) throws SQLException, DAOInitializationException{
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		RatingRecipeValueObject recipe_rating = null;
		ArrayList<RatingRecipeValueObject> ratingRecipeList = new ArrayList<RatingRecipeValueObject>();
		
		String sql = "SELECT * FROM rating_recipe WHERE id_recipe = ?"; //query
		System.out.println(sql);
		
		try {
			
			ps = prepareStatement(sql); // se prepara el statement
			ps.setInt(1, id_recipe); // se envia el id de la receta
	
			rs = ps.executeQuery(); // se ejecuta el query
			
			while(rs.next()) {
				//se encuentra un registo
				
				// Crear Value Object u asignar valores de la base de datos
				recipe_rating = new RatingRecipeValueObject();
				
				recipe_rating.setId_recipe(rs.getInt("id_recipe"));
				recipe_rating.setEmail_user(rs.getString("email_user"));
				recipe_rating.setRating(rs.getInt("rating"));
				
				ratingRecipeList.add(recipe_rating);
			}
			
			return ratingRecipeList;
			
		}finally {
			closeResultSet(rs);
			closeStatement(ps);
		}
		
	}

}
