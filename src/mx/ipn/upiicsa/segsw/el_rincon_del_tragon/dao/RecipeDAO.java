package mx.ipn.upiicsa.segsw.el_rincon_del_tragon.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mx.ipn.upiicsa.segsw.el_rincon_del_tragon.exception.DAOInitializationException;
import mx.ipn.upiicsa.segsw.el_rincon_del_tragon.valueobject.RecipeValueObject;

public class RecipeDAO extends DataAccessObject{

	public RecipeDAO() throws ClassNotFoundException, SQLException {
		super();	
	}
	
	/**
	 * 
	 * @param criterio
	 * @return List<RecipeValueObject>
	 * @throws SQLException
	 * @throws DAOInitializationException
	 */
	public List<RecipeValueObject> searchByName(String criterio) throws SQLException, DAOInitializationException{
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		RecipeValueObject recipe = null;
		List<RecipeValueObject> recipeList = new ArrayList<RecipeValueObject>();
		
		String sql = "SELECT * FROM recipes WHERE name LIKE ?"; // query
		System.out.println(sql);
		
		try {
			
			ps = prepareStatement(sql); // se prepara el statement
			ps.setString(1, "%" + criterio + "%"); // se envia el criterio
			
			rs = ps.executeQuery(); // se ejecuta el query
			
			while(rs.next()) {
				//existen recetas con el criterio expecificado
				
				recipe = new RecipeValueObject(); // se crea un objeto de tipo receta
				
				recipe.setId(rs.getInt("id"));
				recipe.setRecipeCreatorEmail(rs.getString("recipe_creator_email"));
				recipe.setName(rs.getString("name"));
				recipe.setDescription(rs.getString("description"));
				recipe.setIngredientList(rs.getString("ingredient_list"));
				recipe.setProcedure(rs.getString("recipe_procedure"));
				recipe.setRating(rs.getInt("rating"));
				recipe.setImage(rs.getString("image"));
				recipe.setNo_people(rs.getInt("no_people"));
				
				recipeList.add(recipe);
			}
			
			return recipeList;
			
		}finally {
	
			closeResultSet(rs);
			closeStatement(ps);
		}
		
		
	}
	
	/**
	 * 
	 * @return List<RecipeValueObject>
	 * @throws SQLException
	 * @throws DAOInitializationException
	 */
	public List<RecipeValueObject> orderByRating() throws SQLException, DAOInitializationException{
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		RecipeValueObject recipe = null;
		List<RecipeValueObject> recipeList = new ArrayList<RecipeValueObject>();
		
		String sql = "SELECT * FROM recipes ORDER BY rating DESC"; // query
		System.out.println(sql);
		
		try {
			
			ps = prepareStatement(sql); // se prepara el statement
			
			rs = ps.executeQuery(); // se ejecuta el query
			
			while(rs.next()) {
				//existen recetas con el criterio expecificado
				
				recipe = new RecipeValueObject(); // se crea un objeto de tipo receta
				
				recipe.setId(rs.getInt("id"));
				recipe.setRecipeCreatorEmail(rs.getString("recipe_creator_email"));
				recipe.setName(rs.getString("name"));
				recipe.setDescription(rs.getString("description"));
				recipe.setIngredientList(rs.getString("ingredient_list"));
				recipe.setProcedure(rs.getString("recipe_procedure"));
				recipe.setRating(rs.getInt("rating"));
				recipe.setImage(rs.getString("image"));
				recipe.setNo_people(rs.getInt("no_people"));
				
				recipeList.add(recipe);
			}
			
			return recipeList;
			
		}finally {
	
			closeResultSet(rs);
			closeStatement(ps);
		}
		
		
	}
	
	/**
	 * 
	 * @param id
	 * @return RecipeValueObject
	 * @throws SQLException
	 * @throws DAOInitializationException
	 */
	public RecipeValueObject searchById(String id) throws SQLException, DAOInitializationException{
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		RecipeValueObject recipe = null;
		
		String sql = "SELECT * FROM recipes WHERE id = ?"; //query
		System.out.println(sql);
		
		try {
			
			ps = prepareStatement(sql); // se prepara el statement
			ps.setString(1, id); // se manda el id de la receta
			
			rs = ps.executeQuery(); // se ejecuta el query
			
			if(rs.next()) {
				//existe la receta
				
				// Crear Value Object u asignar valores de la base de datos
				recipe = new RecipeValueObject();

				recipe.setId(rs.getInt("id"));
				recipe.setRecipeCreatorEmail(rs.getString("recipe_creator_email"));
				recipe.setName(rs.getString("name"));
				recipe.setDescription(rs.getString("description"));
				recipe.setIngredientList(rs.getString("ingredient_list"));
				recipe.setProcedure(rs.getString("recipe_procedure"));
				recipe.setImage(rs.getString("image"));
				recipe.setRating(rs.getInt("rating"));
				recipe.setNo_people(rs.getInt("no_people"));
			}
			
			return recipe;
			
		}finally {
			closeResultSet(rs);
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
	public void updateRating(int id_recipe, int rating) throws SQLException, DAOInitializationException{
		
		PreparedStatement ps = null;
		
		String sql = "UPDATE recipes SET rating = ? WHERE id = ?"; //query
		System.out.println(sql);
		
		try {
			
			ps = prepareStatement(sql); // se prepara el statement
			ps.setInt(1, rating); // se envia el rating calculado
			ps.setInt(2, id_recipe); // se envia el id de la receta

			ps.executeUpdate(); // se ejecuta el query
			
		}finally {
			closeStatement(ps);
		}
		
	}
	
	
}
