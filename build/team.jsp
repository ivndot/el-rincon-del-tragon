<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>El Rinc&oacute;n Del Trag&oacute;n | Team</title>
    <!--BOOTSTRAP-->
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <link rel="stylesheet" href="css/styles.css" />
    <!--GOOGLE FONTS-->
    <link rel="preconnect" href="https://fonts.gstatic.com" />
    <link href="https://fonts.googleapis.com/css2?family=Montserrat&display=swap" rel="stylesheet" />
  </head>

  <body style="font-family: 'Montserrat', sans-serif">
    <!--NAVEGADOR-->
    <%@ include file = "header.jsp" %>

    <!--CONTENIDO-->
    <div class="container">
      <div class="row" style="margin: 50px 0px;">
        <div class="col-sm-4" style="display: flex; align-items: center;">
            <img src="images/dark_industries_logo_negro.png" alt="logo" width="300px">
        </div>
        <div class="col-sm-4" style="border-left: 1px solid #000000;">
            <!--Ivan-->
          <div class="thumbnail">
            <img src="images/ivan.png" alt="imagen de ivan" />
            <div class="caption">
              <h3 class="text-center" style="font-weight: bold;">Ivan Alamos</h3>
              <p>Soy un estudiante de la carrera de Ingenier&iacute;a en inform&aacute;tica en la UPIICSA.</p>
              <p>Disfruto de ver una buena pel&iacute;cula o escuchar una buena canci&oacute;n.</p>
            </div>
          </div>
        </div>
        <div class="col-sm-4">
            <!--Marco-->
          <div class="thumbnail">
            <img src="images/marco.png" alt="imagen de marco" />
            <div class="caption">
              <h3 class="text-center" style="font-weight: bold;">Marco Epifanio</h3>
              <p>Estudiante en UPIICSA de la carrera de ingeniería en informática.</p>
              <p>Me gusta escuchar música, bailar y salir con mis amigos.</p>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!--FOOTER-->
    <%@ include file = "footer.jsp" %>
  </body>
</html>
