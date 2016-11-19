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
<script src="min-google.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/retina-1.1.0.js"></script>
	<script src="assets/js/jquery.hoverdir.js"></script>
	<script src="assets/js/jquery.hoverex.min.js"></script>
	<script src="assets/js/jquery.prettyPhoto.js"></script>
  	<script src="assets/js/jquery.isotope.min.js"></script>
  	<script src="assets/js/custom.js"></script>
<script type="text/javascript">

function agregar(){
	({
		//"codigo": document.getElementById('codigo').value,
		//"descripcion": document.getElementById('descripcion').value,
		//"precioVenta": document.getElementById('precioVenta').value,
		//"porcentajeGanancia": document.getElementById('porcentajeGanancia').value
		//"unidad": document.getElementById('unidad').value
		
		
	});
	alert("Se modificó el stock del artículo");
}


function enviar() {
	document.getElementById("metodo").value = "modificarArticulo";
}		


function elaboradoCreada (numero){
	if(numero != null) {
		alert("Se modificó el stock del artículo:" + numero);
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

	<div class="navbar navbar-default navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            
          </button>
          <a class="navbar-brand" href="index.jsp">Grupo 12 - Deposito</a>
        </div>
        <div class="navbar-collapse collapse navbar-right">
          <ul class="nav navbar-nav">
            <li class="active"><a href="index.jsp">MENU PRINCIPAL</a></li>
            
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">ARTICULOS <b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="TipoArticulo.jsp">CREAR ARTICULO</a></li>
                <li><a href="ModificarArticulo.jsp">MODIFICAR ARTICULO</a></li>
              </ul>
            </li>
            
            <li><a href="SolicitudCompra.jsp">SOLICITUD DE COMPRA </a></li>
            <li><a href="EntregaArticulo.jsp">ENTREGA DE ARTICULO </a></li>
 
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>

	<div id="headerwrap">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-lg-offset-2">
					<!-- <h1>CREAR ARTICULO - ESTO LO HACE: RAMA</h1>
			    	<h5>ACA HACE TU MAGIA </h5> -->

					<form action="ModificarArticuloServlet" method="POST">
		<input type="hidden" name="listaElaborado" id="listaElaborado" value="">
		<input type="hidden" name="metodo" id="metodo" value="">		
		<table>		
			<tr>
				<td colspan="2" align="center">Modificar Articulo</td>
			</tr>					
		 				 		 	
			<tr>
				<td>Código: </td>
				<td><input type="TEXT" name="codigo" id="codigo"></td>
			</tr>
			
			<tr>
				<td>Cantidad disponible : </td>
				<td><input type="TEXT" name="cantidad" id="cantidad"></td>
			</tr>							
								
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