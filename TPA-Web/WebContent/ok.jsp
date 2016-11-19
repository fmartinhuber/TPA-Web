<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<!-- Bootstrap core CSS -->
    <link href="assets/css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="assets/css/style.css" rel="stylesheet">
    <link href="assets/css/font-awesome.min.css" rel="stylesheet">
<body>
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
	<div class="row">
		<img alt="Brand" src="giphy.gif">
	</div>
</body>
</html>