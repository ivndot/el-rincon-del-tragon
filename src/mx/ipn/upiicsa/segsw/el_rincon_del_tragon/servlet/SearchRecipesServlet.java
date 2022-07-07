package mx.ipn.upiicsa.segsw.el_rincon_del_tragon.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.ipn.upiicsa.segsw.el_rincon_del_tragon.dao.RecipeDAO;
import mx.ipn.upiicsa.segsw.el_rincon_del_tragon.util.SecurityUtility;
import mx.ipn.upiicsa.segsw.el_rincon_del_tragon.util.Utility;
import mx.ipn.upiicsa.segsw.el_rincon_del_tragon.valueobject.ErrorValueObject;
import mx.ipn.upiicsa.segsw.el_rincon_del_tragon.valueobject.RecipeValueObject;

/**
 * 
 * @author Guillermo E. Martinez Barriga
 *
 */
@WebServlet("/search_recipes.controller")
public class SearchRecipesServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
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
		RecipeDAO dao = null;
		List<RecipeValueObject> recipeList = new ArrayList<RecipeValueObject>();
		
		String criterio = request.getParameter("criterio"); // se obtiene el criterio
		String orderBy = request.getParameter("orderBy");
		
		// se ejecuta metodo para saber si el criterio esta vacio o es null
		if(Utility.containsAnEmptyValue(criterio)) {
			criterio ="";
		}
		
		// se pregunta si se envio el oderby
		if(orderBy != null) {
			// se quieren ordenar las recetas
			try {
				
				dao = new RecipeDAO(); // se crea el objeto dao de receta
				recipeList = dao.orderByRating(); // se ejecuta el metodo que los ordena 
				
				request.setAttribute("recipeList", recipeList);
				
				RequestDispatcher rd = request.getRequestDispatcher("recipe_list.jsp");
				rd.forward(request, response);
				return;
				
			}catch(Exception e) {
				
				e.printStackTrace();
				
				error = new ErrorValueObject();
				
				error.setMessage("Ocurri&oacute; un error al buscar las recetas");
				error.setDescription(e.getMessage());
				error.setException(e);
				
				request.setAttribute("error", error);
				
				RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
				rd.forward(request, response);
				
			}finally {
				
				if(dao != null) dao.closeConnection(); // se cierra la conexion
			}
			
		}else {
			
			// se valida el criterio
			if(SecurityUtility.validateInputSearch(criterio)) {
				
				try {
					
					dao = new RecipeDAO(); // se crea el objeto dao de receta
					
					//se ejecuta el metodo para buscar la lista de recetas
					recipeList = dao.searchByName(criterio);
					
					request.setAttribute("recipeList", recipeList);
					
					RequestDispatcher rd = request.getRequestDispatcher("recipe_list.jsp");
					rd.forward(request, response);
					return;
					
				}catch(Exception e) {
					
					e.printStackTrace();
					
					error = new ErrorValueObject();
					
					error.setMessage("Ocurri&oacute; un error al buscar las recetas");
					error.setDescription(e.getMessage());
					error.setException(e);
					
					request.setAttribute("error", error);
					
					RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
					rd.forward(request, response);
					
				}finally {
					
					if(dao != null) dao.closeConnection(); // se cierra la conexion
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
}
