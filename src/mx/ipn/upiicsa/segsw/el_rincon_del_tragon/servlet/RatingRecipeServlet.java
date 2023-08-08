package mx.ipn.upiicsa.segsw.el_rincon_del_tragon.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.ipn.upiicsa.segsw.el_rincon_del_tragon.dao.RatingRecipeDAO;
import mx.ipn.upiicsa.segsw.el_rincon_del_tragon.dao.RecipeDAO;
import mx.ipn.upiicsa.segsw.el_rincon_del_tragon.util.SecurityUtility;
import mx.ipn.upiicsa.segsw.el_rincon_del_tragon.util.Utility;
import mx.ipn.upiicsa.segsw.el_rincon_del_tragon.valueobject.ErrorValueObject;
import mx.ipn.upiicsa.segsw.el_rincon_del_tragon.valueobject.RatingRecipeValueObject;
import mx.ipn.upiicsa.segsw.el_rincon_del_tragon.valueobject.RecipeValueObject;
import mx.ipn.upiicsa.segsw.el_rincon_del_tragon.valueobject.UserValueObject;



@WebServlet("/rating_recipe.controller")
public class RatingRecipeServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doSomething(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doSomething(request, response);
	}

	/**
	 * @see HttpServlet#doSomething(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doSomething(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String referer = request.getHeader("referer"); // se obtiene el referer - el origen de la peticion

		System.out.println("AuthenticateUserServlet {referer: " + referer  +"}");
		// se valida el referar para evitar XSRF
		if(referer == null || referer.indexOf(Utility.REFERER_URL) == -1 )
		{
			request.setAttribute("src", "xsrf");
			RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
			rd.forward(request, response);
			return;
		}
		
		// se obtiene el rating 
		String rating_value = request.getParameter("rating");
		// se obtiene la receta
		RecipeValueObject recipe = (RecipeValueObject) request.getSession().getAttribute("recipe");
		// se obtiene el usuario de la sesion activa
		UserValueObject user = (UserValueObject) request.getSession().getAttribute("user");
		ErrorValueObject error = null;
		
		// se valida si el rating esta vacio
		if(Utility.containsAnEmptyValue(rating_value)) {
			
			error = new ErrorValueObject();
			error.setMessage("Error en calificar la receta");
			error.setDescription("No se defini&oacute; una calificaci&oacute;n para la receta");
			
			request.setAttribute("error", error);
			
			RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
			rd.forward(request, response);
		}
		
		RatingRecipeValueObject rating = null;
		RatingRecipeDAO dao = null;
		RecipeDAO daoRecipe = null;
		
		// se valida el rating
		if(SecurityUtility.validateRating(rating_value)) {
			//el rating es un numero del 1 al 5
			
			//se valida si el usuario esta logeado
			if(user != null) {
				//esta logeado
				//puede calificar la receta
				System.out.println("Usuario loggeado");
				
				try {
					dao = new RatingRecipeDAO();
					daoRecipe = new RecipeDAO();
					
					// se valida si ya se habia calificado antes esa receta
					rating = dao.findRatingRecipe(recipe.getId(), user.getEmail());
					if(rating != null) {
						// ya existia una califiacion para esa receta por el usuario
						// se hace un update
						dao.updateRatingRecipe(rating, rating_value);
						System.out.println("Actualiza valor");
					}else {
						// es la primera vez que el usuario califica la receta
						// se hace un insert
						dao.insertRatingRecipe(recipe.getId(), user.getEmail(), rating_value);
						System.out.println("Inserta valor");
					}
					
					// se obtiene lista de calificaciones de la receta
					ArrayList<RatingRecipeValueObject> ratingList = dao.getRatingsRecipe(recipe.getId()); // lista de objetos de la misma receta
					// se calcula el promedio de calificaciones
					int average = Utility.calculateAverageRating(ratingList);
					// se actualiza el valor en la tabla de recetas
					daoRecipe.updateRating(recipe.getId(), average);
					
					// se vuelve a ejecutar el metodo para obtener el rating desde la base de datos
					rating = dao.findRatingRecipe(recipe.getId(), user.getEmail());
					
					// se manda la receta
					request.setAttribute("recipe", recipe);
					request.setAttribute("requestRating", "success");
					// se manda el rating de la receta
					request.setAttribute("rating", Integer.toString(rating.getRating()));
					//request.getSession().setAttribute("rating", Integer.toString(rating.getRating()));
					
					RequestDispatcher rd = request.getRequestDispatcher("recipe_details.jsp");
					rd.forward(request, response);
					return;
					
				}catch(Exception e) {
					e.printStackTrace();
					
					error = new ErrorValueObject();
					
					error.setMessage("Ocurri&oacute; un error al calificar la receta");
					error.setDescription(e.getMessage());
					error.setException(e);
					
					request.setAttribute("error", error);
					
					RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
					rd.forward(request, response);
				}finally {
					
					if (dao != null) dao.closeConnection(); // se cierra la conexion 
				}

				
			}else {
				//el usuario no esta logeado
				//no puede calificar la receta
				try {
					request.setAttribute("recipe", recipe);
					request.setAttribute("requestRating", "failed");
					
					RequestDispatcher rd = request.getRequestDispatcher("recipe_details.jsp");
					rd.forward(request, response);
					return;
					
				}catch(Exception e){
					e.printStackTrace();
					
					error = new ErrorValueObject();
					
					error.setMessage("Ocurri&oacute; un error al buscar la receta");
					error.setDescription(e.getMessage());
					error.setException(e);
					
					request.setAttribute("error", error);
					
					RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
					rd.forward(request, response);
				}

			}
			
		}else {
			
			//el rating es un numero fuera del rango o es otro caracter
			error = new ErrorValueObject();
			
			error.setMessage("Error en la calificaci&oacute;n");
			error.setDescription("Se ingresaron caracteres no permitidos");
			
			request.setAttribute("error", error);
			
			RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
			rd.forward(request, response);
			
		}
		
	}

}
