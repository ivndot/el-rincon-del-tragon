<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--IMPORTS-->
<%@ page
	import="mx.ipn.upiicsa.segsw.el_rincon_del_tragon.valueobject.RecipeValueObject"
    %>
<%@ page import="java.util.List"%>
<%! List<RecipeValueObject> recipeList;
    RecipeValueObject recipe;%>

<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>El Rinc&oacute;n Del Trag&oacute;n | Lista de recetas</title>
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
      <a style="display:inline-block; color: black; text-decoration: none; margin: 20px 0px;" href="/el-rincon-del-tragon/"><i class="fas fa-arrow-left fa-1x" style="color: black"></i> Regresar</a>

      <h1 class="text-center">Recetas</h1>
      <!--FORMULARIO PARA LA BUSQUEDA DE RECETAS-->
      <div style="width: 100%; display: flex; justify-content: center; margin: 70px 0px">
        <form class="form-inline" action="search_recipes.controller" method="GET">
          <h4>Busque el platillo que desee...</h4>
          <div class="form-group">
            <input name="criterio" type="text" class="form-control" placeholder="Pizza..." style="display: inline-block; width: 500px; height: 40px" />
          </div>
          <button type="submit" class="btn btn-default btn-inline" style="background-color: #cc2706; color: white; border-color: #cc2706; height: 40px">Buscar</button>
        </form>
      </div>

      <!--COMBO BOX ORDENAR-->
      <div class="dropdown">
        <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Ordenar por <span class="caret"></span></button>
        <ul class="dropdown-menu">
          <li><a href="search_recipes.controller?orderBy=rating">Mejor calificaci&oacute;n</a></li>
        </ul>
      </div>

      <h3 style="display: inline-block">Resultados para la búsqueda:</h3>
      <%
      if(request.getParameter("criterio") == null){
      %>
         <!--CRITERIO DE BUSQUEDA-->
         <h4 style="display: inline-block; font-weight: bold"></h4>
      <%
      }else{
      %>
         <!--CRITERIO DE BUSQUEDA-->
         <h4 style="display: inline-block; font-weight: bold"><%=request.getParameter("criterio")%></h4>
      <%
      }
      %>
      

      <% 
      // se obtiene la lista de recetas
      recipeList = (List<RecipeValueObject>) request.getAttribute("recipeList");
      // se valida que hayan registros  
      if(recipeList.size() == 0 || recipeList == null){
      %>    
         <h2 class="text-center" style="color: red"> No se encontraron recetas para la b&uacute;squeda</h2>
         <footer class="bg-light text-center navbar-fixed-bottom text-lg-start">
      <%
      }else{
      %>    
      <div class="container-cards">
      <%
      // se itera por la lista
      for(int i = 0; i < recipeList.size(); i++ ){
          recipe = recipeList.get(i);
      %>
        <!--CARD-->
        <a href="./get_recipe_details.controller?id=<%=recipe.getId()%>" class="link-card">
          <div class="card">
            <!--IMAGEN DE RECETA-->
            <div class="image" style="background-image: url(images/<%=recipe.getImage()%>)"></div>
            <div class="card-content">
              <!--NOMBRE DE RECETA-->
              <h3 class="content-name"><%=recipe.getName()%></h3>
              <!--DESCRIPCIOIN DE RECETA-->
              <p><%=recipe.getDescription()%></p>
              <!--HECHO POR-->
              <p><strong style="display:block;">Hecho por</strong> <%=recipe.getRecipeCreatorEmail()%></p>
              <!--CALIFICACION-->
              <p><strong>Calificaci&oacute;n:</strong> <%=recipe.getRating()%> / 5 <span class="fa fa-star fa-1x checked"></span></p>
            </div>
          </div>
        </a>
      <%    
      }
      %>
        </div>
      </div>
        <footer class="bg-light text-center text-lg-start">
      <%
      }  
      %>


    <!--FOOTER-->
      <a href="/el-rincon-del-tragon/team.jsp" style="text-decoration: none; color: white">
        <div class="text-center p-3" style="padding: 10px; background-color: black">
          <img src="images/dark_industries_logo.png" alt="dark industries logo" width="150px" style="display: inline-block" />
          <p style="display: inline-block; margin-left: 5px">© Dark Industries</p>
        </div>
      </a>
    </footer>
    <!--JQUERY-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <!--JS-->
    <script src="js/app.js"></script>
  </body>
</html>
