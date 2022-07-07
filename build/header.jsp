<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--IMPORTS-->
<%@ page
	import="mx.ipn.upiicsa.segsw.el_rincon_del_tragon.valueobject.UserValueObject"%>
<!--DECLARACION VARIABLES-->
<%! UserValueObject user = null;%>

    <nav class="navbar navbar-default" style="background-color: black; margin-bottom: 0%; border: none; border-radius: 0%">
      <div class="container">
        <div class="navbar-header">
          <a class="navbar-brand" href="/">
            <img src="images/ham_icon.png" alt="logo" width="25px" style="display: inline-block" />
            <strong style="color: white">El Rinc&oacute;n Del Trag&oacute;n</strong>
          </a>
        </div>
<%
	// se obtiene el usuario
	user = (UserValueObject) request.getSession().getAttribute("user");

	if (user == null) {
		//no hay usuario logeado
	%>
        <!--USUARIO NO LOGEADO-->
        <div class="navbar-form navbar-right">
          <a href="./register.jsp" style="text-decoration: none"><button type="submit" class="btn btn-default btn-signup" >Sign up</button></a>
          <a href="./login.jsp" style="text-decoration: none"><button type="submit" class="btn btn-default" style="background-color: #cc2706;border: 1px solid #cc2706 ; color: white">Log in</button></a>
        </div>
	<%	
	}else{
		//hay un usuario logeado
	%>
        <!--USUARIO LOGEADO-->
        <div class="navbar-form navbar-right">
          <p style="display: inline-block; margin-right: 10px; color: white; font-weight: bold"><%=user.getName()%></p>
          <a href="./logout.controller" style="text-decoration: none"><button type="submit" class="btn btn-default" style="background-color: #cc2706; border: none; color: white">Salir</button></a>
        </div>
	<%	
	}
	%>

      </div>
    </nav>
