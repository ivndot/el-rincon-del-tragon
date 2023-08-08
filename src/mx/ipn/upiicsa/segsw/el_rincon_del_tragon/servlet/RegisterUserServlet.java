package mx.ipn.upiicsa.segsw.el_rincon_del_tragon.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.ipn.upiicsa.segsw.el_rincon_del_tragon.dao.UserDAO;
import mx.ipn.upiicsa.segsw.el_rincon_del_tragon.util.SecurityUtility;
import mx.ipn.upiicsa.segsw.el_rincon_del_tragon.util.Utility;
import mx.ipn.upiicsa.segsw.el_rincon_del_tragon.valueobject.ErrorValueObject;
import mx.ipn.upiicsa.segsw.el_rincon_del_tragon.valueobject.UserValueObject;


/**
 * 
 * @author Guillermo E. Martinez Barriga
 *
 */
@WebServlet("/register_user.controller")
public class RegisterUserServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doSomething(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doSomething(request, response);
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doSomething(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
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
		
		ErrorValueObject error = null;
		UserDAO dao = null;
		UserValueObject user = null;
		
		String email = request.getParameter("email");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String password = request.getParameter("password");
		String passwordConfirmation = request.getParameter("password-confirmation");
		
		if(Utility.containsAnEmptyValue(email, firstname, lastname, password, passwordConfirmation))
		{
			request.setAttribute("message", "Falt&oacute; capturar algun campo obligatorio");
			request.setAttribute("type", "error");
			
			RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
			rd.forward(request, response);
			return;
		}
		
		// se valida que todos los campos no contengan caracteres indebidos
		if(SecurityUtility.validateEmail(email) && SecurityUtility.validatePassword(password) && SecurityUtility.validatePassword(passwordConfirmation) && SecurityUtility.validateNames(firstname) && SecurityUtility.validateNames(lastname)) {
			
			try 
			{
				dao = new UserDAO();
				
				user = dao.findById(email); // se busca si el usuario ya existe
				
				if(password.equals(passwordConfirmation) == false) // se valida si las constraseñas coinciden
				{
					// las contraseñas son diferentes
					
					request.setAttribute("message", "Las contraseñas no son iguales.");
					request.setAttribute("type", "error");
					
					RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
					rd.forward(request, response);
					return;
				}
				else if(SecurityUtility.isPasswordStrong(password) == false) // se valida si la contraseña es segura
				{
					//la contraseña no es segura
					
					request.setAttribute("message", "La contraseña debe tener una logitud minima de 5 caracteres y contener al menos 1 min&uacute;scula, 1 may&uacute;scula, 1 n&uactue;mero y un s&iacute;mbolo especial .!#$%&_");
					request.setAttribute("type", "error");
					
					RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
					rd.forward(request, response);
					return;
				}
				else if(user != null) // El usuario ya existe 
				{
					//hay un usuario registrado con el correo electronico introducido
					
					request.setAttribute("message", "El usuario ya existe");
					request.setAttribute("type", "error");
					
					RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
					rd.forward(request, response);
					return;
				}
				else
				{
					user = new UserValueObject();
					
					user.setEmail(email);
					user.setFirstname(firstname);
					user.setLastname(lastname);
					user.setPassword(password);
					
					//user.setDaysOfPasswordValidity(30);
					//user.setTemporalPassword(false);
					//user.setStatus("ACTIVE");
					
					dao.create(user); // se ejecuta el metodo de creacion de usuario
					
					request.setAttribute("src", "auth");
					
					RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
					rd.forward(request, response);
					return;
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
						
				error = new ErrorValueObject();
				
				error.setMessage("Ocurrio un error al registrar usuario.");
				error.setDescription(ex.getMessage());
				error.setException(ex);
				
				request.setAttribute("error", error);
				
				RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
				rd.forward(request, response);
				return;
			}
			finally
			{
				if(dao != null) dao.closeConnection();
			}
			
		}else {
			
			error = new ErrorValueObject();
			
			error.setMessage("Caracteres no permitidos");
			error.setDescription("Se ingresaron credenciales con caracteres no permitidos");
			
			request.setAttribute("error", error);
			
			RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
			rd.forward(request, response);
			return;

			
		}
		

	}
}

