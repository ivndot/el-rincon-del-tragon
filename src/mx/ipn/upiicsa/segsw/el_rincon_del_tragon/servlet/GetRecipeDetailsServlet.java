package mx.ipn.upiicsa.segsw.el_rincon_del_tragon.servlet;

import java.io.IOException;
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

/**
 * 
 * @author Guillermo E. Martinez Barriga
 *
 */
@WebServlet("/get_recipe_details.controller")
public class GetRecipeDetailsServlet extends HttpServlet implements Servlet {
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
		if(referer == null || referer.indexOf("/") == -1 )
		{
			request.setAttribute("src", "xsrf");
			RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
			rd.forward(request, response);
			return;
		}
		
		ErrorValueObject error = null;
		RecipeValueObject recipe= null;
		RecipeDAO dao = null;
		RatingRecipeDAO daoRatingRecipe = null;
		RatingRecipeValueObject rating_recipe = null;
		UserValueObject user = (UserValueObject)request.getSession().getAttribute("user");
		
		String id = request.getParameter("id"); // se obtiene el id de la receta
		
		// se ejecuta el metodo para saber si el id esta vacio o es null
		if(Utility.containsAnEmptyValue(id)) {
			
			error = new ErrorValueObject();
			error.setMessage("Error en la b&uacute;squeda de receta");
			error.setDescription("No se defini&oacute; el id de la receta");
			
			request.setAttribute("error", error);
			
			RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
			rd.forward(request, response);
		}
		
		// se valida el id de busqueda
		if(SecurityUtility.validateId(id)) {
			
			try {
				
				dao = new RecipeDAO(); // se crea un objeto de tipo DAO de la receta
				daoRatingRecipe = new RatingRecipeDAO();
				
				// se ejecuta el metodo de buscar la receta por su id
				recipe = dao.searchById(id);
				
				if(recipe == null) {
					request.setAttribute("error", "error");
					
					RequestDispatcher rd = request.getRequestDispatcher("recipe_details.jsp");
					rd.forward(request, response);
					return;
				}
				
				// se valida que el usuario este autenticado para mostrar su rating de la receta
				if(user != null) {
					// se vuelve a ejecutar el metodo para obtener el rating desde la base de datos
					rating_recipe = daoRatingRecipe.findRatingRecipe(recipe.getId(), user.getEmail());
					
					// si rating recipe es null quiere decir que el usuario no tiene calificacion de esa receta
					if(rating_recipe == null) {
						// se envia null
						request.setAttribute("rating", null);
					}else {
						// se envia el rating del usuario por esa receta
						request.setAttribute("rating", Integer.toString(rating_recipe.getRating()));
					}
					//request.getSession().setAttribute("rating", Integer.toString(rating_recipe.getRating()) );
				}
				
				request.setAttribute("recipe", recipe);
				request.getSession().setAttribute("recipe", recipe);
				
				RequestDispatcher rd = request.getRequestDispatcher("recipe_details.jsp");
				rd.forward(request, response);
				
				return;
				
				
			}catch(Exception e) {
				
				e.printStackTrace();
				
				error = new ErrorValueObject();
				
				error.setMessage("Ocurri&oacute; un error al buscar la receta");
				error.setDescription(e.getMessage());
				error.setException(e);
				
				request.setAttribute("error", error);
				
				RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
				rd.forward(request, response);
				
			}finally {
				
				if (dao != null) dao.closeConnection(); // se cierra la conexion 
			}
			
			
		}else {
			
			error = new ErrorValueObject();
			
			error.setMessage("Error en la b&uacute;squeda");
			error.setDescription("Se ingresaron caracteres no permitidos");
			
			request.setAttribute("error", error);
			
			RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
			rd.forward(request, response);
		}
		
	}

}
