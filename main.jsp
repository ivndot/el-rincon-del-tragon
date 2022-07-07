<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--IMPORTS-->
<%@ page
	import="mx.ipn.upiicsa.segsw.el_rincon_del_tragon.valueobject.UserValueObject"
    %>    
<%! String src = null;%>      

<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>El Rinc&oacute;n Del Trag&oacute;n | Inicio</title>
    <!--BOOTSTRAP-->
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <link rel="stylesheet" href="css/styles.css" />
    <!--GOOGLE FONTS-->
    <link rel="preconnect" href="https://fonts.gstatic.com" />
    <link href="https://fonts.googleapis.com/css2?family=Montserrat&display=swap" rel="stylesheet" />
    <!-- ICONS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css" integrity="sha512-HK5fgLBL+xu6dm/Ii3z4xhlSUyZgTT9tuc/hSrtw6uzJOvgRr2a9jyxxT1ely+B+xFAmJKVSTbpM/CuL7qxO8w==" crossorigin="anonymous" />
  </head>

    <%
    src = (String) request.getAttribute("src"); 
  	if(src != null && src.equals("auth")) {
    %>        
        <body style="font-family: 'Montserrat', sans-serif" onload="showAlertRegister()">
    <%
	}else if (src != null && src.equals("xsrf")){
	%>
        <body style="font-family: 'Montserrat', sans-serif" onload="showAlertXSRF()">
	<%
    }else {
	%>
        <body style="font-family: 'Montserrat', sans-serif">
	<%
    }
    %>
    <!--NAVEGADOR-->
    <%@ include file = "header.jsp" %>

    <!--IMAGEN-->
    <div style="width: 100%; background: linear-gradient(to bottom, rgb(0, 0, 0) 50%, rgba(0, 0, 0, 0) 50%); top: 0">
      <img src="images/tragon_logo.png" alt="logo" width="400px" class="center-block" />
    </div>
    <!--CONTENIDO-->
    <div class="container">
      <!--ALERTA REGISTRO-->
      <div id="alert-register" class="alert alert-success" role="alert">
        <p class="alert-link text-center">Se registr&oacute; el usuario correctamente</p>
      </div>
      <!--ALERTA XSRF-->
      <div id="alert-xsrf" class="alert alert-danger" role="alert">
        <p class="alert-link text-center">Operaci&oacute;n inv&aacute;lida</p>
      </div>
      <!--FORMULARIO PARA LA BUSQUEDA DE RECETAS-->
      <div style="width: 100%; display: flex; justify-content: center; margin: 70px 0px">
        <form class="form-inline" action="./search_recipes.controller" method="GET">
          <h4>Busque el platillo que desee...</h4>
          <div class="form-group">
            <input name="criterio" type="text" class="form-control" placeholder="Pizza..." style="display: inline-block; width: 500px; height: 40px" />
          </div>
          <button type="submit" class="btn btn-default btn-inline" style="background-color: #cc2706; color: white; border-color: #cc2706; height: 40px">Buscar</button>
        </form>
      </div>
    </div>

    <!--FOOTER-->
    <%@ include file = "footer.jsp" %>
  </body>
</html>
