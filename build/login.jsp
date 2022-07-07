<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>El Rinc&oacute;n Del Trag&oacute;n | Log in</title>
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
      <!--FORMULARIO AUTENTICACION-->
      <form action="authenticate.controller" method="POST" class="form">
        <div class="form-color"></div>
        <div class="form-content">
          <h2 class="text-center"><strong>Log in</strong></h2>
          <p>Introduce tu email y tu contraseña para ingresar</p>
          <label for="email">Email</label>
          <input name="email" type="email" class="input" placeholder="Email" required/>
          <label for="password">Contraseña</label>
          <input name="password" type="password" class="input" placeholder="Contraseña" pattern="[a-zA-Z0-9.!#$%&_]{5,20}" title="Debe contener de 5 a 20 caracteres de los cuales solo se permiten letras, n&uacute;meros y los caracteres especiales .!#$%&_" required/>
          <input type="submit" name="button" value="Ingresar" class="btn-form" />
        </div>
      </form>
    </div>

    <!--FOOTER-->
    <%@ include file = "footer.jsp" %>
  </body>
</html>
