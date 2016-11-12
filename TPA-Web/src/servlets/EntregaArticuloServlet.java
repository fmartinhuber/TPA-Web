package servlets;

import java.io.IOException;
import java.util.*;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.*;

import controler.IDepositoControladorLocal;
import controler.IEntregaArticuloControladorLocal;

@WebServlet("/EntregaArticuloServlet")
public class EntregaArticuloServlet extends HttpServlet  {
	
	@EJB
	IEntregaArticuloControladorLocal depositoEntregaArticulo;

	private static final long serialVersionUID = 1L;
	
    public EntregaArticuloServlet() {
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		//System.out.println("AGUANTE JQUERY VIEJA NO ME IMPORTA NADA: " + request.getParameter("opcion"));
		//response.getWriter().write("POR FAVOR FUNCIONA");
		
		
		
		//----------------------------------------------------------------------------------------------------//
		
/*		//-----SOLICITUDES HARDCODEADAS-----//
		//Hardcodeo un string doblemente parseado de Solicitudes, por row y columnas. Deberia buscarse en la base y armarse aca
		if (request.getParameter("opcion").equalsIgnoreCase("obtSolPen")){
			//Parametros: Codigo;Fecha
			String solicitudesHardcore = "G12JO5I1;?24/08/2016-??G12I95TA;?11/09/2016-??G129IT15;?17/10/2016";
			response.getWriter().write(solicitudesHardcore);
		}
*/			
		if (request.getParameter("opcion").equalsIgnoreCase("obtSolPen")){
			//Obtenemos de la base las SolicitudesArticulo Pendientes
			List<SolicitudArticuloDTO> solisArtDto = new ArrayList<SolicitudArticuloDTO>();
			solisArtDto = depositoEntregaArticulo.listarSolicitudesPendientes();
			
			//Formateo la salida parseando columnas con ";?" y filas con "-??"
			String respuesta = "";
			for (SolicitudArticuloDTO s : solisArtDto){
				respuesta += s.getCodigo() + ";?" + s.getFechaEntrega() + "-??";
			}
			//Cortamos el "-??" final y enviamos la respuesta
			respuesta = respuesta.substring(0, respuesta.length()-3);
			response.getWriter().write(respuesta);
		}
		
		
		
		//----------------------------------------------------------------------------------------------------//
		
/*		//-----ARTICULOS HARDCODEADOS-----//
		//Hardcodeo un string doblemente parseado de Articulos, por row y columnas. Deberia buscarse en la base y armarse aca
		if (request.getParameter("opcion").equalsIgnoreCase("obtArticulos")){
			//Obtengo la solicitud a buscar
			String solicitudABuscar = request.getParameter("solicitudBuscada");
			
			//Aca se armaria una unica respuesta desde la base, por ser hardcodeado comparo con G12JO5I1
			if (solicitudABuscar.equalsIgnoreCase("G12JO5I1")){
				//Parametros: Codigo;Nombre;Descripcion;Cantidad;Cumplimiento
				String articulosHardcore = "G12890471;?Aire Acondicionado Split WBC 12B-13B 2645 F/C;?4 modos de operación diferentes: Frío, deshumidificación, ventilación y calor;?2;?Cumplido-??G1268671;?Freezer Horizontal Eternity M210;?202 Lt;?8;?Stock Insuficiente";
				response.getWriter().write(articulosHardcore);
			}
			
		}
*/		
		if (request.getParameter("opcion").equalsIgnoreCase("obtArticulos")){
			//Obtengo la solicitud a buscar
			String solicitudABuscar = request.getParameter("solicitudBuscada");
			
			//Obtenemos de la base los ItemSolicitudArticulos de la SolicitudArticulo buscada
			List<ItemSolicitudArticuloDTO> itemSolArtDto = new ArrayList<ItemSolicitudArticuloDTO>();
			itemSolArtDto = depositoEntregaArticulo.obtenerItemDeSolicitud(solicitudABuscar);
			
			//Formateo la salida parseando columnas con ";?" y filas con "-??"
			String respuesta = "";
			for (ItemSolicitudArticuloDTO i : itemSolArtDto){
				respuesta += i.getArticulo().getCodArticulo() +  ";?" + i.getArticulo().getNombre() + ";?" + i.getArticulo().getDescripcion() + ";?" + i.getCantidad() + ";?";
				//Valido si hay STOCK SUFICIENTE para cumplir el pedido y muestro el resultado
				if (i.getCantidad() <= i.getArticulo().getCantidadDisponible()){
					//Si se pide menos o igual que el stock, muestro OK
					respuesta += "OK-??";
				}else{
					//Si se pide mas, muestro NO OK
					respuesta += "Stock Insuficiente-??";
				}
			}
			//Cortamos el "-??" final y enviamos la respuesta
			respuesta = respuesta.substring(0, respuesta.length()-3);
			response.getWriter().write(respuesta);
		}
		
		
		
		//----------------------------------------------------------------------------------------------------//
		
		//----- MOSTRAR CANTIDAD ARTICULOS HARDCODEADOS-----//
		//Hardcodeo la busqueda del Articulo G12890471
		if (request.getParameter("opcion").equalsIgnoreCase("actArticulos")){
			//Obtengo el codigo articulo buscado
			String codArticulo = request.getParameter("solicitudBuscada");
			
			//Devuelvo la cantidad hardcodeadisima de 2
			response.getWriter().write("2");
		}
		
		
		//----- ACTUALIZAR CANTIDAD ARTICULOS HARDCODEADOS-----//
		/*Hardcodeo la actualizacion del Articulo G12890471. Aca se deberia recorrer a lista de Articulos de la Solicitud, encontrarlo, modificar su cantidad y actualizarlo en la base
		de esta forma al volver a consultarlo ya estaria actualizado*/
		if (request.getParameter("opcion").equalsIgnoreCase("setCantArticulos")){
			
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

}