<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page
	import="mx.ipn.upiicsa.segsw.el_rincon_del_tragon.util.Utility"%>

<%!
	String message;
	String type;
%>
	<head>
	<meta charset="UTF-8" />
		<!-- BOOTSTRAP -->
		<link rel="stylesheet" href="css/bootstrap.min.css" />
		<link rel="stylesheet" href="css/styles.css" />
	</head>

<%
	message = (String) request.getAttribute("message");
	type = (String) request.getAttribute("type");

	if (Utility.containsAnEmptyValue(message) == false) // Hay mensaje por mostrar
	{
		
		if(type.equals("error")){
			// se manda un alert de color rojo
			%>
			<div class="alert alert-danger" role="alert" style="margin-top:20px;">
			<% 
		}else{
			// se manda un alert de color verde 
			%>
			<div class="alert alert-success" role="alert" style="margin-top:20px;">
			<% 
		}
			%>
	  			<p class ="alert-link"><%=message%></p>
			</div>

<%
	} 
%>
