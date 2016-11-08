package servlets;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.util.*;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controler.DepositoControlador;
import dto.ArticuloDTO;
import serviceREST.CrearArticuloJAXRSClient;


@WebServlet("/CrearArticuloServlet")
public class CrearArticuloServlet extends HttpServlet {

	@EJB
	DepositoControlador deposito;
	
	private static final long serialVersionUID = 1L;

	public CrearArticuloServlet() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("metodo: " + request.getMethod());
		System.out.println("metodo: " + request.getParameter("metodo"));

		try {
			if (request.getParameter("metodo").equalsIgnoreCase("crearArticulo")) {
				crearArticulo(request, response);
			} else if (request.getParameter("metodo").equalsIgnoreCase("nuevoMetodo")) {
				//HACE TU LOGICA
				
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void crearArticulo(HttpServletRequest request, HttpServletResponse response) throws NotBoundException, IOException {

		ArticuloDTO articuloDto = new ArticuloDTO();
		//TODOS LOS SETTERS
		deposito.crearArticulo(articuloDto);
		
		
		CrearArticuloJAXRSClient.conexion(articuloDto);
		// Cantidad Disponible, Codigo, Descripcion, Fecha, Foto, Marca, Nombre, Origen, Precio 
		// Electrodomestico: ficha tecnica
		// Muebles: material
		// Niños: edad 
		// Moda: color y talle
		
//		ArticuloBean newArticulo = new ArticuloBean();
//		
//		Calendar cal01 = GregorianCalendar.getInstance(); 	
//		cal01.add(Calendar.DAY_OF_YEAR, -1);	
//		Date day01 = cal01.getTime();	
//		cal01.add(Calendar.MONTH, 1); 		
//		
//		newArticulo.setId(ArticuloDao.getInstancia().obtenerMaximoIdArticulo());	
//		newArticulo.setIdDeposito(request.getParameter("deposito"));
//		newArticulo.setCodigo(Integer.valueOf(request.getParameter("codigo")));
//		newArticulo.setDescripcion(request.getParameter("descripcion"));
//		newArticulo.setFecha(day01);
//		newArticulo.setFoto(request.getParameter("foto"));
//		newArticulo.setMarca(request.getParameter("marca"));
//		newArticulo.setNombre(request.getParameter("nombre"));
//		newArticulo.setOrigen(request.getParameter("origen"));
//		newArticulo.setPrecio(Float.valueOf(request.getParameter("precio")));		
//		newArticulo.setCantidadDisponible(0);
//		newArticulo.setTipo(request.getParameter("tipo"));
//		
//		newArticulo.setFichaTecnica(request.getParameter("fichaTecnica"));
//		newArticulo.setMaterial(request.getParameter("material"));
//		newArticulo.setEdadRecomendada(request.getParameter("edad"));
//		newArticulo.setColor(request.getParameter("color"));
//		newArticulo.setTalle(request.getParameter("talle"));
//		
//		System.out.println("codigo" +request.getParameter("codigo"));
//		System.out.println("deposito" +request.getParameter("deposito"));
//		System.out.println("descripcion" +request.getParameter("descripcion"));
//		//System.out.println("fecha" +request.getParameter("fecha"));
//		System.out.println("foto" +request.getParameter("foto"));
//		System.out.println("marca" +request.getParameter("marca"));
//		System.out.println("nombre" +request.getParameter("nombre"));
//		System.out.println("origen" +request.getParameter("origen"));
//		System.out.println("precio" +request.getParameter("precio"));
//		//System.out.println("cantidad" +request.getParameter("cantidad"));
//		System.out.println("tipo" +request.getParameter("tipo"));
//		
//		System.out.println("fichaTecnica" +request.getParameter("fichaTecnica"));
//		System.out.println("material" +request.getParameter("material"));
//		System.out.println("edad" +request.getParameter("edad"));
//		System.out.println("color" +request.getParameter("color"));
//		System.out.println("talle" +request.getParameter("talle"));
//		
//		try{		
//			
//			DepositoControlador.getInstancia().crearArticulo(newArticulo);
//			response.getWriter().print("<h1> Se creó el artículo de tipo " +newArticulo.getTipo()+"<h1>");
//			
//			response.getWriter().print("<p> <a href=\"/tpia-rama/index.jsp\">Regresar Menu</a></p>");
//			//<li class="active"><a href="index.jsp">MENU PRINCIPAL</a></li>
//			
//		}catch(Exception e){
//			e.printStackTrace();
//		}

	}

}
