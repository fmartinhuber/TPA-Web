package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controler.IEntregaArticuloControladorLocal;
import dto.*;

@WebServlet("/SolicitudCompraServlet")
public class SolicitudCompraServlet extends HttpServlet {

	@EJB
	IEntregaArticuloControladorLocal depositoEntregaArticulo;
	
	private static final long serialVersionUID = 1L;
       
    public SolicitudCompraServlet() {

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		
		
		
		
		//----------------------------------------------------------------------------------------------------//
		
		//OBTENER SOLICITUD DE ARTICULOS
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
		
		//OBTENER ARTICULOS DE SOLICITUD SELECCIONADA
		if (request.getParameter("opcion").equalsIgnoreCase("obtArticulos")){
			//Obtengo la solicitud a buscar
			String solicitudABuscar = request.getParameter("solicitudBuscada");
			
			//Obtenemos de la base los ItemSolicitudArticulos de la SolicitudArticulo buscada
			List<ItemSolicitudArticuloDTO> itemSolArtDto = new ArrayList<ItemSolicitudArticuloDTO>();
			itemSolArtDto = depositoEntregaArticulo.obtenerItemDeSolicitud(solicitudABuscar);
			
			//Formateo la salida parseando columnas con ";?" y filas con "-??"
			String respuesta = "";
			for (ItemSolicitudArticuloDTO i : itemSolArtDto){
				respuesta += i.getArticulo().getCodArticulo() +  ";?" + i.getArticulo().getNombre() + ";?" + i.getArticulo().getDescripcion() + ";?" + i.getCantidad() + "-??";
			}
			//Cortamos el "-??" final y enviamos la respuesta
			respuesta = respuesta.substring(0, respuesta.length()-3);
			response.getWriter().write(respuesta);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

}
