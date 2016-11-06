<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="assets/ico/favicon.ico">

<title>IA - Grupo 12</title>

<!-- Bootstrap core CSS -->
<link href="assets/css/bootstrap.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="assets/css/style.css" rel="stylesheet">
<link href="assets/css/font-awesome.min.css" rel="stylesheet">

<script src="assets/js/modernizr.js"></script>
</head>

<script type="text/javascript">

function agregar(){
	({
		//"codigo": document.getElementById('codigo').value,
		//"descripcion": document.getElementById('descripcion').value,
		//"precioVenta": document.getElementById('precioVenta').value,
		//"porcentajeGanancia": document.getElementById('porcentajeGanancia').value
		//"unidad": document.getElementById('unidad').value
		
		
	});
	alert("Se agrego el artículo de tipo mueble");
}


function enviar() {
	document.getElementById("metodo").value = "crearArticulo";
}		


function elaboradoCreada (numero){
	if(numero != null) {
		alert("Se creo el artículo de tipo mueble:" + numero);
	}
	
	
}

</script>

<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat"%>
  
<%
   Date dNow = new Date();
   SimpleDateFormat ft = 
   new SimpleDateFormat ("dd/MM/yyyy");
   String currentDate = ft.format(dNow);
%>

<body>

	<!-- Fixed navbar -->
	<div class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.jsp">Grupo 12 - Deposito</a>
			</div>
			<div class="navbar-collapse collapse navbar-right">
				<ul class="nav navbar-nav">
<!-- 					<li><a href="CrearArticulo.jsp">CREAR ARTICULO </a></li>	 -->
					<li class="active"><a href="index.jsp">MENU PRINCIPAL</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>

	<div id="headerwrap">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-lg-offset-2">
					<!-- <h1>CREAR ARTICULO - ESTO LO HACE: RAMA</h1>
			    	<h5>ACA HACE TU MAGIA </h5> -->

					<form action="CrearArticuloServlet" method="POST">
		<input type="hidden" name="listaElaborado" id="listaElaborado" value="">
		<input type="hidden" name="metodo" id="metodo" value="">		
		<table>		
			<tr>
				<td colspan="2" align="center">Crear Artículo Mueble</td>
			</tr>
			
			<!-- 
		// Cantidad Disponible, Codigo, Descripcion, Fecha, Foto, Marca, Nombre, Origen, Precio 
		// Electrodomestico: ficha tecnica
		// Muebles: material
		// Niños: edad 
		// Moda: color y talle	
		 -->
		 
		 	<tr>
				<td>Id Depósito: </td>
				<td><input name="deposito" id="deposito" value="G12" readonly="readonly"></td>
			</tr>
		 
		 	<tr>
				<td>Tipo: </td>
				<td><input name="tipo" id="tipo" value="Mueble" readonly="readonly"></td>
			</tr>
		 		 		
			<tr>
				<td>Código: </td>
				<td><input type="TEXT" name="codigo" id="codigo"></td>
			</tr>
			<tr>
				<td>Nombre: </td>
				<td><input type="TEXT" name="nombre" id="nombre"></td>
			</tr>
			<tr>
				<td>Descripción: </td>
				<td><input type="TEXT" name="descripcion" id="descripcion"></td>
			</tr>
			
			<tr>
				<td>Fecha: </td>
				<td><input type="TEXT" value="<%=currentDate%>" name="fecha" id="fecha" readonly="readonly"></td>
			</tr>
			<tr>
				<td>Foto: </td>
				<td><input type="TEXT" name="foto" id="foto"></td>
			</tr>
			<tr>
				<td>Marca: </td>
				<td><input type="TEXT" name="marca" id="marca"></td>
			</tr>
			<tr>
				<td>Origen: </td>
				<td><input type="TEXT" name="origen" id="origen"></td>
			</tr>
			<tr>
				<td>Precio: </td>
				<td><input type="TEXT" name="precio" id="precio"></td>
			</tr>
			<!-- 
			<tr>
				<td>Cantidad disponible : </td>
				<td><input type="TEXT" name="cantidad" id="cantidad"></td>
			</tr>
			 -->
			<tr>
				<td>Material: </td>
				<td><input type="TEXT" name="material" id="material"></td>
			</tr>
		
			
				<!-- 
			<tr>
				<td colspan="2" align="right"><input type="button" value="Agregar" onClick="agregar();"></td>
			</tr>	
			 -->	
			<tr>
				<td align="center"><input type="submit" value="Aceptar" onClick="enviar();"></td>
				<td align="center"><input type="reset" value="Cancelar"></td>
			</tr>
		</table>

	</form>


				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /headerwrap -->
</body>
</html>