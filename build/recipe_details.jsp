<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--IMPORTS-->
<%@ page
	import="mx.ipn.upiicsa.segsw.el_rincon_del_tragon.valueobject.RecipeValueObject"
    %>
<%!RecipeValueObject recipe;
   String requestRating;
   String rating;
   String error;%>    
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>El Rinc&oacute;n Del Trag&oacute;n | Detalle de receta</title>
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
    //se obtienen las variables
    recipe = (RecipeValueObject) request.getAttribute("recipe");
    requestRating = (String) request.getAttribute("requestRating");
    rating = (String) request.getAttribute("rating");
    error = (String) request.getAttribute("error");
    
    //se manda el body
    if(requestRating != null && requestRating.equals("failed")) {
		//el usuario no esta autenticado
		// no puede calificar la receta
	%>
    	<body style="font-family: 'Montserrat', sans-serif" onload="showAlertRating()">
	<%
    }else if(requestRating != null && requestRating.equals("success") || rating != null){
        int value = Integer.parseInt(rating);
        System.out.println("----"+value+"----");
	%>
    	<body style="font-family: 'Montserrat', sans-serif" onload="paintStars(<%=value%>)">
	<%
    }else {
	%>
    	<body style="font-family: 'Montserrat', sans-serif">
	<%
    }
    %>
    
    <!--NAVEGADOR-->
    <%@ include file = "header.jsp" %>

    <%
     //se valida si hay errores
     if(error != null){
    %>     
        <h2 class="text-center" style="color: red;\">No se encontr&oacute; la receta indicada</h2>
        <footer class="bg-light text-center navbar-fixed-bottom text-lg-start">
     <%
     }else{
        // Arreglo de string que contiene la lista de ingredientes
		String[] ingredients= recipe.getIngredientList().split(";");
		// Arreglo de string que contiene los pasos de la receta
		String[] steps = recipe.getProcedure().split(";");
     %>
    <!--CONTENIDO-->
    <!--IMAGEN-->
    <div style="margin: 0px; width: 100%; height: 400px; background-image: url(images/<%=recipe.getImage()%>); background-position: center; background-repeat: no-repeat; background-size: cover; position: relative; display: flex; align-items: center">
      <div style="width: 100%; height: 100px; background-color: rgba(0, 0, 0, 0.4); display: flex; justify-content: center; align-items: center">
        <h2 style="color: white; font-weight: bold" charset="UTF-8"><%=recipe.getName()%></h2>
      </div>
    </div>

    <div class="container" style="margin-top: 50px">
       <!-- BACK ARROW -->
       <a style="color: black; text-decoration: none" href="./search_recipes.controller?criterio="><i class="fas fa-arrow-left fa-1x" style="color: black"></i> Regresar</a>
      <!--ALERTA-->
      <div id="alert-rating" class="alert alert-danger" role="alert">
        <p class="alert-link text-center">Inicie sesi&oacute;n para calificar esta receta</p>
      </div>
      <!--CONTENEDOR DE ESTRELLAS PARA RATING-->
      <div class="text-center container-rating">
        <h4 style="font-weight: bold">Califica esta receta</h4>
        <a href="rating_recipe.controller?rating=5" class="s1"><span id="5" class="fa fa-star fa-1x"></span></a>
        <a href="rating_recipe.controller?rating=4" class="s2"><span id="4" class="fa fa-star fa-1x"></span></a>
        <a href="rating_recipe.controller?rating=3" class="s3"><span id="3" class="fa fa-star fa-1x"></span></a>
        <a href="rating_recipe.controller?rating=2" class="s4"><span id="2" class="fa fa-star fa-1x"></span></a>
        <a href="rating_recipe.controller?rating=1" class="s5"><span id="1" class="fa fa-star fa-1x"></span></a>
      </div>
      <!--DESCRIPCION-->
      <h2>Descripción</h2>
      <p><%=recipe.getDescription()%></p>
      <hr />
      <!--INGREDIENTES-->
      <h2>Ingredientes</h2>
      <ul>
        <%
        for(String ingredient: ingredients) {
			%>
            	<li><%=ingredient%></li>
			<%
            }
        %>
      </ul>
      <hr />
      <!--PROCEDIMIENTO-->
      <h2>Procedimiento</h2>
      <ol>
        <%
        for(String step: steps) {
			%>
            	<li><%=step%></li>
			<%
            }
        %>
      </ol>
      <hr />
      <!--PERSONAS-->
      <h2>Receta para</h2>
      <p><%=recipe.getNo_people()%> personas</p>
      <hr />
      <!--HECHO POR-->
      <div class="text-center">
        <h4 style="font-weight: bold">Hecho por</h4>
        <p><%=recipe.getRecipeCreatorEmail()%></p>
      </div>
      <!--CALIFICACION-->
      <h4 class="text-right" style="font-weight: bold;display: inline-block;">Calificaci&oacute;n promedio: </h4>
      <p style="display: inline-block;"> <%=recipe.getRating()%> / 5 <span class="fa fa-star fa-1x checked"></span></p>
    </div>

        <footer class="bg-light text-center text-lg-start" style="margin-top: 50px">
     <%
     }
    %>

    <!--FOOTER-->
      <a href="./team.jsp" style="text-decoration: none; color: white">
        <div class="text-center p-3" style="padding: 10px; background-color: black">
          <img src="images/dark_industries_logo.png" alt="dark industries logo" width="150px" style="display: inline-block" />
          <p style="display: inline-block; margin-left: 5px">© Dark Industries</p>
        </div>
      </a>
    </footer>
    <!--JQUERY-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!--JS-->
    <script src="js/app.js"></script>
  </body>
</html>
