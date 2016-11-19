<!-- SOLICITUD DE ARTICULO - ESTO LO HACE: DARO -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- <meta http-equiv="X-UA-Compatible" content="IE=edge"> -->
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
    
    
	<style>
	table {
	    font-family: arial, sans-serif;
	    border-collapse: collapse;
	    width: 100%;
	}
	
	td, th {
	    border: 1px solid #dddddd;
	    text-align: left;
	    padding: 8px;
	}
	
	tr:nth-child(even) {
	    background-color: #dddddd;
	}
	</style>
    
</head>

<script src="min-google.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/retina-1.1.0.js"></script>
	<script src="assets/js/jquery.hoverdir.js"></script>
	<script src="assets/js/jquery.hoverex.min.js"></script>
	<script src="assets/js/jquery.prettyPhoto.js"></script>
  	<script src="assets/js/jquery.isotope.min.js"></script>
  	<script src="assets/js/custom.js"></script>
<script src="jquery-3.1.1.js"></script>
<script>
	//Tabla Solicitudes de Articulos al iniciar la pagina
	$(document).ready(function(){
		var accion = "obtSolPen";
		$.get("EntregaArticuloServlet", {opcion: accion}, function(responseText) {
			var obtenido = responseText;
			var obtParseRow = obtenido.split("-??");
			$('#SolicitudArticulo tr').not(':first').remove();
			var html = '';
			for(var i=0; i < Object.keys(obtParseRow).length; i++){
				var obtParseColumn = obtParseRow[i].split(";?");
				html += '<tr><td>' + obtParseColumn[0] + '</td><td>' + obtParseColumn[1] + '</td></tr>';
				//De esta forma cargo la tabla con un radiobutton pero no me da bola por cargarse antes de futuros clicks dinamicos
				//html += '<tr><td>' + obtParseColumn[0] + '</td><td>' + obtParseColumn[1] + '</td>';
				//html += '<td><div class="radio"><input type="radio" name="selectSolRadio" id="selectSolRadio value="'+i+'"></div></td></tr>';
			}
			$('#SolicitudArticulo tr').first().after(html);
		});
	});

	
	//Tabla Articulos al momento de busqueda
	$(document).ready(function() {
		$("#obtArticulos").click(function() {
			var accion = "obtArticulos";
			var valorSolBuscada = $('#solicitudSeleccionada').val();
			$.get("EntregaArticuloServlet", {opcion: accion, solicitudBuscada: valorSolBuscada}, function(responseText) {
				var obtenido = responseText;
				var obtParseRow = obtenido.split("-??");

				//Si trajo datos muestro, sino alerta
				if (obtenido.trim()){
					//Seteo la solicitud utilizada como informacion en textfield disabled
					$("#solicitudMuestra").val(valorSolBuscada);

					$('#DetalleSolicitado tr').not(':first').remove();
					var html = '';
					for(var i=0; i < Object.keys(obtParseRow).length; i++){
						var obtParseColumn = obtParseRow[i].split(";?");
						html += '<tr><td>' + obtParseColumn[0] + '</td><td>' + obtParseColumn[1] + '</td><td>' + obtParseColumn[2] + '</td><td>' + obtParseColumn[3] + '</td><td>' + obtParseColumn[4] + '</td></tr>'; 
					}
					$('#DetalleSolicitado tr').first().after(html);
				}else{
					alert("No se encontro la Solicitud de Articulos ingresada");
				}
			});
		});
	});

	
	//Obtener Articulo a modificar
	$(document).ready(function() {
		$("#obtModifArticulos").click(function() {
			var accion = "actArticulos";
			var valorArtBuscado = $('#codModArticulo').val();
			var valorSolBuscada = $('#solicitudMuestra').val();

			$.get("EntregaArticuloServlet", {opcion: accion, articuloBuscado: valorArtBuscado, solicitudBuscada: valorSolBuscada}, function(responseText) {
				var obtenido = responseText;
				var obtParseRow = obtenido.split(";?");

				//Si trajo datos muestro, sino alerta
				if (obtenido.trim()){
					//Seteo la cantidad como informacion en textfield disabled. 
					//0: Cantidad Pedida
					//1: Cantidad en Stock
					$('#cantSolicitadaArticulo').val(obtParseRow[0]);
					$('#cantStockArticulo').val(obtParseRow[1]);
				}else{
					alert("No se encontro el Articulo ingresado");
				}
				
			});
		});
	});


	//Actualizar Articulo a modificar
	$(document).ready(function() {
		$("#modificarArticulo").click(function(){
			var accion = "setCantArticulos";
			var nuevaCantidad = $('#nuevaCantidadArticulo').val();
			var articuloAModificar = $('#codModArticulo').val();
			var valorSolBuscada = $('#solicitudMuestra').val();
			
			//Si es Natural o cero continuo, sino alerta
			if (esNatural(nuevaCantidad)){
				$.get("EntregaArticuloServlet", {opcion: accion, nuevaCant: nuevaCantidad, articulo: articuloAModificar, solicitudBuscada: valorSolBuscada}, function(responseText) {
					//Blanqueo campos y alerta que se actualizo de forma correcta
					alert("Cantidad de Articulo modificada de forma correcta");
					$('#codModArticulo').val('');
					$('#cantSolicitadaArticulo').val('');
					$('#nuevaCantidadArticulo').val('');
					$('#cantStockArticulo').val('');
				});
			}else{
				alert("Por favor, ingrese un numero Natural");
			}
		});
	});

	
	//Realizar Entrega de Articulo
	$(document).ready(function() {
		$("#realizarEntrega").click(function(){
			var accion = "realizarEntrega";
			var valorSolBuscada = $('#solicitudMuestra').val();
			$.get("EntregaArticuloServlet", {opcion: accion, solicitudBuscada: valorSolBuscada}, function(responseText) {
				alert("Se realiza la entrega a Despacho de la Solicitud: " + valorSolBuscada);
			});
		});
	});

	
	//Valida numeros Naturales
	function esNatural(parametroStr) {
	    return /^\+?\d+$/.test(parametroStr);
	}
