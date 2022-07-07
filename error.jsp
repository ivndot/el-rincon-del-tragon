<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--IMPORTS-->
<%@ page
	import="mx.ipn.upiicsa.segsw.el_rincon_del_tragon.valueobject.ErrorValueObject"
    %>
<%!ErrorValueObject error;%>    

<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>El Rinc&oacute;n Del Trag&oacute;n | Error</title>
    <!--BOOTSTRAP-->
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <link rel="stylesheet" href="css/styles.css" />
    <!--GOOGLE FONTS-->
    <link rel="preconnect" href="https://fonts.gstatic.com" />
    <link href="https://fonts.googleapis.com/css2?family=Montserrat&display=swap" rel="stylesheet" />
    <!-- ICONS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css" integrity="sha512-HK5fgLBL+xu6dm/Ii3z4xhlSUyZgTT9tuc/hSrtw6uzJOvgRr2a9jyxxT1ely+B+xFAmJKVSTbpM/CuL7qxO8w==" crossorigin="anonymous" />
  </head>
  <body style="font-family: 'Montserrat', sans-serif">
    
    <!--NAVEGADOR-->
    <%@ include file = "header.jsp" %>

    <!--CONTENIDO-->
    <div class="container">
       <!-- BACK ARROW -->
       <a style="display:inline-block; color: black; text-decoration: none; margin: 20px 0px;" href="./search_recipes.controller?criterio="><i class="fas fa-arrow-left fa-1x" style="color: black"></i> Regresar</a>

        <%
        //se obtiene el error
        error = (ErrorValueObject) request.getAttribute("error");
        if(error == null)
		{
        %>    
		    <!--NO HAY OBJETO ERROR-->
            <h2 styler="color: red;">No se encontr&oacute; informaci&oacute;n del error a mostrar</h2>
		<%
        }
		else
		{
		%>
            <!--TITULO ERROR-->
            <h2 style="color:red;"><%=error.getMessage()%></h2>
            <!--DESCRIPCION ERROR-->
            <h4><%=error.getDescription()%></h4>
		<%
        	if(error.getException()!=null) {
		%>
        		<!--EXCEPCION-->
                <h3><%=error.getException()%></h3>
		<%
        	}
		}
        %>
    </div>

    <!--FOOTER-->
    <footer class="bg-light text-center navbar-fixed-bottom text-lg-start">
      <a href="./team.jsp" style="text-decoration: none; color: white">
        <div class="text-center p-3" style="padding: 10px; background-color: black">
          <img src="images/dark_industries_logo.png" alt="dark industries logo" width="150px" style="display: inline-block" />
          <p style="display: inline-block; margin-left: 5px">© Dark Industries</p>
        </div>
      </a>
    </footer>
  </body>
</html>
