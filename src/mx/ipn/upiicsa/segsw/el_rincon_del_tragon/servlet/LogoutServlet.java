package mx.ipn.upiicsa.segsw.el_rincon_del_tragon.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Guillermo E. Martinez Barriga
 *
 */
@WebServlet("/logout.controller")
public class LogoutServlet extends HttpServlet implements Servlet 
{
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
		
		// Eliminar atributo en HttpSesion y redirigir a siguiente View
		request.getSession().removeAttribute("user");
		
		RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
		rd.forward(request, response);
		return;	
	}
}