</script>




<body>
	<!-- Fixed navbar -->
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
			    	
			    	<h3>Solicitud de Articulos</h3>
			    	<table id="SolicitudArticulo" class="table table-striped">
			    		<tr>
			    			<td>Codigo</td>
			    			<td>Fecha</td>
			    		</tr>
			    	</table>
			    	<br><br>
			    	
			    	<h3>Codigo Solicitud de Articulos</h3> <input class="form-control" type="text" name="solicitudSeleccionada" id="solicitudSeleccionada">
			    	<button class="btn btn-success btn-lg" type="button"  id="obtArticulos" name="obtArticulos">Obtener Articulos</button>
			    	<br><br>
			    	<h3>Articulos de la Solicitud<h3></h3> <input class="form-control" type="text" name="solicitudMuestra" id="solicitudMuestra" disabled readonly>
					<br><br>
					<table id="DetalleSolicitado" class="table table-striped">
				 		<tr>
				 			<td>Codigo</td>
				 			<td>Nombre</td>
				 			<td>Descripcion</td>
				 			<td>Cantidad</td>
				 			<td>Cumplimiento</td>
				 		</tr>
				 	</table>
				 	<hr>
				 	<br><br>
				 	
				 	<h3>Modificacion de Cantidades</h3>
				 	<table class="table table-striped">
				 	<tr><td>Codigo Articulo:</td><td><input class="form-control" type="text" name="codModArticulo" id="codModArticulo"> <button type="button" class="btn btn-primary" id="obtModifArticulos" name="obtModifArticulos">Modificar Articulo</button></td></tr>
				 	<tr><td>Cantidad Solicitada: </td><td><input class="form-control" type="text" name="cantSolicitadaArticulo" id="cantSolicitadaArticulo" disabled readonly></td></tr>
				 	<tr><td>Cantidad en Stock:  </td><td><input class="form-control" type="text" name="cantStockArticulo" id="cantStockArticulo" disabled readonly></td></tr>
				 	<tr><td>Nueva cantidad a solicitar: </td><td><input class="form-control" type="text" name="nuevaCantidadArticulo" id="nuevaCantidadArticulo"></td></tr>
				 	<tr></tr>
				 	<tr><td></td>
				 		<td><button type="button" class="btn btn-danger" id="modificarArticulo" name="modificarArticulo">Aceptar</button><br></td>
				 	</tr>
				 	<hr>
				 	</table>
				 	
				 	<button class="btn btn-success btn-lg" type="button" id="realizarEntrega" name="realizarEntrega">Realizar Entrega de Articulo</button><br>
				 	
				 	<br><br>
				 	
				 </div><!-- col -lg-8 -->
    		</div><!-- /row -->
	    </div> <!-- /container -->
	</div><!-- /headerwrap -->
</body>
</html>