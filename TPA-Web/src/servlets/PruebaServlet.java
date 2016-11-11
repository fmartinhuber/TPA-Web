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

import org.apache.log4j.Logger;

import controler.IDepositoControladorLocal;
import dto.ArticuloDTO;
import dto.ItemSolicitudCompraDTO;
import dto.SolicitudArticuloDTO;
import dto.SolicitudCompraDTO;
import serviceREST.SolicitudCompraFabricaClient;

/**
 * Servlet implementation class PruebaServlet
 */
@WebServlet("/PruebaServlet")
public class PruebaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB
	IDepositoControladorLocal controlador;
	
	static Logger log = Logger.getLogger(PruebaServlet.class.getName());
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PruebaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		ArticuloEJBLocal.
//		try {
//			InitialContext ic = new InitialContext();
//			ArticuloBean asd = (ArticuloBean) 
//			ic.lookup("java:global/TPA-ServerEAR/TPA-Server/ArticuloEJB!negocio.ArticuloEJB");
//			System.out.println(asd.toString());
//		} catch (NamingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		List <SolicitudArticuloDTO> solicitudesArticulo = controlador.listarSolicitudArticuloPendiente();
		
		SolicitudCompraDTO solicitudCompraDTO = new SolicitudCompraDTO();
		solicitudCompraDTO.setCodigo(1);
		solicitudCompraDTO.setPendiente("pendiente");
		solicitudCompraDTO.setSolicitudesArticulos(solicitudesArticulo);
		ItemSolicitudCompraDTO itemSolicitudCompraDTO = new ItemSolicitudCompraDTO();
		itemSolicitudCompraDTO.setCantidad(1);
		ArticuloDTO articuloDTO = new ArticuloDTO();
		articuloDTO.setCodArticulo(1);
		articuloDTO.setMarca("marca");
		articuloDTO.setColor("color");
		itemSolicitudCompraDTO.setArticulo(articuloDTO);
		List <ItemSolicitudCompraDTO> list = new ArrayList <ItemSolicitudCompraDTO>();
		list.add(itemSolicitudCompraDTO);
		solicitudCompraDTO.setItemsSolicitudesCompra(list);
		
		controlador.crearSolicitudCompra(solicitudCompraDTO);
		
		SolicitudCompraFabricaClient.conexion(solicitudCompraDTO);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
