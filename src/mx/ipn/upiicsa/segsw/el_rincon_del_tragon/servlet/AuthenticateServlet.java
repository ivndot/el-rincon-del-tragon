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
 * Authenticate a user
 * @author Guillermo E. Martinez Barriga
 *
 */
@WebServlet("/authenticate.controller")
public class AuthenticateServlet extends HttpServlet implements Servlet {
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
	 * @see HttpServlet#doSomething(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doSomething(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
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
		
		
		ErrorValueObject error = null; // variable de tipo error
		
		String email = request.getParameter("email"); // se obtiene el email
		String password = request.getParameter("password"); // se obtiene el password
		UserDAO dao = null;
		UserValueObject user = null;
		
		if(Utility.containsAnEmptyValue(email, password)) // se valida si los campos estan vacios
		{
			// error por falta de parametro(s)
			
			error = new ErrorValueObject(); // objeto de tipo error
			
			error.setMessage("Par&aacute;metro faltante");
			error.setDescription("Falt&oacute; capturar alg&uacute;n campo obligatorio");
			
			request.setAttribute("error", error);
			
			RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
			rd.forward(request, response);

			return;
		}
		
		// se valida el email
		// se valida el password
		if(SecurityUtility.validateEmail(email) && SecurityUtility.validatePassword(password)) {
			
			try 
			{
				dao = new UserDAO(); // nuevo objeto DAO
				user = dao.authenticate(email, password); // se ejecuta el metodo de autenticar y se iguala a la variables de usuario
				
				if(user != null) // Credenciales validas
				{
					//Date currentDate = new Date(); //fecha actual
					
					//long passwordAgeInMilis =  currentDate.getTime() - user.getDateOfLastPasswordUpdate().getTime();  // In Miliseconds
					
					//int remainingDaysOfPasswordValidity = (int) (user.getDaysOfPasswordValidity() -  passwordAgeInMilis/1000/60/60/24);
					
					// System.out.println("{ dias restantes: " + (user.getDaysOfPasswordValidity() - passwordAgeInMilis/1000/60/60/24) + "}");
					
					/*if(user.getStatus().equals(UserValueObject.STATUS.ACTIVE))
					{
						if( remainingDaysOfPasswordValidity < 0 )
						{
							request.setAttribute("message", "Su contraseña ha caducado.");
						}
						else 
						{

							if( remainingDaysOfPasswordValidity  < 7  )
							{
								request.setAttribute("message", "Su contraseña caducar&aacute; en " + remainingDaysOfPasswordValidity + " d&iacute;a(s).");
							}
							
							request.getSession().setAttribute("user", user);
						}
					}
					else if(user.getStatus().equals(UserValueObject.STATUS.BLOCKED))
					{	
						request.setAttribute("message", "El usuario est&aacute; bloqueado.");
					}*/
					
					request.getSession().setAttribute("user", user);

					RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
					rd.forward(request, response);
					return;				
				}
				else // Las credenciales NO son validas
				{
					// error por credenciales no validas
					
					error = new ErrorValueObject();
					
					error.setMessage("Credenciales no v&aacute;lidas");
					error.setDescription("Las credenciales proporcionadas no son correctas");
					
					request.setAttribute("error", error);
					
					RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
					rd.forward(request, response);
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
						
				error = new ErrorValueObject();
				
				error.setMessage("Ocurri&oacute; un error al validar credenciales");
				error.setDescription(ex.getMessage());
				error.setException(ex);
				
				request.setAttribute("error", error);
				
				RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
				rd.forward(request, response);
			}
			finally
			{
				if(dao != null) dao.closeConnection();

			}
		
		}else {
			
			error = new ErrorValueObject();
			
			if(password.length() > 20 || password.length() < 5) {
				//  error en la longitud del password
				error.setMessage("Error en la contraseña");
				error.setDescription("Tu constraseña debe tener m&iacute;nimo 5 y m&aacute;ximo 20 caracteres");
			}else {
				// error en los caracteres del email o del password
				error.setMessage("Caracteres no permitidos");
				error.setDescription("Se ingresaron credenciales con caracteres no permitidos");
			}

			
			request.setAttribute("error", error);
			
			RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
			rd.forward(request, response);
			return;
			
		}
		
		
	}
}
