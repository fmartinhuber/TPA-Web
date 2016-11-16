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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import controler.IDepositoControladorLocal;
import dto.ArticuloDTO;
import serviceREST.CrearArticuloClient;


@WebServlet("/CrearArticuloServlet")
public class CrearArticuloServlet extends HttpServlet {

	@EJB
	IDepositoControladorLocal deposito;
	
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
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void crearArticulo(HttpServletRequest request, HttpServletResponse response) throws NotBoundException, IOException {

		ArticuloDTO newArticulo = new ArticuloDTO();
		
		Calendar cal01 = GregorianCalendar.getInstance(); 	
		cal01.add(Calendar.DAY_OF_YEAR, -1);	
		Date day01 = cal01.getTime();	
		cal01.add(Calendar.MONTH, 1); 		
		
		//newArticulo.setId(ArticuloDao.getInstancia().obtenerMaximoIdArticulo());	
		newArticulo.setIdDeposito(request.getParameter("deposito"));
		//newArticulo.setCodArticulo(Integer.valueOf(request.getParameter("codigo")));
		newArticulo.setCodArticulo(request.getParameter("codigo"));
		newArticulo.setDescripcion(request.getParameter("descripcion"));
		newArticulo.setFecha(day01);
		newArticulo.setFoto(request.getParameter("foto"));
		newArticulo.setMarca(request.getParameter("marca"));
		newArticulo.setNombre(request.getParameter("nombre"));
		newArticulo.setOrigen(request.getParameter("origen"));
		newArticulo.setPrecio(Float.valueOf(request.getParameter("precio")));		
		newArticulo.setCantidadDisponible(0);
		newArticulo.setTipo(request.getParameter("tipo"));
		
		newArticulo.setFichaTecnica(request.getParameter("fichaTecnica"));
		newArticulo.setMaterial(request.getParameter("material"));
		newArticulo.setEdadRecomendada(request.getParameter("edad"));
		newArticulo.setColor(request.getParameter("color"));
		newArticulo.setTalle(request.getParameter("talle"));
		
		
		deposito.crearArticulo(newArticulo);
		
//		Gson prettyGson = new GsonBuilder().serializeNulls().create();
//		String JSON = prettyGson.toJson(newArticulo); 
//
//		CrearArticuloClient.conexion(newArticulo);
//		PruebaDespachoQueue.prueba("prueeeeeeeeeeeeeeba");
		
		response.getWriter().print("<h1> Se creó el artículo de tipo " +newArticulo.getTipo()+"<h1>");		
		response.getWriter().print("<p> <a href=\"/TPA-Web-0.0.1-SNAPSHOT/\">Regresar Menu</a></p>");		
					
		// Enviarlo por REST
		//CrearArticuloJAXRSClient.conexion(articuloDto);
		CrearArticuloClient.conexion(newArticulo);
	}

}
