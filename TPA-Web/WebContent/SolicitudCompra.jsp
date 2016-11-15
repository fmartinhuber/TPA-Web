<!-- SOLICITUD DE COMPRA - ESTO LO HACE: MAR -> Pero lo hace Daro porque es groso -->

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
  
<script src="jquery-3.1.1.js"></script>
<script>
	//Tabla Solicitudes de Articulos al iniciar la pagina
	$(document).ready(function(){
		var accion = "obtSolPen";
		$.get("SolicitudCompraServlet", {opcion: accion}, function(responseText) {
			var obtenido = responseText;
			var obtParseRow = obtenido.split("-?");
			$('#SolicitudArticulo tr').not(':first').remove();
			var html = '';
			for(var i=0; i < Object.keys(obtParseRow).length; i++){
				var obtParseColumn = obtParseRow[i].split(";");
				html += '<tr><td>' + obtParseColumn[0] + '</td><td>' + obtParseColumn[1] + '</td></tr>';
				}
			$('#SolicitudArticulo tr').first().after(html);
		});
	});
	

	
	//Tabla Articulos al momento de busqueda
	$(document).ready(function() {
		$("#obtArticulos").click(function() {
			var accion = "obtArticulos";
			var valorSolBuscada = $('#solicitudSeleccionada').val();
			$.get("SolicitudCompraServlet", {opcion: accion, solicitudBuscada: valorSolBuscada}, function(responseText) {
				var obtenido = responseText;
				var obtParseRow = obtenido.split("-?");

				//Si trajo datos muestro, sino alerta
				if (obtenido.trim()){
					//Seteo la solicitud utilizada como informacion en textfield disabled
					$("#solicitudMuestra").val(valorSolBuscada);

					$('#DetalleSolicitado tr').not(':first').remove();
					var html = '';
					for(var i=0; i < Object.keys(obtParseRow).length; i++){
						var obtParseColumn = obtParseRow[i].split(";");
						html += '<tr><td>' + obtParseColumn[0] + '</td><td>' + obtParseColumn[1] + '</td><td>' + obtParseColumn[2] + '</td><td>' + obtParseColumn[3] + 	'</td></tr>'; 
					}
					$('#DetalleSolicitado tr').first().after(html);
				}else{
					alert("No se encontro la Solicitud de Articulos ingresada");
				}
			});
		});
	});
	


	//Cargar Articulo
	$(document).ready(function() {
		$("#ingresarArt").click(function() {
			var accion = "ingresarArticulos";
			//Almaceno los 3 valores
			var valorSolBuscada = $('#solicitudSeleccionada').val();	//CodigoSolicitud
			var valorCompraArticulo =  $('#codCompraArticulo').val();	//CodigoArticulo
			var valorCantAComprar = $('#cantAComprar').val();			//CantidadAComprar

			$.get("SolicitudCompraServlet", {opcion: accion, solicitudBuscada: valorSolBuscada, articuloAComprar: valorCompraArticulo, cantidadAComprar: valorCantAComprar}, function(responseText) {
				var obtenido = responseText;
				var obtParseRow = obtenido.split("-?");

				$('#ComprarArticulo tr').not(':first').remove();
				var html = '';
				for(var i=0; i < Object.keys(obtParseRow).length; i++){
					var obtParseColumn = obtParseRow[i].split(";");
					html += '<tr><td>' + obtParseColumn[0] + '</td><td>' + obtParseColumn[1] + '</td><td>' + obtParseColumn[2] + '</td></tr>'; 
				}
				$('#ComprarArticulo tr').first().after(html);
			});
		});
	});


	
	//Enviar Solicitud de Compra
	$(document).ready(function() {
		$("#realizarSolicitudCompra").click(function() {
			var accion = "enviarSolicitudCompra";
			
			$.get("SolicitudCompraServlet", {opcion: accion}, function() {
				alert("Solicitud de Compra creada correctamente");
			});
		});	
	});
	

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
          	<li><a href="SolicitudCompra.jsp">SOLICITUD DE COMPRA </a></li>
            <li class="active"><a href="index.jsp">MENU PRINCIPAL</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>
    
    <form action="SolicitudCompraServlet" method="POST">
    <div id="headerwrap">
	    <div class="container">
			<div class="row">
				<div class="col-lg-8 col-lg-offset-2">
			    	
			    	Solicitud de Articulos
			    	<table id=SolicitudArticulo>
			    		<tr>
			    			<td>Codigo</td>
			    			<td>Fecha</td>
			    		</tr>
			    	</table>
			    	<br><br>
			    	
			    	Codigo Solicitud de Articulos: <input type="text" name="solicitudSeleccionada" id="solicitudSeleccionada">
			    	<button type="button" id="obtArticulos" name="obtArticulos">Obtener Articulos</button>
			    	
			    	<br><br>
			    	Articulos de la Solicitud <input type="text" name="solicitudMuestra" id="solicitudMuestra" disabled readonly>
					<table id=DetalleSolicitado>
				 		<tr>
				 			<td>Codigo</td>
				 			<td>Nombre</td>
				 			<td>Descripcion</td>
				 			<td>Cantidad</td>
				 		</tr>
				 	</table>
				 	
				 	<br><br>
				 	<hr>         
				 	<br><br>
				 	
				 	<h4>Seleccionar Articulos a Comprar</h4>
				 	
				 	Codigo Articulo: <input type="text" name="codCompraArticulo" id="codCompraArticulo">
				 	Cantidad a comprar: <input type="text" name="cantAComprar" id="cantAComprar"><br>
				 	<button type="button" id="ingresarArt" name="ingresarArt">Ingresar Articulo</button><br> 
				 	
				 	<br>
					Articulos Comprar
			    	<table id=ComprarArticulo>
			    		<tr>
			    			<td>Codigo Solicitud</td>
			    			<td>Codigo Articulo</td>
			    			<td>Cantidad</td>
			    		</tr>
			    	</table>
			    	<br><br><br>
				 	
				 	<button type="button" id="realizarSolicitudCompra" name="realizarSolicitudCompra">Realizar Solicitud de Compra</button><br> 
				 	<br><br>
				 	
			    	
    		</div><!-- /row -->
	    </div> <!-- /container -->
	</div><!-- /headerwrap -->
    </div>
    </form>
    
</body>
</html>