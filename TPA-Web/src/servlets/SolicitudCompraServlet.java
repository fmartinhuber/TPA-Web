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
import serviceREST.SolicitudCompraFabricaClient;

@WebServlet("/SolicitudCompraServlet")
public class SolicitudCompraServlet extends HttpServlet {

	@EJB
	IEntregaArticuloControladorLocal depositoEntregaArticulo;
	
	private static final long serialVersionUID = 1L;
	
	//Este Array se va a ir cargando por cada row de la tabla de Articulos a Comprar
	private List<String> cadenaStringSalida = new ArrayList<String>();
	
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
			
			//Formateo la salida parseando columnas con ";" y filas con "-?"
			String respuesta = "";
			for (SolicitudArticuloDTO s : solisArtDto){
				respuesta += s.getCodigo() + ";" + s.getFechaEntrega() + "-?";
			}
			//Cortamos el "-?" final y enviamos la respuesta
			respuesta = respuesta.substring(0, respuesta.length()-2);
			response.getWriter().write(respuesta);
			
			//Como se accede a este metodo al momento de abrir la pagina, blanqueamos el vector acumulado
			cadenaStringSalida.clear();
		}
		
		
		
		
		//----------------------------------------------------------------------------------------------------//
		
		//OBTENER ARTICULOS DE SOLICITUD SELECCIONADA
		if (request.getParameter("opcion").equalsIgnoreCase("obtArticulos")){
			//Obtengo la solicitud a buscar
			String solicitudABuscar = request.getParameter("solicitudBuscada");
			
			//Obtenemos de la base los ItemSolicitudArticulos de la SolicitudArticulo buscada
			List<ItemSolicitudArticuloDTO> itemSolArtDto = new ArrayList<ItemSolicitudArticuloDTO>();
			itemSolArtDto = depositoEntregaArticulo.obtenerItemDeSolicitud(solicitudABuscar);
			
			//Formateo la salida parseando columnas con ";" y filas con "-?"
			String respuesta = "";
			for (ItemSolicitudArticuloDTO i : itemSolArtDto){
				respuesta += i.getArticulo().getCodArticulo() +  ";" + i.getArticulo().getNombre() + ";" + i.getArticulo().getDescripcion() + ";" + i.getCantidad() + "-?";
			}
			//Cortamos el "-" final y enviamos la respuesta
			respuesta = respuesta.substring(0, respuesta.length()-2);
			response.getWriter().write(respuesta);			
		}
		
		
		
		
		//----------------------------------------------------------------------------------------------------//
		
		//INGRESAR ARTICULOS A COMPRAR
		if (request.getParameter("opcion").equalsIgnoreCase("ingresarArticulos")){
			//Obtengo la solicitud a buscar
			String cadenaStringSalida = request.getParameter("solicitudBuscada");
			//Obtengo el articulo a ser comprado
			String articuloAComprar = request.getParameter("articuloAComprar");
			//Obtengo la cantidad a ser comprada
			String cantidadAComprar = request.getParameter("cantidadAComprar");
			
			//Armo cadena a ser enviada y la guardo en el array
			String cadenaRow = cadenaStringSalida + ";" + articuloAComprar + ";" + cantidadAComprar;
			List<String> cadenaLocal = new ArrayList<String>();
			cadenaLocal = agregarRow(cadenaRow);
			
			//Armo la respuesta parseando la cadena a imprimir
			String respuesta = "";
			for (String s : cadenaLocal){
				respuesta = respuesta + s + "-?";
			}
			//Cortamos el "-" final y enviamos la respuesta
			respuesta = respuesta.substring(0, respuesta.length()-2);
			response.getWriter().write(respuesta);
		}
		

		
		
		
		//----------------------------------------------------------------------------------------------------//
		
		//ENVIAR SOLICITUD DE COMPRA
		if (request.getParameter("opcion").equalsIgnoreCase("enviarSolicitudCompra")){
			SolicitudCompraDTO solicitud = depositoEntregaArticulo.generarSolicitudCompra(cadenaStringSalida);		
			SolicitudCompraFabricaClient.conexion(solicitud);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	
	private List<String> agregarRow (String cadena){
		cadenaStringSalida.add(cadena);
		return cadenaStringSalida;
	}

}
