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
					<li><a href="CrearArticulo.jsp">CREAR ARTICULO </a></li>
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

					<h4>Crear Articulo</h4>

					<table id=CrearArticulo>
						<br> Nombre
						<input type="text" name="nombre" id="nombre">
						<br> Código:
						<input type="text" name="codigo" id="codigo">
						<br> Descripción:
						<input type="text" name="descripcion" id="descripcion">
						<br> Marca:
						<input type="text" name="marca" id="marca">
						<br> Precio:
						<input type="text" name="precio" id="precio">
						<br> Foto:
						<input type="text" name="foto" id="foto">
						<br> Origen:
						<input type="text" name="origen" id="origen">
						<br> Tipo:						
						<select id="comboTipo" name="comboTipo">							
							<option value="seleccione">Seleccione...</option>
							<option value="volvo">Moda</option>
							<option value="mercedes">Niño</option>
							<option value="audi">Mueble</option>
							<option value="audi">Electrodoméstico</option>
						</select>
						<br> Grupo:
						<input name="grupo" id="grupo" value="G12" readonly="readonly">
						<br>




						<button type="button" id="modificarArticulo"
							name="modificarArticulo">Aceptar</button>

						
					</table>


				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /headerwrap -->
</body>
</html>