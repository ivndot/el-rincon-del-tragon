<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>El Rinc&oacute;n Del Trag&oacute;n | Sign up</title>
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
      <a style="display:inline-block; color: black; text-decoration: none; margin: 20px 0px;" href="/"><i class="fas fa-arrow-left fa-1x" style="color: black"></i> Regresar</a>
	 <!--MENSAJES-->
	 <%@ include file = "messages_panel.jsp" %>
      <!--FORMULARIO REGISTRO-->
      <form action="register_user.controller" method="POST" class="form" style="margin: 70px auto">
        <div class="form-color"></div>
        <div class="form-content">
          <h2 class="text-center"><strong>Sign up</strong></h2>
          <p>Introduce la informaci&oacute;n que se te solicita</p>
          <label for="email">Email</label>
          <input name="email" type="email" class="input" placeholder="Email" required />
          <label for="firstname">Nombre</label>
          <input name="firstname" type="text" class="input" placeholder="Nombre" pattern="[a-zA-Z]{2,20}" title="Solo se permiten letras" required />
          <label for="lastname">Apellido(s)</label>
          <input name="lastname" type="text" class="input" placeholder="Apellido(s)" pattern="[a-zA-Z]{2,20}" title="Solo se permiten letras" required />
          <label for="password">Contraseña</label>
          <input
            name="password"
            type="password"
            class="input"
            placeholder="Contraseña"
            pattern="(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[.!#$%&_])[A-Za-z0-9.!#$%&_]{5,20}"
            title="Debe contener de 5 a 20 caracteres de los cuales solo se permiten letras, n&uacute;meros y los caracteres especiales .!#$%&_"
            required
            onfocus="showPasswordProperties()"
          />
          <div id="password-parameters">
            <p>La contraseña debe contar con lo siguiente:</p>
            <ul>
              <li>Por lo menos una letra <strong>min&uacute;scula</strong></li>
              <li>Por lo menos una letra <strong>may&uacute;scula</strong></li>
              <li>Por lo menos un caracter <strong>num&eacute;rico</strong></li>
              <li>Por lo menos un caracter <strong>especial .!#$%&_</strong></li>
              <li>Contener de 5 a 20 caracteres</li>
            </ul>
          </div>

          <label for="password-confirmation">Confirmar contraseña</label>
          <input
            name="password-confirmation"
            type="password"
            class="input"
            placeholder="Confirmar contraseña"
            pattern="(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[.!#$%&_])[A-Za-z0-9.!#$%&_]{5,20}"
            title="Debe contener de 5 a 20 caracteres de los cuales solo se permiten letras, n&uacute;meros y los caracteres especiales .!#$%&_"
            required
          />
          <input type="submit" name="button" value="Ingresar" class="btn-form" />
        </div>
      </form>
    </div>

    <!--FOOTER-->
    <footer class="bg-light text-center text-lg-start">
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
