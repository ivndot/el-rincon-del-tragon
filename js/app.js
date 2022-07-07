//alerta rating
$("#alert-rating").hide();
//alerta registro
$("#alert-register").hide();
//alerta registro
$("#alert-xsrf").hide();
//div que contiene instrucciones para la contraseña
$("#password-parameters").hide();

function showAlertRating() {
  $("#alert-rating")
    .fadeTo(2000, 500)
    .slideUp(500, function () {
      $("#alert-rating").slideUp(500);
    });
}

function showAlertRegister() {
  $("#alert-register")
    .fadeTo(2000, 500)
    .slideUp(500, function () {
      $("#alert-register").slideUp(500);
    });
}

function showAlertXSRF() {
  $("#alert-xsrf")
    .fadeTo(2000, 500)
    .slideUp(500, function () {
      $("#alert-xsrf").slideUp(500);
    });
}

function showPasswordProperties(){
  $("#password-parameters").show();
}

function paintStars(rating) {
  switch (rating) {
    case 1:
      $("#1").addClass("checked");
      break;
    case 2:
      $("#1").addClass("checked");
      $("#2").addClass("checked");
      break;
    case 3:
      $("#1").addClass("checked");
      $("#2").addClass("checked");
      $("#3").addClass("checked");
      break;
    case 4:
      $("#1").addClass("checked");
      $("#2").addClass("checked");
      $("#3").addClass("checked");
      $("#4").addClass("checked");
      break;
    case 5:
      $("#1").addClass("checked");
      $("#2").addClass("checked");
      $("#3").addClass("checked");
      $("#4").addClass("checked");
      $("#5").addClass("checked");
      break;
  }
}
